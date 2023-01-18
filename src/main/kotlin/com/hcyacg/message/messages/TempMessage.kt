package com.hcyacg.message.messages

import com.hcyacg.message.MiraiMessageChain
import com.hcyacg.message.MiraiMessageChainSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/16/2023 13:58
 * @Description
 **/
@Serializable
data class TempMessage(
    @SerialName("messageChain")
    val messageChain: List<@Serializable(with = MiraiMessageChainSerializer::class)  MiraiMessageChain>,
    @SerialName("sender")
    val sender: TempMessageSender,
    @SerialName("type")
    val type: String
): MiraiMessageChain()

@Serializable
data class TempMessageSender(
    @SerialName("group")
    val group: TempMessageGroup,
    @SerialName("id")
    val id: Long,
    @SerialName("joinTimestamp")
    val joinTimestamp: Long,
    @SerialName("lastSpeakTimestamp")
    val lastSpeakTimestamp: Long,
    @SerialName("memberName")
    val memberName: String,
    @SerialName("muteTimeRemaining")
    val muteTimeRemaining: Long,
    @SerialName("permission")
    val permission: String,
    @SerialName("specialTitle")
    val specialTitle: String
)

@Serializable
data class TempMessageGroup(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("permission")
    val permission: String
)
