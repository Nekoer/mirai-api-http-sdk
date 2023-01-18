package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 21:13
 * @Description
 **/
@Serializable
internal data class KickModel(
    @SerialName("target")
    val groupId: Long,
    @SerialName("memberId")
    val memberId: Long,
    @SerialName("msg")
    val msg: String
)
