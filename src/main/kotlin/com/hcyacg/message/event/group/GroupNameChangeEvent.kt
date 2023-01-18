package com.hcyacg.message.event.group


import com.hcyacg.message.event.BotEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupNameChangeEvent(
    @SerialName("current")
    val current: String,
    @SerialName("group")
    val group: Group,
    @SerialName("operator")
    val `operator`: Operator,
    @SerialName("origin")
    val origin: String,
    @SerialName("type")
    val type: String
): BotEvent()
