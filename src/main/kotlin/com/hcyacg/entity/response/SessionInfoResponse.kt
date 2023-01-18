package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/15/2023 22:57
 * @Description
 **/
@Serializable
internal data class SessionInfoResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val sessionInfoResponseData: SessionInfoResponseData,
    @SerialName("msg")
    val msg: String
)

@Serializable
data class SessionInfoResponseData(
    @SerialName("qq")
    val qq: Qq,
    @SerialName("sessionKey")
    val sessionKey: String
)

@Serializable
data class Qq(
    @SerialName("id")
    val id: Long,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("remark")
    val remark: String
)
