package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 15:59
 * @Description
 **/
@Serializable

internal data class MuteModel(
    @SerialName("target")
    val groupId: Long,
    @SerialName("memberId")
    val memberId: Long,
    @SerialName("time")
    val time: Long?
)
