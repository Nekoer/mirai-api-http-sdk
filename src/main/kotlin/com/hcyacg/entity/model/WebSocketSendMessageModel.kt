package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/18/2023 11:14
 * @Description
 **/
@Serializable
data class WebSocketSendMessageModel<T>(
    @SerialName("command")
    val command: String,
    @SerialName("content")
    val content: T? = null,
    @SerialName("subCommand")
    val subCommand: String? = null,
    @SerialName("syncId")
    val syncId: Long
)
