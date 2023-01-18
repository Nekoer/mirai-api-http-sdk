package com.hcyacg.message.event.other


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Client(
    @SerialName("id")
    val id: Int,
    @SerialName("platform")
    val platform: String
)
