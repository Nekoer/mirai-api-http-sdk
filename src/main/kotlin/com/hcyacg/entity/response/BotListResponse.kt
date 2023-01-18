package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/16/2023 16:28
 * @Description
 **/
@Serializable
internal data class BotListResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: List<Long>,
    @SerialName("msg")
    val msg: String
)
