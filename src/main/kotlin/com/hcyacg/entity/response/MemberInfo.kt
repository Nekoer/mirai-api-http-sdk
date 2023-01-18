package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 21:54
 * @Description
 **/
@Serializable
data class MemberInfo(
    @SerialName("group")
    val group: Group,
    @SerialName("id")
    val id: Long,
    @SerialName("joinTimestamp")
    val joinTimestamp: Long,
    @SerialName("lastSpeakTimestamp")
    val lastSpeakTimestamp: Long,
    @SerialName("memberName")
    val memberName: String,
    @SerialName("muteTimeRemaining")
    val muteTimeRemaining: Long,
    @SerialName("permission")
    val permission: String,
    @SerialName("specialTitle")
    val specialTitle: String
)

@Serializable
data class Group(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("permission")
    val permission: String
)
