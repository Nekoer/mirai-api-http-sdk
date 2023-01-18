package com.hcyacg.message.event.group


import com.hcyacg.message.event.BotEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberLeaveEventKick(
    @SerialName("member")
    val member: Member,
    @SerialName("operator")
    val `operator`: Operator,
    @SerialName("type")
    val type: String
): BotEvent()
