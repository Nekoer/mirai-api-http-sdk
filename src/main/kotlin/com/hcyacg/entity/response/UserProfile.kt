package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/16/2023 18:14
 * @Description
 **/
@Serializable
data class UserProfile(
    @SerialName("age")
    val age: Int,
    @SerialName("email")
    val email: String,
    @SerialName("level")
    val level: Int,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("sex")
    val sex: String,
    @SerialName("sign")
    val sign: String
)
