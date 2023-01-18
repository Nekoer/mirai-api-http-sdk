package com.hcyacg.message.event.group


import com.hcyacg.message.event.BotEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupRecallEvent(
    @SerialName("authorId")
    val authorId: Int,
    @SerialName("group")
    val group: Group,
    @SerialName("messageId")
    val messageId: Int,
    @SerialName("operator")
    val `operator`: Operator,
    @SerialName("time")
    val time: Int,
    @SerialName("type")
    val type: String
): BotEvent()
