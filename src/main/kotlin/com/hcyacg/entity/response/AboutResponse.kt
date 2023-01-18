package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/16/2023 16:21
 * @Description
 **/
@Serializable
internal data class AboutResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: AboutResponseData,
    @SerialName("msg")
    val msg: String
)

@Serializable
data class AboutResponseData(
    @SerialName("version")
    val version: String
)
