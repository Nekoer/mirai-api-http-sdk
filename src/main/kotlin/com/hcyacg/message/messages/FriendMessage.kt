package com.hcyacg.message.messages

import com.hcyacg.message.MiraiMessageChain
import com.hcyacg.message.MiraiMessageChainSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/16/2023 13:38
 * @Description
 **/
@Serializable
data class FriendMessage(
    @SerialName("messageChain")
    val messageChain: List<@Serializable(with = MiraiMessageChainSerializer::class) MiraiMessageChain>,
    @SerialName("sender")
    val sender: FriendMessageSender,
    @SerialName("type")
    val type: String
): MiraiMessageChain()

@Serializable
data class FriendMessageSender(
    @SerialName("id")
    val id: Long,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("remark")
    val remark: String
)
