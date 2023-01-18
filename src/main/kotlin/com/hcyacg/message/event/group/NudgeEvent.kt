package com.hcyacg.message.event.group


import com.hcyacg.message.event.BotEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NudgeEvent(
    @SerialName("action")
    val action: String,
    @SerialName("fromId")
    val fromId: Int,
    @SerialName("subject")
    val subject: Subject,
    @SerialName("suffix")
    val suffix: String,
    @SerialName("target")
    val target: Int,
    @SerialName("type")
    val type: String
): BotEvent()
