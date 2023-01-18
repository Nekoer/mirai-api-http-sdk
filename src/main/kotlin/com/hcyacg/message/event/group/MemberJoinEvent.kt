package com.hcyacg.message.event.group


import com.hcyacg.message.event.BotEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberJoinEvent(
    @SerialName("invitor")
    val invitor: Member?,
    @SerialName("member")
    val member: Member,
    @SerialName("type")
    val type: String
): BotEvent()
