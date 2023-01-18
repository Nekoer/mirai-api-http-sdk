package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 23:17
 * @Description
 **/
@Serializable
data class ManageNewFriendModel(
    @SerialName("eventId")
    val eventId: Long,
    @SerialName("fromId")
    val fromId: Long,
    @SerialName("groupId")
    val groupId: Long,
    @SerialName("message")
    val message: String,
    @SerialName("operate")
    val operate: Int
)
