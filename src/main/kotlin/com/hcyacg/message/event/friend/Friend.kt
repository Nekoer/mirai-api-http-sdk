package com.hcyacg.message.event.friend

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author Nekoer
 * @Date  1/16/2023 15:45
 * @Description
 **/
@Serializable
data class Friend(
    @SerialName("id")
    val id: Int,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("remark")
    val remark: String
)
