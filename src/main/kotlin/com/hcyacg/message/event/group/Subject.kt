package com.hcyacg.message.event.group


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Subject(
    @SerialName("id")
    val id: Int,
    @SerialName("kind")
    val kind: String
)
