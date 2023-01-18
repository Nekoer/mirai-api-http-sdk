package com.hcyacg.message.event.group


import com.hcyacg.message.event.BotEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberHonorChangeEvent(
    @SerialName("action")
    val action: String,
    @SerialName("honor")
    val honor: String,
    @SerialName("member")
    val member: Member,
    @SerialName("type")
    val type: String
): BotEvent()
