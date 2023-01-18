package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author Nekoer
 * @Date  1/16/2023 19:31
 * @Description
 **/
@Serializable
internal class NudgeMessageResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("msg")
    val msg: String
)
