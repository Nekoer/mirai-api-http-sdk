package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/16/2023 18:50
 * @Description
 **/
@Serializable
internal data class SendMessageResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("messageId")
    val messageId: Long,
    @SerialName("msg")
    val msg: String
)
