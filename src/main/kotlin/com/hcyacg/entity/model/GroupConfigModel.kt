package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 21:46
 * @Description
 **/
@Serializable
data class GroupConfigModel(
    @SerialName("config")
    val config: GroupConfigModelConfig,
    @SerialName("target")
    val target: Int
)

@Serializable
data class GroupConfigModelConfig(
    @SerialName("allowMemberInvite")
    val allowMemberInvite: Boolean,
    @SerialName("anonymousChat")
    val anonymousChat: Boolean,
    @SerialName("autoApprove")
    val autoApprove: Boolean,
    @SerialName("confessTalk")
    val confessTalk: Boolean,
    @SerialName("name")
    val name: String
)
