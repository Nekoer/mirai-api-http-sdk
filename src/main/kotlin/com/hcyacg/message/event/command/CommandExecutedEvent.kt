package com.hcyacg.message.event.command


import com.hcyacg.message.event.BotEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.lang.reflect.Member

@Serializable
data class CommandExecutedEvent(
    @SerialName("args")
    val args: List<Arg>,
    @SerialName("friend")
    val friend: Member?,
    @SerialName("member")
    val member: Member?,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String
): BotEvent()
