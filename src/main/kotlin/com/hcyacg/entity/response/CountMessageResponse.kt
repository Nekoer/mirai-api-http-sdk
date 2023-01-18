package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author Nekoer
 * @Date  1/16/2023 00:43
 * @Description
 **/
@Serializable
internal data class CountMessageResponse(
    @SerialName("code")
    var code: Int,
    @SerialName("msg")
    var msg: String,
    @SerialName("data")
    var data: Int
)
