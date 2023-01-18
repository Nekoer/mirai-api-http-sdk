package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 21:56
 * @Description
 **/
@Serializable
data class MemberInfoModel(
    @SerialName("target")
    val target: Long,
    @SerialName("memberId")
    val memberId: Long,
    @SerialName("info")
    val info: MemberInfoModelInfo,
)

@Serializable
data class MemberInfoModelInfo(
    @SerialName("name")
    val name: String? = "",
    @SerialName("specialTitle")
    val specialTitle: String? = ""
)
