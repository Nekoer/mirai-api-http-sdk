package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 22:59
 * @Description
 **/
@Serializable

data class GroupAnnoResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: GroupAnnoResponseData,
    @SerialName("msg")
    val msg: String
)

@Serializable
data class GroupAnnoResponseData(
    @SerialName("allConfirmed")
    val allConfirmed: Boolean,
    @SerialName("confirmedMembersCount")
    val confirmedMembersCount: Int,
    @SerialName("content")
    val content: String,
    @SerialName("fid")
    val fid: String,
    @SerialName("group")
    val group: Group,
    @SerialName("publicationTime")
    val publicationTime: Int,
    @SerialName("senderId")
    val senderId: Int
)
