package com.hcyacg.service

import com.hcyacg.entity.response.FriendListResponseData
import com.hcyacg.entity.response.FriendProFile
import com.hcyacg.entity.response.UploadImageResponse
import com.hcyacg.message.MiraiMessageChain
import org.slf4j.Logger
import java.io.File

/**
 * @Author Nekoer
 * @Date  1/18/2023 00:03
 * @Description
 **/
interface FriendService : MiraiBaseService {
    val log: Logger
    fun friendList(): List<FriendListResponseData>
    fun friendProfile(target: Long): FriendProFile?
    fun sendMessage(messageChain: List<MiraiMessageChain>, target: Long): Long
    fun sendMessageWithQuote(messageChain: List<MiraiMessageChain>, target: Long, messageId: Long): Long
    fun sendNudge(qq: Long): Boolean
    fun recall(messageId: Long, friend: Long): Boolean
    fun roamingMessages(timeStart: Long, timeEnd: Long, friendId: Long): List<MiraiMessageChain>
    fun uploadImage(img: File): UploadImageResponse?
    fun deleteFriend(friendId: Long): Boolean
    fun manageNewFriend(eventId: Long, fromId: Long, groupId: Long, message: String, operate: Int): Boolean
}
