package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author Nekoer
 * @Date  1/15/2023 23:10
 * @Description
 **/
@Serializable
internal class ReleaseResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("msg")
    val msg: String
)
