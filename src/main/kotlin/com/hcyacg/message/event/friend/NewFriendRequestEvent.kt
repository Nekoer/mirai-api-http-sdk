package com.hcyacg.message.event.friend


import com.hcyacg.message.event.BotEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewFriendRequestEvent(
    @SerialName("eventId")
    val eventId: Int,
    @SerialName("fromId")
    val fromId: Int,
    @SerialName("groupId")
    val groupId: Int,
    @SerialName("message")
    val message: String,
    @SerialName("nick")
    val nick: String,
    @SerialName("type")
    val type: String
): BotEvent()
