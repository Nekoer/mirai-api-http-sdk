package com.hcyacg.message.event.command


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Arg(
    @SerialName("text")
    val text: String,
    @SerialName("type")
    val type: String
)
