package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 21:36
 * @Description
 **/
@Serializable
internal data class EssenceModel(
    @SerialName("target")
    val groupId: Long,
    @SerialName("messageId")
    val messageId: Long
)
