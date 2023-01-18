package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 21:40
 * @Description
 **/
@Serializable
data class GroupConfig(
    @SerialName("allowMemberInvite")
    val allowMemberInvite: Boolean,
    @SerialName("anonymousChat")
    val anonymousChat: Boolean,
    @SerialName("autoApprove")
    val autoApprove: Boolean,
    @SerialName("confessTalk")
    val confessTalk: Boolean,
    @SerialName("name")
    val name: String?
)
