package com.hcyacg.message.event.other


import com.hcyacg.message.event.BotEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OtherClientOnlineEvent(
    @SerialName("client")
    val client: Client,
    @SerialName("kind")
    val kind: Int,
    @SerialName("type")
    val type: String
): BotEvent()
