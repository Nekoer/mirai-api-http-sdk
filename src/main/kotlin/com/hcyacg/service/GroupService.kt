package com.hcyacg.service

import com.hcyacg.entity.model.*
import com.hcyacg.entity.response.*
import com.hcyacg.message.MiraiMessageChain
import org.slf4j.Logger
import java.io.File

/**
 * @Author Nekoer
 * @Date  1/18/2023 00:03
 * @Description
 **/
interface GroupService : MiraiBaseService {

    val log: Logger
    fun groupList(): List<GroupListResponseData>
    fun memberList(target: Long): List<MemberListResponseData>
    fun memberProfile(target: Long, memberId: Long): MemberProfile?
    fun sendMessage(messageChain: List<MiraiMessageChain>, target: Long): Long
    fun sendMessageWithQuote(messageChain: List<MiraiMessageChain>, target: Long, messageId: Long): Long
    fun sendTempMessage(messageChain: List<MiraiMessageChain>, qq: Long, group: Long): Long
    fun sendTempMessageWithQuote(messageChain: List<MiraiMessageChain>, qq: Long, group: Long, messageId: Long): Long
    fun sendNudge(group: Long, qq: Long): Boolean
    fun recall(messageId: Long, group: Long): Boolean
    fun fileList(fileListModel: FileListModel): List<FileListResponseData>
    fun fileInfo(fileInfoModel: FileInfoModel): FileListResponseData?
    fun mkdirFileFolder(fileMkdirModel: FileMkdirModel): FileListResponseData?
    fun deleteFile(deleteFileModel: DeleteFileModel): Boolean

    //TODO 重命名文件 [POST] /file/rename
    //TODO 移动文件 [POST] /file/move
    fun uploadImage(img: File): UploadImageResponse?
    fun uploadTempGroupImage(img: File): UploadImageResponse?
    fun uploadVoice(voice: File): UploadVoiceResponse?
    fun uploadFile(target: Long, path: String, file: File): FileListResponseData?
    fun mute(groupId: Long, memberId: Long, time: Long): Boolean
    fun unmute(groupId: Long, memberId: Long): Boolean
    fun kick(groupId: Long, memberId: Long, msg: String): Boolean
    fun quit(groupId: Long): Boolean
    fun muteAll(groupId: Long): Boolean
    fun unmuteAll(groupId: Long): Boolean
    fun setEssence(groupId: Long, messageId: Long): Boolean
    fun getGroupConfig(groupId: Long): GroupConfig?
    fun setGroupConfig(groupConfig: GroupConfigModel): Boolean
    fun getMemberInfo(groupId: Long, memberId: Long): MemberInfo?
    fun setMemberInfo(groupId: Long, memberId: Long, name: String?, specialTitle: String?): Boolean
    fun setMemberAdmin(groupId: Long, memberId: Long, assign: Boolean): Boolean
    fun getAnnoList(groupId: Long, offset: Long, size: Long = 10): List<AnnoListResponseData>
    fun setGroupAnno(groupAnnoModel: GroupAnnoModel): GroupAnnoResponseData?
    fun deleteGroupAnno(groupId: Long, annoId: String): Boolean
    fun manageMemberJoin(eventId: Long, fromId: Long, groupId: Long, message: String, operate: Int): Boolean
    fun manageBotInvitedJoinGroup(eventId: Long, fromId: Long, groupId: Long, message: String, operate: Int): Boolean

}
