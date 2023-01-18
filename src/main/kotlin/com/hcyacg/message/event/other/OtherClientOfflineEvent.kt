package com.hcyacg.message.event.other


import com.hcyacg.message.event.BotEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OtherClientOfflineEvent(
    @SerialName("client")
    val client: Client,
    @SerialName("type")
    val type: String
): BotEvent()
