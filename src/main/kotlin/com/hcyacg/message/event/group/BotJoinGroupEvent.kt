package com.hcyacg.message.event.group


import com.hcyacg.message.event.BotEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BotJoinGroupEvent(
    @SerialName("group")
    val group: Group,
    @SerialName("invitor")
    val invitor: Member?,
    @SerialName("type")
    val type: String
): BotEvent()
