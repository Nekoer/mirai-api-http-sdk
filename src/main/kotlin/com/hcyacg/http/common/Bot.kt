package com.hcyacg.http.common

import com.hcyacg.entity.response.BotListResponse
import com.hcyacg.entity.response.BotProFile
import com.hcyacg.entity.response.UserProfile
import com.hcyacg.service.BotService
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @Author Nekoer
 * @Date  1/16/2023 18:38
 * @Description
 **/
open class Bot(
    override val miraiHost: String,
    override val miraiPort: Int,
    override val sessionKey: String?,
    override val defaultSessionStatus: String,
    override val client: HttpClient,
    override val json: Json
) : BotService {
    override val log: Logger = LoggerFactory.getLogger(this::class.java)


    /**
     * 获取登录账号
     * 使用此方法获取所有当前登录账号
     */
    override fun botList(): List<Long> {
        return try {
            runBlocking {
                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/botList")
                    }
                }
                log.debug(response.bodyAsText())
                val botListResponse = json.decodeFromString<BotListResponse>(response.bodyAsText())
                log.debug("获取登录账号 => {}", botListResponse)
                return@runBlocking botListResponse.data
            }
        } catch (e: Exception) {
            log.error("获取登录账号 => {}", e.message)
            return mutableListOf()
        }
    }

    /**
     * 获取Bot资料
     */
    override fun botProfile(): BotProFile? {
        return try {
            runBlocking {
                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/botProfile")
                        (if (sessionKey.isNullOrBlank()) {
                            defaultSessionStatus
                        } else {
                            sessionKey
                        })?.let { it1 ->
                            parameters.append(
                                "sessionKey", it1
                            )
                        }
                    }
                }
                log.debug(response.bodyAsText())
                val botProFile = json.decodeFromString<BotProFile>(response.bodyAsText())
                log.debug("获取Bot资料 => {}", botProFile)
                return@runBlocking botProFile
            }
        } catch (e: Exception) {
            log.error("获取Bot资料 => {}", e.message)
            return null
        }
    }

    /**
     * 获取QQ用户资料
     */
    override fun userProfile(target: Long): UserProfile? {
        return try {
            runBlocking {
                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/userProfile")

                        (if (sessionKey.isNullOrBlank()) {
                            defaultSessionStatus
                        } else {
                            sessionKey
                        })?.let { it1 ->
                            parameters.append(
                                "sessionKey", it1
                            )
                        }
                        parameters.append("target", target.toString())
                    }
                }
                log.debug(response.bodyAsText())
                val userProfile = json.decodeFromString<UserProfile>(response.bodyAsText())
                log.debug("获取QQ用户资料 => {}", userProfile)
                return@runBlocking userProfile
            }
        } catch (e: Exception) {
            log.error("获取QQ用户资料 => {}", e.message)
            return null
        }
    }
}
