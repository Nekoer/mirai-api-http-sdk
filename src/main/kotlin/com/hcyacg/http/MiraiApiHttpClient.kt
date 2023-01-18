package com.hcyacg.http

import ch.qos.logback.classic.Level
import com.hcyacg.MiraiApiBaseClient
import com.hcyacg.config.LogLevelConfig
import com.hcyacg.entity.model.BindModel
import com.hcyacg.entity.model.ReleaseModel
import com.hcyacg.entity.model.VerifyModel
import com.hcyacg.entity.response.*
import com.hcyacg.http.common.*
import com.hcyacg.http.common.Group
import com.hcyacg.message.MiraiMessageChain
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @Author Nekoer
 * @Date  1/15/2023 19:49
 * @Description mirai 的 http api 客户端对象
 */

/**
 * mirai 的 http api 客户端对象
 */

class MiraiApiclient(
    private val miraiHost: String,
    private val miraiPort: Int,
    private val miraiVerifyKey: String?,
    private val botQQ: Long,
    debug: Boolean = false
) : MiraiApiBaseClient {
    override var sessionKey: String? = null
    override val log: Logger = LoggerFactory.getLogger(this::class.java)
    override val json = Json {
        encodeDefaults = false
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }

    override val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    override val group = Group(miraiHost, miraiPort, sessionKey, defaultSessionStatus, client, json)
    override val friend = Friend(miraiHost, miraiPort, sessionKey, defaultSessionStatus, client, json)
    override val bot = Bot(miraiHost, miraiPort, sessionKey, defaultSessionStatus, client, json)
    override val stranger = Stranger(miraiHost, miraiPort, sessionKey, defaultSessionStatus, client, json)
    override val console = Console(miraiHost, miraiPort, sessionKey, defaultSessionStatus, client, json)

    init {
        if (debug) {
            LogLevelConfig.setLevel(Level.DEBUG)
        } else {
            LogLevelConfig.setLevel(Level.INFO)
        }


        val bindFlag = bind()
        log.debug("bind的状态 => {}", bindFlag)


    }

    /**
     * 认证
     * @see <a href="https://docs.mirai.mamoe.net/mirai-api-http/adapter/HttpAdapter.html#%E8%AE%A4%E8%AF%81">认证</a>
     */
    @OptIn(InternalAPI::class)
    override fun verify() {
        try {
            runBlocking {
                miraiVerifyKey?.let {
                    val response: HttpResponse = client.post {
                        url {
                            host = miraiHost
                            port = miraiPort
                            appendPathSegments("/verify")
                        }

                        contentType(ContentType.Application.Json)
                        body = Json.encodeToString(VerifyModel(verifyKey = it))
                    }
                    log.debug(response.bodyAsText())
                    val session = Json.decodeFromString<MiraiHttpSessionKey>(response.readBytes().decodeToString())
                    if (!session.session.contentEquals(defaultSessionStatus)) {
                        sessionKey = session.session
                    }
                    log.debug("获取到本次连接的session Key => {}", session.session)
                }
            }
        } catch (e: Exception) {
            log.error("认证出现异常 => {}", e.message)
        }
    }

    /**
     * 绑定并激活session
     * @see <a href="https://docs.mirai.mamoe.net/mirai-api-http/adapter/HttpAdapter.html#%E7%BB%91%E5%AE%9A">绑定</a>
     */
    @OptIn(InternalAPI::class)
    fun bind(): Boolean {
        try {
            runBlocking {
                verify()
                sessionKey?.let {

                    val response: HttpResponse = client.post {
                        url {
                            host = miraiHost
                            port = miraiPort
                            appendPathSegments("/bind")
                        }

                        contentType(ContentType.Application.Json)
                        body = Json.encodeToString(BindModel(sessionKey = it, qq = botQQ))
                    }
                    log.debug(response.bodyAsText())
                    val bindResponse = Json.decodeFromString<BindResponse>(response.bodyAsText())
                    log.debug("绑定状态 => code:{} msg:{}", bindResponse.code, bindResponse.msg)

                    return@runBlocking bindResponse.msg.contains("success")
                }
            }
            return false
        } catch (e: Exception) {
            log.error("绑定出现异常 => {}", e.message)
            return false
        }
    }


    /**
     * 获取会话信息
     *
     * 使用此方法获取 session 的相关信息
     */
    fun sessionInfo(): SessionInfoResponseData? {
        return try {
            runBlocking {
                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        if (sessionKey.isNullOrBlank()) {
                            appendPathSegments("/sessionInfo")
                        } else {
                            appendPathSegments("/sessionInfo?sessionKey=$sessionKey")

                        }
                    }
                    contentType(ContentType.Application.Json)
                }
                log.debug(response.bodyAsText())
                val sessionInfoResponse = Json.decodeFromString<SessionInfoResponse>(response.bodyAsText())
                log.debug("获取会话信息 => {}", sessionInfoResponse)
                return@runBlocking sessionInfoResponse.sessionInfoResponseData
            }
        } catch (e: Exception) {
            log.error("获取会话信息出现异常 => {}", e.message)
            return null
        }
    }

    /**
     * 使用此方式释放session及其相关资源（Bot不会被释放）
     *
     * 不使用的Session应当被释放，长时间（30分钟）未使用的Session将自动释放，否则Session持续保存Bot收到的消息，将会导致内存泄露(开启websocket后将不会自动释放)
     */
    @OptIn(InternalAPI::class)
    fun release(): Boolean {
        var data = ""
        if (sessionKey.isNullOrBlank()) {
            data = Json.encodeToString(ReleaseModel(sessionKey = defaultSessionStatus, qq = botQQ))
        } else {
            sessionKey?.let {
                data = Json.encodeToString(ReleaseModel(sessionKey = it, qq = botQQ))
            }
        }
        return try {
            runBlocking {
                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/release")
                    }

                    body = data
                    contentType(ContentType.Application.Json)
                }
                log.debug(response.bodyAsText())
                val releaseResponse = Json.decodeFromString<ReleaseResponse>(response.bodyAsText())
                log.debug("释放session => code:{} msg:{}", releaseResponse.code, releaseResponse.msg)
                return@runBlocking releaseResponse.msg.contains("success")
            }
        } catch (e: Exception) {
            log.error("释放session出现异常 => {}", e.message)
            return false
        }
    }


    /**
     * 查看队列大小
     *
     * 使用此方法获取 session 未读缓存消息的数量
     */
    fun countMessage(): Int? {
        return try {
            runBlocking {
                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        if (sessionKey.isNullOrBlank()) {
                            appendPathSegments("/countMessage?sessionKey=$defaultSessionStatus")
                        } else {
                            appendPathSegments("/countMessage?sessionKey=$sessionKey")
                        }


                    }
                    contentType(ContentType.Application.Json)
                }
                log.debug(response.bodyAsText())
                val countMessageResponse = Json.decodeFromString<CountMessageResponse>(response.bodyAsText())
                log.debug("获取信息队列 => {}", countMessageResponse)
                return@runBlocking countMessageResponse.data
            }
        } catch (e: Exception) {
            log.error("获取信息队列出现异常 => {}", e.message)
            return -1
        }
    }

    /**
     * 获取队列头部
     *
     * 即按时间顺序获取消息，获取消息后从队列中移除
     */
    fun fetchMessage(count: Int): List<MiraiMessageChain> {
        return try {
            runBlocking {
                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        if (sessionKey.isNullOrBlank()) {
                            appendEncodedPathSegments("/fetchMessage?sessionKey=$defaultSessionStatus&count=$count")
                        } else {
                            appendEncodedPathSegments("/fetchMessage?sessionKey=$it&count=$count")
                        }
                    }
                }
                log.debug(response.bodyAsText())
                val messageResponse = Json.decodeFromString<MessageResponse>(response.bodyAsText())
                log.debug("获取队列头部 => {}", messageResponse)
                return@runBlocking messageResponse.data
            }
        } catch (e: Exception) {
            log.error("获取队列头部出现异常 => {}", e.message)
            return mutableListOf()
        }
    }

    /**
     * 获取队列尾部
     *
     * 即获取最新的消息，获取消息后从队列中移除
     */
    fun fetchLatestMessage(count: Int): List<MiraiMessageChain> {
        return try {
            runBlocking {
                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        if (sessionKey.isNullOrBlank()) {
                            appendEncodedPathSegments("/fetchLatestMessage?sessionKey=$defaultSessionStatus&count=$count")
                        } else {
                            appendEncodedPathSegments("/fetchLatestMessage?sessionKey=$it&count=$count")
                        }
                    }
                }
                log.debug(response.bodyAsText())
                val messageResponse = Json.decodeFromString<MessageResponse>(response.bodyAsText())
                log.debug("获取队列尾部 => {}", messageResponse)
                return@runBlocking messageResponse.data
            }
        } catch (e: Exception) {
            log.error("获取队列尾部出现异常 => {}", e.message)
            return mutableListOf()
        }
    }

    /**
     * 查看队列头部
     *
     * 即按时间顺序查看消息，查看消息后不从队列中移除
     */
    fun peekMessage(count: Int): List<MiraiMessageChain> {
        return try {
            runBlocking {
                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        if (sessionKey.isNullOrBlank()) {
                            appendEncodedPathSegments("/peekMessage?sessionKey=$defaultSessionStatus&count=$count")
                        } else {
                            appendEncodedPathSegments("/peekMessage?sessionKey=$it&count=$count")
                        }
                    }
                }
                log.debug(response.bodyAsText())
                val messageResponse = Json.decodeFromString<MessageResponse>(response.bodyAsText())
                log.debug("查看队列头部 => {}", messageResponse)
                return@runBlocking messageResponse.data
            }
        } catch (e: Exception) {
            log.error("查看队列头部出现异常 => {}", e.message)
            return mutableListOf()
        }
    }

    /**
     * 查看队列尾部
     *
     * 即查看最新的消息，查看消息后不从队列中移除
     */
    fun peekLatestMessage(count: Int): List<MiraiMessageChain> {
        return try {
            runBlocking {
                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        if (sessionKey.isNullOrBlank()) {
                            appendEncodedPathSegments("/peekLatestMessage?sessionKey=$defaultSessionStatus&count=$count")
                        } else {
                            appendEncodedPathSegments("/peekLatestMessage?sessionKey=$it&count=$count")
                        }
                    }
                }
                log.debug(response.bodyAsText())
                val messageResponse = Json.decodeFromString<MessageResponse>(response.bodyAsText())
                log.debug("查看队列尾部 => {}", messageResponse)
                return@runBlocking messageResponse.data
            }
        } catch (e: Exception) {
            log.error("查看队列尾部出现异常 => {}", e.message)
            return mutableListOf()
        }
    }


    /**
     * 关于
     *
     * 使用此方法获取插件的信息，如版本号
     */
    override fun about(): String? {
        return try {
            runBlocking {
                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/about")
                    }
                }
                log.debug(response.bodyAsText())
                val aboutResponse = Json.decodeFromString<AboutResponse>(response.bodyAsText())
                log.debug("关于 => {}", aboutResponse)
                return@runBlocking aboutResponse.data.version
            }
        } catch (e: Exception) {
            log.error("查看关于出现异常 => {}", e.message)
            return null
        }
    }


    /**
     * 缓存操作
     *
     * 通过messageId获取消息
     * 此方法通过 messageId 获取历史消息, 历史消息的缓存有容量大小, 在配置文件中设置
     *
     * messageId	Int	    false	1234567890	获取消息的messageId
     *
     * target	    Long	false	1234567890	好友id或群id
     */
    override fun messageFromId(messageId: Int, target: Long): List<MiraiMessageChain>? {
        return try {
            runBlocking {
                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        if (sessionKey.isNullOrBlank()) {
                            appendEncodedPathSegments("/messageFromId?sessionKey=$defaultSessionStatus&messageId=$messageId&target=$target")
                        } else {
                            appendEncodedPathSegments("/messageFromId?sessionKey=$it&messageId=$messageId&target=$target")
                        }
                    }
                }
                log.debug(response.bodyAsText())
                val messageFromIdResponse = Json.decodeFromString<MessageFromIdResponse>(response.bodyAsText())
                log.debug("通过messageId获取消息 => {}", messageFromIdResponse)
                return@runBlocking messageFromIdResponse.data.messageChain
            }
        } catch (e: Exception) {
            log.error("通过messageId获取消息 => {}", e.message)
            return null
        }
    }


}
