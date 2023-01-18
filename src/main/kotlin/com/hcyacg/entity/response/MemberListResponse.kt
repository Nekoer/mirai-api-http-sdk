package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/16/2023 16:48
 * @Description
 **/
@Serializable
internal data class MemberListResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: List<MemberListResponseData>,
    @SerialName("msg")
    val msg: String
)

@Serializable
data class MemberListResponseData(
    @SerialName("group")
    val group: MemberListResponseDataGroup,
    @SerialName("id")
    val id: Long,
    @SerialName("joinTimestamp")
    val joinTimestamp: Int,
    @SerialName("lastSpeakTimestamp")
    val lastSpeakTimestamp: Int,
    @SerialName("memberName")
    val memberName: String,
    @SerialName("muteTimeRemaining")
    val muteTimeRemaining: Int,
    @SerialName("permission")
    val permission: String,
    @SerialName("specialTitle")
    val specialTitle: String
)

@Serializable
data class MemberListResponseDataGroup(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("permission")
    val permission: String
)
