package com.hcyacg.http.common

import com.hcyacg.entity.model.CmdExecute
import com.hcyacg.entity.model.CmdRegisterModel
import com.hcyacg.entity.response.BindResponse
import com.hcyacg.message.MiraiMessageChain
import com.hcyacg.service.ConsoleService
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @Author Nekoer
 * @Date  1/17/2023 23:32
 * @Description
 **/
class Console(
    override val miraiHost: String,
    override val miraiPort: Int,
    override val sessionKey: String?,
    override val defaultSessionStatus: String,
    override val client: HttpClient,
    override val json: Json
) : ConsoleService {
    override val log: Logger = LoggerFactory.getLogger(this::class.java)

    @OptIn(InternalAPI::class)
    override fun cmdExecute(messageChain: List<MiraiMessageChain>): Boolean {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/cmd/execute")
                        parameters.append(
                            "sessionKey", if (sessionKey.isNullOrBlank()) {
                                defaultSessionStatus
                            } else {
                                sessionKey
                            }
                        )
                    }
                    contentType(ContentType.Application.Json)
                    body = json.encodeToString(CmdExecute(messageChain))
                }

                log.debug(response.bodyAsText())
                val bindResponse = json.decodeFromString<BindResponse>(response.bodyAsText())
                log.debug("执行命令 => {}", bindResponse)
                return@runBlocking bindResponse.code == 0
            }
        } catch (e: Exception) {
            log.error("执行命令出现异常 => {}", e.message)
            return false
        }
    }

    @OptIn(InternalAPI::class)
    override fun cmdRegister(cmdRegisterModel: CmdRegisterModel): Boolean {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/cmd/register")
                        parameters.append(
                            "sessionKey", if (sessionKey.isNullOrBlank()) {
                                defaultSessionStatus
                            } else {
                                sessionKey
                            }
                        )
                    }
                    contentType(ContentType.Application.Json)
                    body = json.encodeToString(cmdRegisterModel)
                }

                log.debug(response.bodyAsText())
                val bindResponse = json.decodeFromString<BindResponse>(response.bodyAsText())
                log.debug("注册命令 => {}", bindResponse)
                return@runBlocking bindResponse.code == 0
            }
        } catch (e: Exception) {
            log.error("注册命令出现异常 => {}", e.message)
            return false
        }
    }
}
