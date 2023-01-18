package com.hcyacg.http.common

import com.hcyacg.entity.model.NudgeKind
import com.hcyacg.entity.model.NudgeMessageModel
import com.hcyacg.entity.response.NudgeMessageResponse
import com.hcyacg.service.StrangerService
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
 * @Date  1/16/2023 19:47
 * @Description
 **/
class Stranger(
    override val miraiHost: String,
    override val miraiPort: Int,
    override val sessionKey: String?,
    override val defaultSessionStatus: String,
    override val client: HttpClient,
    override val json: Json
) : StrangerService {
    override val log: Logger = LoggerFactory.getLogger(this::class.java)

    @OptIn(InternalAPI::class)
    override fun sendNudge(qq: Long): Boolean {
        return try {
            runBlocking {
                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/sendNudge")
                        (if (sessionKey.isNullOrBlank()) {
                            defaultSessionStatus
                        } else {
                            sessionKey
                        }).let { it1 ->
                            parameters.append(
                                "sessionKey", it1
                            )
                        }
                    }

                    contentType(ContentType.Application.Json)
                    body = json.encodeToString(
                        NudgeMessageModel(
                            NudgeKind.STRANGER.value,
                            qq,
                            qq
                        )
                    )
                }

                log.debug(response.bodyAsText())
                val nudgeMessageResponse = Json.decodeFromString<NudgeMessageResponse>(response.bodyAsText())
                log.debug("发送戳一戳状态 => {}", nudgeMessageResponse)
                return@runBlocking nudgeMessageResponse.code == 0
            }
        } catch (e: Exception) {
            log.error("发送头像戳一戳出现异常 => {}", e.message)
            return false
        }
    }
}
