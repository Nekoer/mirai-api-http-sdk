package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/16/2023 19:52
 * @Description
 **/
@Serializable
internal data class RecallModel(
    @SerialName("messageId")
    val messageId: Long,
    @SerialName("target")
    val target: Long
)
