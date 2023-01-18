package com.hcyacg.message.messages

import com.hcyacg.message.MiraiMessageChain
import com.hcyacg.message.MiraiMessageChainSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/16/2023 14:00
 * @Description
 **/
@Serializable
data class FriendSyncMessage(
    @SerialName("messageChain")
    val messageChain: List<@Serializable(with = MiraiMessageChainSerializer::class)  MiraiMessageChain>,
    @SerialName("subject")
    val subject: FriendSyncMessageSubject,
    @SerialName("type")
    val type: String
): MiraiMessageChain()

@Serializable
data class FriendSyncMessageSubject(
    @SerialName("id")
    val id: Long,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("remark")
    val remark: String
)
