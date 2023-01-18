package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 21:28
 * @Description
 **/
@Serializable
internal data class QuitGroupModel(
    @SerialName("target")
    val target: Long
)
