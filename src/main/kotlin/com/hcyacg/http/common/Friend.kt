package com.hcyacg.http.common

import com.hcyacg.entity.model.*
import com.hcyacg.entity.response.*
import com.hcyacg.message.MiraiMessageChain
import com.hcyacg.service.FriendService
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.util.*
import io.ktor.util.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

/**
 * @Author Nekoer
 * @Date  1/16/2023 18:37
 * @Description
 **/
open class Friend(
    override val miraiHost: String,
    override val miraiPort: Int,
    override val sessionKey: String?,
    override val defaultSessionStatus: String,
    override val client: HttpClient,
    override val json: Json
) : FriendService {
    override val log: Logger = LoggerFactory.getLogger(this::class.java)

    /**
     * 获取好友列表
     *
     * 使用此方法获取bot的好友列表
     */
    override fun friendList(): List<FriendListResponseData> {
        return try {
            runBlocking {
                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/friendList")
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
                val friendListResponse = json.decodeFromString<FriendListResponse>(response.bodyAsText())
                log.debug("获取好友列表 => {}", friendListResponse)
                return@runBlocking friendListResponse.data
            }
        } catch (e: Exception) {
            log.error("获取好友列表 => {}", e.message)
            return mutableListOf()
        }
    }


    /**
     * 获取好友资料
     */
    override fun friendProfile(target: Long): FriendProFile? {
        return try {
            runBlocking {
                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/friendProfile")
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
                val friendProFile = json.decodeFromString<FriendProFile>(response.bodyAsText())
                log.debug("获取好友资料 => {}", friendProFile)
                return@runBlocking friendProFile
            }
        } catch (e: Exception) {
            log.error("获取好友资料 => {}", e.message)
            return null
        }
    }

    @OptIn(InternalAPI::class)
    override fun sendMessage(messageChain: List<MiraiMessageChain>, target: Long): Long {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/sendFriendMessage")
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

                    contentType(ContentType.Application.Json)
                    body = json.encodeToString(FriendMessageModel(messageChain, target, null))

                }
                log.debug(response.bodyAsText())
                val sendMessageResponse = json.decodeFromString<SendMessageResponse>(response.bodyAsText())
                log.debug("本次消息的message id => {}", sendMessageResponse.messageId)
                return@runBlocking sendMessageResponse.messageId
            }
        } catch (e: Exception) {
            log.error("发送好友消息出现异常 => {}", e.message)
            return -1
        }
    }

    @OptIn(InternalAPI::class)
    override fun sendMessageWithQuote(messageChain: List<MiraiMessageChain>, target: Long, messageId: Long): Long {
        return try {
            runBlocking {
                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/sendFriendMessage")
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

                    contentType(ContentType.Application.Json)
                    body = json.encodeToString(FriendMessageModel(messageChain, target, messageId))

                }
                log.debug(response.bodyAsText())
                val sendMessageResponse = json.decodeFromString<SendMessageResponse>(response.bodyAsText())
                log.debug("本次消息的message id => {}", sendMessageResponse.messageId)
                return@runBlocking sendMessageResponse.messageId
            }
        } catch (e: Exception) {
            log.error("发送好友消息出现异常 => {}", e.message)
            return -1
        }
    }

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
                        })?.let { it1 ->
                            parameters.append(
                                "sessionKey", it1
                            )
                        }
                    }

                    contentType(ContentType.Application.Json)
                    body = json.encodeToString(
                        NudgeMessageModel(
                            NudgeKind.FRIEND.value,
                            qq,
                            qq
                        )
                    )
                }

                log.debug(response.bodyAsText())
                val nudgeMessageResponse = json.decodeFromString<NudgeMessageResponse>(response.bodyAsText())
                log.debug("发送戳一戳状态 => {}", nudgeMessageResponse)
                return@runBlocking nudgeMessageResponse.msg.contains("success")
            }
        } catch (e: Exception) {
            log.error("发送头像戳一戳出现异常 => {}", e.message)
            return false
        }
    }

    @OptIn(InternalAPI::class)
    override fun recall(messageId: Long, friend: Long): Boolean {

        return try {
            runBlocking {
                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/recall")
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

                    contentType(ContentType.Application.Json)
                    body = json.encodeToString(RecallModel(messageId, friend))
                }

                log.debug(response.bodyAsText())
                val recallResponse = json.decodeFromString<RecallResponse>(response.bodyAsText())
                log.debug("撤回状态 => {}", recallResponse)
                return@runBlocking recallResponse.msg.contains("success")
            }
        } catch (e: Exception) {
            log.error("撤回消息出现异常 => {}", e.message)
            return false
        }
    }

    /**
     * 获取漫游消息
     */
    @OptIn(InternalAPI::class)
    override fun roamingMessages(timeStart: Long, timeEnd: Long, friendId: Long): List<MiraiMessageChain> {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/roamingMessages")
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

                    contentType(ContentType.Application.Json)
                    body = json.encodeToString(RoamingMessagesModel(timeStart, timeEnd, friendId))
                }

                log.debug(response.bodyAsText())
                val roamingMessagesResponse = json.decodeFromString<RoamingMessagesResponse>(response.bodyAsText())
                log.debug("获取漫游消息 => {}", roamingMessagesResponse)
                return@runBlocking roamingMessagesResponse.data
            }
        } catch (e: Exception) {
            log.error("获取漫游消息出现异常 => {}", e.message)
            return mutableListOf()
        }
    }


    override fun uploadImage(img: File): UploadImageResponse? {

        return try {
            runBlocking {

                val response: HttpResponse = client.submitFormWithBinaryData(
                    url = url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/uploadImage")
                        (if (sessionKey.isNullOrBlank()) {
                            defaultSessionStatus
                        } else {
                            sessionKey
                        })?.let {
                            parameters.append(
                                "sessionKey", it
                            )
                        }
                    },

                    formData = formData {
                        append("type", "friend")
                        append("image", img.readBytes(), Headers.build {
                            append(HttpHeaders.ContentDisposition, "filename=\"${img.name}\"")
                        })
                    }
                )

                log.debug(response.bodyAsText())
                val uploadImageResponse = json.decodeFromString<UploadImageResponse>(response.bodyAsText())
                log.debug("上传图片 => {}", uploadImageResponse)
                return@runBlocking uploadImageResponse
            }
        } catch (e: Exception) {
            log.error("删除文件出现异常 => {}", e.message)
            return null
        }
    }

    @OptIn(InternalAPI::class)
    override fun deleteFriend(friendId: Long): Boolean {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/deleteFriend")
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

                    contentType(ContentType.Application.Json)
                    body = json.encodeToString(DeleteFriendModel(friendId))
                }

                log.debug(response.bodyAsText())
                val bindResponse = json.decodeFromString<BindResponse>(response.bodyAsText())
                log.debug("删除好友 => {}", bindResponse)
                return@runBlocking bindResponse.code == 0
            }
        } catch (e: Exception) {
            log.error("删除好友出现异常 => {}", e.message)
            return false
        }
    }


    /**
     * operate	说明
     *
     * 0	同意添加好友
     *
     * 1	拒绝添加好友
     *
     * 2	拒绝添加好友并添加黑名单，不再接收该用户的好友申请
     */
    @OptIn(InternalAPI::class)
    override fun manageNewFriend(eventId: Long, fromId: Long, groupId: Long, message: String, operate: Int): Boolean {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/resp/newFriendRequestEvent")
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
                    contentType(ContentType.Application.Json)
                    body = json.encodeToString(
                        ManageNewFriendModel(
                            eventId,
                            fromId,
                            groupId,
                            message,
                            operate
                        )
                    )
                }
                log.debug(response.bodyAsText())
                val bindResponse = json.decodeFromString<BindResponse>(response.bodyAsText())
                log.debug("添加好友申请 => {}", bindResponse)
                return@runBlocking bindResponse.code == 0

            }
        } catch (e: Exception) {
            log.error("添加好友申请出现异常 => {}", e.message)
            return false
        }
    }
}
