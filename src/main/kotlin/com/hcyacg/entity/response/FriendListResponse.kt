package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/16/2023 16:39
 * @Description
 **/
@Serializable
internal data class FriendListResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: List<FriendListResponseData>,
    @SerialName("msg")
    val msg: String
)

@Serializable
data class FriendListResponseData(
    @SerialName("id")
    val id: Long,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("remark")
    val remark: String
)
