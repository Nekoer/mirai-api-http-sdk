package com.hcyacg.message.event.friend


import com.hcyacg.message.event.BotEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FriendRecallEvent(
    @SerialName("authorId")
    val authorId: Int,
    @SerialName("messageId")
    val messageId: Int,
    @SerialName("operator")
    val `operator`: Int,
    @SerialName("time")
    val time: Int,
    @SerialName("type")
    val type: String
): BotEvent()
