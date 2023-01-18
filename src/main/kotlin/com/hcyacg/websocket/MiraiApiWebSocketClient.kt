package com.hcyacg.websocket

import ch.qos.logback.classic.Level
import com.hcyacg.MiraiApiBaseClient
import com.hcyacg.config.LogLevelConfig
import com.hcyacg.http.common.*
import com.hcyacg.message.MiraiMessageChain
import com.hcyacg.message.MiraiMessageChainSerializer
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import io.ktor.websocket.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import kotlin.concurrent.thread

/**
 * @Author Nekoer
 * @Date  1/17/2023 23:44
 * @Description
 **/
private class MiraiApiWebSocketClient(
    private val miraiHost: String,
    private val miraiPort: Int,
    private val miraiVerifyKey: String?,
    private val botQQ: Long,
    debug: Boolean = false
) : MiraiApiBaseClient {
    override var sessionKey: String? = null
    override val log: Logger = LoggerFactory.getLogger(this::class.java)
    override val json: Json = Json {
        encodeDefaults = false
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }
    override val group: Group
        get() = TODO("Not yet implemented")
    override val friend: Friend
        get() = TODO("Not yet implemented")
    override val bot: Bot
        get() = TODO("Not yet implemented")
    override val stranger: Stranger
        get() = TODO("Not yet implemented")
    override val console: Console
        get() = TODO("Not yet implemented")

    override val client = HttpClient(CIO) {
        install(WebSockets) {
            pingInterval = 20_000
            maxFrameSize = Long.MAX_VALUE
            contentConverter = KotlinxWebsocketSerializationConverter(json)
        }
    }

    @Volatile
    private var asyncId: Long = 0

    @Synchronized
    internal fun nextAsyncId(): Long {
        asyncId += 1
        val new = asyncId
        if (new > 1000000) {
            asyncId = Random().nextLong(1060000)
        }
        return new
    }

    internal val commands = mutableListOf<String>()

    init {
        if (debug) {
            LogLevelConfig.setLevel(Level.DEBUG)
        } else {
            LogLevelConfig.setLevel(Level.INFO)
        }


        thread {
            verify()
        }
    }

    override fun verify(): Unit {
        try {
            miraiVerifyKey?.let {
                runBlocking {
                    client.webSocket(
                        method = HttpMethod.Get,
                        host = miraiHost,
                        port = miraiPort,
                        path = "/all",
                        request = {
                            parameter("qq", botQQ)
                            parameter("verifyKey", it)
                        }
                    ) {
                        while (true) {
                            val othersMessage = incoming.receive()
                            val response = othersMessage.readBytes().decodeToString()
                            log.debug(response)

                            val tempJson = json.parseToJsonElement(response)
                            val session =
                                tempJson.jsonObject["data"]?.jsonObject?.get("session")?.jsonPrimitive?.contentOrNull
                            if (!session.isNullOrBlank()) {
                                if (!session.contentEquals(defaultSessionStatus)) {
                                    sessionKey = session
                                    log.debug("获取到本次连接的session Key => {}", session)
                                }
                            } else if (tempJson.jsonObject["syncId"]?.jsonPrimitive?.content.contentEquals("-1")) {
                                //mirai通知
                                val message = json.decodeFromString(
                                    MiraiMessageChainSerializer,
                                    tempJson.jsonObject["data"].toString()
                                )
                                log.debug(message.toString())
                            } else if (commands.size > 0) {
                                commands.forEach {
                                    send(it)
                                    commands.remove(it)
                                }
                            }
                        }
                    }
                }
            }

            client.close()
        } catch (e: Exception) {
            log.error("认证出现异常 => {}", e.message)
        }
    }


    override fun about(): String? {
        return try {
            runBlocking {
//                val id = nextAsyncId()
//                log.debug(clientSession.toString())
//                clientSession.get().send(json.encodeToString(WebSocketSendMessageModel(
//                    "about",
//                    null,
//                    null,
//                    id
//                )))
//
//                log.debug(clientSession.get().incoming.receive().toString())
                return@runBlocking ""
            }
        } catch (e: Exception) {
            log.error("查看关于出现异常 => {}", e.message)
            return null
        }
    }

    override fun messageFromId(messageId: Int, target: Long): List<MiraiMessageChain>? {
        TODO("Not yet implemented")
    }

}
