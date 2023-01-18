package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author Nekoer
 * @Date  1/16/2023 19:55
 * @Description
 **/
@Serializable
internal class RecallResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("msg")
    val msg: String
)
