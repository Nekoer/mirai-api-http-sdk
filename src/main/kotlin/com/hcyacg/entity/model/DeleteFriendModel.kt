package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 15:52
 * @Description
 **/
@Serializable
internal data class DeleteFriendModel(
    @SerialName("target")
    val target: Long
)
