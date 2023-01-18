package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/16/2023 16:45
 * @Description
 **/
@Serializable
internal data class GroupListResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: List<GroupListResponseData>,
    @SerialName("msg")
    val msg: String
)

@Serializable
data class GroupListResponseData(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("permission")
    val permission: String
)
