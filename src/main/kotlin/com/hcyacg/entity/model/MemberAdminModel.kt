package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 22:11
 * @Description
 **/
@Serializable
data class MemberAdminModel(
    @SerialName("target")
    val target: Long,
    @SerialName("memberId")
    val memberId: Long,
    @SerialName("assign")
    val assign: Boolean,
)
