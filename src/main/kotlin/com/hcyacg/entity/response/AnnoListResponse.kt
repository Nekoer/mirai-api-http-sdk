package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 22:46
 * @Description
 **/
@Serializable
data class AnnoListResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: List<AnnoListResponseData>,
    @SerialName("msg")
    val msg: String
)

@Serializable
data class AnnoListResponseData(
    @SerialName("allConfirmed")
    val allConfirmed: Boolean,
    @SerialName("confirmedMembersCount")
    val confirmedMembersCount: Long,
    @SerialName("content")
    val content: String,
    @SerialName("fid")
    val fid: String,
    @SerialName("group")
    val group: Group,
    @SerialName("publicationTime")
    val publicationTime: Long,
    @SerialName("senderId")
    val senderId: Long
)
