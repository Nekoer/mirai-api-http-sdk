package com.hcyacg.message.event.group


import com.hcyacg.message.event.BotEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupAllowConfessTalkEvent(
    @SerialName("current")
    val current: Boolean,
    @SerialName("group")
    val group: Group,
    @SerialName("isByBot")
    val isByBot: Boolean,
    @SerialName("origin")
    val origin: Boolean,
    @SerialName("type")
    val type: String
): BotEvent()
