package com.hcyacg.message.event.group


import com.hcyacg.message.event.BotEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupAllowMemberInviteEvent(
    @SerialName("current")
    val current: Boolean,
    @SerialName("group")
    val group: Group,
    @SerialName("operator")
    val `operator`: Operator,
    @SerialName("origin")
    val origin: Boolean,
    @SerialName("type")
    val type: String
): BotEvent()
