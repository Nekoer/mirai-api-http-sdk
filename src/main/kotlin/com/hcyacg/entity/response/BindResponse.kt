package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/15/2023 22:07
 * @Description
 **/
@Serializable
internal data class BindResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("msg")
    val msg: String
)
