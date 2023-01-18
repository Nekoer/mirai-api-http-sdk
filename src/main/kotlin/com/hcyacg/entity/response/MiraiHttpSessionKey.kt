package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/15/2023 20:22
 * @Description session key
 **/
@Serializable
internal data class MiraiHttpSessionKey(
    @SerialName("code")
    val code: Int,
    @SerialName("session")
    val session: String
)
