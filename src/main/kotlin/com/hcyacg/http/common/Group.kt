package com.hcyacg.http.common

import com.hcyacg.entity.model.*
import com.hcyacg.entity.response.*
import com.hcyacg.message.MiraiMessageChain
import com.hcyacg.service.GroupService
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
 * @Date  1/16/2023 18:36
 * @Description
 **/
open class Group(
    override val miraiHost: String,
    override val miraiPort: Int,
    override val sessionKey: String?,
    override val defaultSessionStatus: String,
    override val client: HttpClient,
    override val json: Json
) : GroupService {
    override val log: Logger = LoggerFactory.getLogger(this::class.java)

    /**
     * 获取群列表
     *
     * 使用此方法获取bot的群列表
     */
    override fun groupList(): List<GroupListResponseData> {
        return try {
            runBlocking {
                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/groupList")
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
                val groupListResponse = json.decodeFromString<GroupListResponse>(response.bodyAsText())
                log.debug("获取群列表 => {}", groupListResponse)
                return@runBlocking groupListResponse.data
            }
        } catch (e: Exception) {
            log.error("获取群列表 => {}", e.message)
            return mutableListOf()
        }
    }

    /**
     * 获取群成员列表
     */
    override fun memberList(target: Long): List<MemberListResponseData> {
        return try {
            runBlocking {
                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/memberList")
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
                val groupListResponse = json.decodeFromString<MemberListResponse>(response.bodyAsText())
                log.debug("获取群成员列表 => {}", groupListResponse)
                return@runBlocking groupListResponse.data
            }
        } catch (e: Exception) {
            log.error("获取群成员列表 => {}", e.message)
            return mutableListOf()
        }
    }


    /**
     * 获取群成员资料
     */
    override fun memberProfile(target: Long, memberId: Long): MemberProfile? {
        return try {
            runBlocking {
                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/memberProfile")
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
                        parameters.append("memberId", memberId.toString())

                    }
                }
                log.debug(response.bodyAsText())
                val memberProfile = json.decodeFromString<MemberProfile>(response.bodyAsText())
                log.debug("获取群成员资料 => {}", memberProfile)
                return@runBlocking memberProfile
            }
        } catch (e: Exception) {
            log.error("获取群成员资料 => {}", e.message)
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
                        appendPathSegments("/sendGroupMessage")
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
                    body = json.encodeToString(GroupMessageModel(messageChain, target, null))
                }
                log.debug(response.bodyAsText())
                val sendMessageResponse = json.decodeFromString<SendMessageResponse>(response.bodyAsText())
                log.debug("本次消息的message id => {}", sendMessageResponse.messageId)
                return@runBlocking sendMessageResponse.messageId
            }
        } catch (e: Exception) {
            log.error("发送群消息出现异常 => {}", e.message)
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
                        appendPathSegments("/sendGroupMessage")
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
                    body = json.encodeToString(GroupMessageModel(messageChain, target, messageId))

                }

                log.debug(response.bodyAsText())
                val sendMessageResponse = json.decodeFromString<SendMessageResponse>(response.bodyAsText())
                log.debug("本次消息的message id => {}", sendMessageResponse.messageId)
                return@runBlocking sendMessageResponse.messageId
            }
        } catch (e: Exception) {
            log.error("发送群消息出现异常 => {}", e.message)
            return -1
        }
    }


    @OptIn(InternalAPI::class)
    override fun sendTempMessage(messageChain: List<MiraiMessageChain>, qq: Long, group: Long): Long {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/sendGroupMessage")
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
                    body = json.encodeToString(GroupTempMessageModel(messageChain, qq, group, null))

                }
                log.debug(response.bodyAsText())
                val sendMessageResponse = json.decodeFromString<SendMessageResponse>(response.bodyAsText())
                log.debug("本次消息的message id => {}", sendMessageResponse.messageId)
                return@runBlocking sendMessageResponse.messageId
            }
        } catch (e: Exception) {
            log.error("发送临时群消息出现异常 => {}", e.message)
            return -1
        }
    }

    @OptIn(InternalAPI::class)
    override fun sendTempMessageWithQuote(
        messageChain: List<MiraiMessageChain>,
        qq: Long,
        group: Long,
        messageId: Long
    ): Long {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/sendGroupMessage")
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
                        GroupTempMessageModel(
                            messageChain,
                            qq,
                            group,
                            messageId
                        )
                    )

                }

                log.debug(response.bodyAsText())
                val sendMessageResponse = json.decodeFromString<SendMessageResponse>(response.bodyAsText())
                log.debug("本次消息的message id => {}", sendMessageResponse.messageId)
                return@runBlocking sendMessageResponse.messageId
            }
        } catch (e: Exception) {
            log.error("发送临时群消息出现异常 => {}", e.message)
            return -1
        }
    }

    @OptIn(InternalAPI::class)
    override fun sendNudge(group: Long, qq: Long): Boolean {
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
                            NudgeKind.GROUP.value,
                            group,
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
    override fun recall(messageId: Long, group: Long): Boolean {

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
                    body = json.encodeToString(RecallModel(messageId, group))

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

    override fun fileList(fileListModel: FileListModel): List<FileListResponseData> {
        return try {
            runBlocking {
                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/file/list")
                        (if (sessionKey.isNullOrBlank()) {
                            defaultSessionStatus
                        } else {
                            sessionKey
                        })?.let { it1 ->
                            parameters.append(
                                "sessionKey", it1
                            )
                        }
                        fileListModel.group?.toString()?.let { it1 -> parameters.append("group", it1) }
                        fileListModel.target?.toString()?.let { it1 -> parameters.append("target", it1) }
                        fileListModel.withDownloadInfo?.toString()
                            ?.let { it1 -> parameters.append("withDownloadInfo", it1) }
                        fileListModel.id?.let { it1 -> parameters.append("id", it1) }
                        fileListModel.offset?.toString()?.let { it1 -> parameters.append("offset", it1) }
                        fileListModel.size?.toString()?.let { it1 -> parameters.append("size", it1) }
                        fileListModel.qq?.toString()?.let { it1 -> parameters.append("qq", it1) }
                    }
                }
                log.debug(response.bodyAsText())
                val fileListResponse = json.decodeFromString<FileListResponse>(response.bodyAsText())
                log.debug("查看文件列表 => {}", fileListResponse)
                return@runBlocking fileListResponse.data
            }
        } catch (e: Exception) {
            log.error("查看文件列表出现异常 => {}", e.message)
            return mutableListOf()
        }
    }

    override fun fileInfo(fileInfoModel: FileInfoModel): FileListResponseData? {
        return try {
            runBlocking {
                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/file/info")
                        (if (sessionKey.isNullOrBlank()) {
                            defaultSessionStatus
                        } else {
                            sessionKey
                        })?.let { it1 ->
                            parameters.append(
                                "sessionKey", it1
                            )
                        }
                        fileInfoModel.group?.toString()?.let { it1 -> parameters.append("group", it1) }
                        fileInfoModel.target?.toString()?.let { it1 -> parameters.append("target", it1) }
                        fileInfoModel.withDownloadInfo?.toString()
                            ?.let { it1 -> parameters.append("withDownloadInfo", it1) }
                        fileInfoModel.id.let { it1 -> parameters.append("id", it1) }
                        fileInfoModel.qq?.toString()?.let { it1 -> parameters.append("qq", it1) }
                    }


                }
                log.debug(response.request.url.toString())
                log.debug(response.bodyAsText())
                val fileInfoResponse = json.decodeFromString<FileInfoResponse>(response.bodyAsText())
                log.debug("获取文件信息 => {}", fileInfoResponse)
                return@runBlocking fileInfoResponse.data
            }
        } catch (e: Exception) {
            log.error("获取文件信息出现异常 => {}", e.message)
            return null
        }
    }

    @OptIn(InternalAPI::class)
    override fun mkdirFileFolder(fileMkdirModel: FileMkdirModel): FileListResponseData? {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/file/mkdir")
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
                    body = json.encodeToString(fileMkdirModel)
                }

                log.debug(response.bodyAsText())
                val fileInfoResponse = json.decodeFromString<FileInfoResponse>(response.bodyAsText())
                log.debug("创建文件夹 => {}", fileInfoResponse)
                return@runBlocking fileInfoResponse.data
            }
        } catch (e: Exception) {
            log.error("创建文件夹出现异常 => {}", e.message)
            return null
        }
    }

    @OptIn(InternalAPI::class)
    override fun deleteFile(deleteFileModel: DeleteFileModel): Boolean {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/file/delete")
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
                    body = json.encodeToString(deleteFileModel)
                }

                log.debug(response.bodyAsText())
                val bindResponse = json.decodeFromString<BindResponse>(response.bodyAsText())
                log.debug("删除文件 => {}", bindResponse)
                return@runBlocking bindResponse.code == 0
            }
        } catch (e: Exception) {
            log.error("删除文件出现异常 => {}", e.message)
            return false
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
                        append("type", "group")
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


    override fun uploadTempGroupImage(img: File): UploadImageResponse? {

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
                        append("type", "temp")
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


    override fun uploadVoice(voice: File): UploadVoiceResponse? {

        return try {
            runBlocking {

                val response: HttpResponse = client.submitFormWithBinaryData(
                    url = url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/uploadVoice")
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
                        append("type", "temp")
                        append("voice", voice.readBytes(), Headers.build {
                            append(HttpHeaders.ContentDisposition, "filename=\"${voice.name}\"")
                        })
                    }
                )

                log.debug(response.bodyAsText())
                val uploadVoiceResponse = json.decodeFromString<UploadVoiceResponse>(response.bodyAsText())
                log.debug("语音文件上传 => {}", uploadVoiceResponse)
                return@runBlocking uploadVoiceResponse
            }
        } catch (e: Exception) {
            log.error("语音文件上传出现异常 => {}", e.message)
            return null
        }
    }


    override fun uploadFile(target: Long, path: String, file: File): FileListResponseData? {
        return try {
            runBlocking {

                val response: HttpResponse = client.submitFormWithBinaryData(

                    url = url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/file/upload")
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
                        append("type", "group")
                        append("target", target)
                        append("path", path)
                        append("file", file.readBytes(), Headers.build {
                            append(HttpHeaders.ContentDisposition, "filename=\"${file.name}\"")
                        })

                    })

                log.debug(response.bodyAsText())
                val fileInfoResponse = json.decodeFromString<FileInfoResponse>(response.bodyAsText())
                log.debug("群文件上传 => {}", fileInfoResponse)
                return@runBlocking fileInfoResponse.data

            }
        } catch (e: Exception) {
            e.printStackTrace()
            log.error("群文件上传出现异常 => {}", e.message)
            return null
        }
    }

    override fun mute(groupId: Long, memberId: Long, time: Long): Boolean {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/mute")
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
                    setBody(json.encodeToString(MuteModel(groupId, memberId, time)))
                }

                log.debug(response.bodyAsText())
                val bindResponse = json.decodeFromString<BindResponse>(response.bodyAsText())
                log.debug("禁言 => {}", bindResponse)
                return@runBlocking bindResponse.code == 0
            }
        } catch (e: Exception) {
            log.error("禁言出现异常 => {}", e.message)
            return false
        }
    }


    @OptIn(InternalAPI::class)
    override fun unmute(groupId: Long, memberId: Long): Boolean {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/unmute")
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
                    body = json.encodeToString(UnMuteModel(groupId, memberId))
                }

                log.debug(response.bodyAsText())
                val bindResponse = json.decodeFromString<BindResponse>(response.bodyAsText())
                log.debug("解除群成员禁言 => {}", bindResponse)
                return@runBlocking bindResponse.code == 0
            }
        } catch (e: Exception) {
            log.error("解除群成员禁言出现异常 => {}", e.message)
            return false
        }
    }

    @OptIn(InternalAPI::class)
    override fun kick(groupId: Long, memberId: Long, msg: String): Boolean {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/kick")
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
                    body = json.encodeToString(KickModel(groupId, memberId, msg))
                }

                log.debug(response.bodyAsText())
                val bindResponse = json.decodeFromString<BindResponse>(response.bodyAsText())
                log.debug("退出群聊 => {}", bindResponse)
                return@runBlocking bindResponse.code == 0
            }
        } catch (e: Exception) {
            log.error("退出群聊出现异常 => {}", e.message)
            return false
        }
    }

    @OptIn(InternalAPI::class)
    override fun quit(groupId: Long): Boolean {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/quit")
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
                    body = json.encodeToString(QuitGroupModel(groupId))
                }

                log.debug(response.bodyAsText())
                val bindResponse = json.decodeFromString<BindResponse>(response.bodyAsText())
                log.debug("退出群聊 => {}", bindResponse)
                return@runBlocking bindResponse.code == 0
            }
        } catch (e: Exception) {
            log.error("退出群聊出现异常 => {}", e.message)
            return false
        }
    }

    @OptIn(InternalAPI::class)
    override fun muteAll(groupId: Long): Boolean {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/muteAll")
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
                    //结构一样 故复用
                    body = json.encodeToString(QuitGroupModel(groupId))
                }

                log.debug(response.bodyAsText())
                val bindResponse = json.decodeFromString<BindResponse>(response.bodyAsText())
                log.debug("全体禁言 => {}", bindResponse)
                return@runBlocking bindResponse.code == 0
            }
        } catch (e: Exception) {
            log.error("全体禁言出现异常 => {}", e.message)
            return false
        }
    }

    @OptIn(InternalAPI::class)
    override fun unmuteAll(groupId: Long): Boolean {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/unmuteAll")
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
                    //结构一样 故复用
                    body = json.encodeToString(QuitGroupModel(groupId))
                }

                log.debug(response.bodyAsText())
                val bindResponse = json.decodeFromString<BindResponse>(response.bodyAsText())
                log.debug("解除全体禁言 => {}", bindResponse)
                return@runBlocking bindResponse.code == 0
            }
        } catch (e: Exception) {
            log.error("解除全体禁言出现异常 => {}", e.message)
            return false
        }
    }

    @OptIn(InternalAPI::class)
    override fun setEssence(groupId: Long, messageId: Long): Boolean {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/setEssence")
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
                    body = json.encodeToString(EssenceModel(groupId, messageId))
                }

                log.debug(response.bodyAsText())
                val bindResponse = json.decodeFromString<BindResponse>(response.bodyAsText())
                log.debug("设置群精华消息 => {}", bindResponse)
                return@runBlocking bindResponse.code == 0
            }
        } catch (e: Exception) {
            log.error("设置群精华消息出现异常 => {}", e.message)
            return false
        }
    }

    override fun getGroupConfig(groupId: Long): GroupConfig? {
        return try {
            runBlocking {

                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/groupConfig")
                        (if (sessionKey.isNullOrBlank()) {
                            defaultSessionStatus
                        } else {
                            sessionKey
                        })?.let { it1 ->
                            parameters.append(
                                "sessionKey", it1
                            )
                        }
                        parameters.append("target", groupId.toString())
                    }
                    contentType(ContentType.Application.Json)
                }

                log.debug(response.bodyAsText())
                val groupConfig = json.decodeFromString<GroupConfig>(response.bodyAsText())
                log.debug("获取群设置 => {}", groupConfig)
                return@runBlocking groupConfig
            }
        } catch (e: Exception) {
            log.error("获取群设置出现异常 => {}", e.message)
            return null
        }
    }

    @OptIn(InternalAPI::class)
    override fun setGroupConfig(groupConfig: GroupConfigModel): Boolean {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/groupConfig")
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
                    body = json.encodeToString(groupConfig)
                }

                log.debug(response.bodyAsText())
                val bindResponse = json.decodeFromString<BindResponse>(response.bodyAsText())
                log.debug("修改群设置 => {}", bindResponse)
                return@runBlocking bindResponse.code == 0
            }
        } catch (e: Exception) {
            log.error("修改群设置出现异常 => {}", e.message)
            return false
        }
    }

    override fun getMemberInfo(groupId: Long, memberId: Long): MemberInfo? {
        return try {
            runBlocking {

                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/memberInfo")
                        (if (sessionKey.isNullOrBlank()) {
                            defaultSessionStatus
                        } else {
                            sessionKey
                        })?.let { it1 ->
                            parameters.append(
                                "sessionKey", it1
                            )
                        }
                        parameters.append("target", groupId.toString())
                        parameters.append("memberId", memberId.toString())
                    }
                    contentType(ContentType.Application.Json)
                }

                log.debug(response.bodyAsText())
                val memberInfo = json.decodeFromString<MemberInfo>(response.bodyAsText())
                log.debug("获取群员设置 => {}", memberInfo)
                return@runBlocking memberInfo
            }
        } catch (e: Exception) {
            log.error("获取群员设置出现异常 => {}", e.message)
            return null
        }
    }

    /**
     * 群头衔需要群主权限
     */
    @OptIn(InternalAPI::class)
    override fun setMemberInfo(groupId: Long, memberId: Long, name: String?, specialTitle: String?): Boolean {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/memberInfo")
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
                    body =
                        json.encodeToString(
                            MemberInfoModel(
                                groupId,
                                memberId,
                                MemberInfoModelInfo(name, specialTitle)
                            )
                        )
                }
                log.debug(response.bodyAsText())
                val bindResponse = json.decodeFromString<BindResponse>(response.bodyAsText())
                log.debug("修改群员设置 => {}", bindResponse)
                return@runBlocking bindResponse.code == 0
            }
        } catch (e: Exception) {
            log.error("修改群员设置出现异常 => {}", e.message)
            return false
        }
    }

    @OptIn(InternalAPI::class)
    override fun setMemberAdmin(groupId: Long, memberId: Long, assign: Boolean): Boolean {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/memberAdmin")
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
                    body = json.encodeToString(MemberAdminModel(groupId, memberId, assign))
                }
                log.debug(response.bodyAsText())
                val bindResponse = json.decodeFromString<BindResponse>(response.bodyAsText())
                log.debug("修改群员管理员 => {}", bindResponse)
                return@runBlocking bindResponse.code == 0
            }
        } catch (e: Exception) {
            log.error("修改群员管理员出现异常 => {}", e.message)
            return false
        }
    }


    override fun getAnnoList(groupId: Long, offset: Long, size: Long): List<AnnoListResponseData> {
        return try {
            runBlocking {

                val response: HttpResponse = client.get {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/anno/list")
                        (if (sessionKey.isNullOrBlank()) {
                            defaultSessionStatus
                        } else {
                            sessionKey
                        })?.let { it1 ->
                            parameters.append(
                                "sessionKey", it1
                            )
                        }
                        parameters.append("id", groupId.toString())
                        parameters.append("offset", offset.toString())
                        parameters.append("size", size.toString())
                    }
                    contentType(ContentType.Application.Json)
                }
                log.debug(response.bodyAsText())
                val annoListResponse = json.decodeFromString<AnnoListResponse>(response.bodyAsText())
                log.debug("获取群公告 => {}", annoListResponse)
                return@runBlocking annoListResponse.data
            }
        } catch (e: Exception) {
            log.error("获取群公告出现异常 => {}", e.message)
            return mutableListOf()
        }
    }

    @OptIn(InternalAPI::class)
    override fun setGroupAnno(groupAnnoModel: GroupAnnoModel): GroupAnnoResponseData? {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/anno/publish")
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
                    body = json.encodeToString(groupAnnoModel)
                }
                log.debug(response.bodyAsText())
                val groupAnnoResponse = json.decodeFromString<GroupAnnoResponse>(response.bodyAsText())
                log.debug("发布群公告 => {}", groupAnnoResponse)
                return@runBlocking groupAnnoResponse.data
            }
        } catch (e: Exception) {
            log.error("发布群公告出现异常 => {}", e.message)
            return null
        }
    }

    @OptIn(InternalAPI::class)
    override fun deleteGroupAnno(groupId: Long, annoId: String): Boolean {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/anno/delete")
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
                    body = json.encodeToString(DeleteGroupAnnoModel(groupId, annoId))
                }
                log.debug(response.bodyAsText())
                val bindResponse = json.decodeFromString<BindResponse>(response.bodyAsText())
                log.debug("删除群公告 => {}", bindResponse)
                return@runBlocking bindResponse.code == 0
            }
        } catch (e: Exception) {
            log.error("删除群公告出现异常 => {}", e.message)
            return false
        }
    }

    /**
     *     operate	说明
     *
     *     0	同意入群
     *
     *     1	拒绝入群
     *
     *     2	忽略请求
     *
     *     3	拒绝入群并添加黑名单，不再接收该用户的入群申请
     *
     *     4	忽略入群并添加黑名单，不再接收该用户的入群申请
     */
    @OptIn(InternalAPI::class)
    override fun manageMemberJoin(eventId: Long, fromId: Long, groupId: Long, message: String, operate: Int): Boolean {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/resp/memberJoinRequestEvent")
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
                log.debug("用户入群申请 => {}", bindResponse)
                return@runBlocking bindResponse.code == 0
            }
        } catch (e: Exception) {
            log.error("用户入群出现异常 => {}", e.message)
            return false
        }
    }

    /**
     * operate	说明
     *
     * 0	同意邀请
     *
     * 1	拒绝邀请
     */
    @OptIn(InternalAPI::class)
    override fun manageBotInvitedJoinGroup(
        eventId: Long,
        fromId: Long,
        groupId: Long,
        message: String,
        operate: Int
    ): Boolean {
        return try {
            runBlocking {

                val response: HttpResponse = client.post {
                    url {
                        host = miraiHost
                        port = miraiPort
                        appendPathSegments("/resp/botInvitedJoinGroupRequestEvent")
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
                log.debug("Bot被邀请入群申请 => {}", bindResponse)
                return@runBlocking bindResponse.code == 0
            }
        } catch (e: Exception) {
            log.error("Bot被邀请入群申请出现异常 => {}", e.message)
            return false
        }
    }
}
