package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author Nekoer
 * @Date  1/15/2023 22:05
 * @Description
 **/
@Serializable
internal data class BindModel(
    @SerialName("sessionKey")
    private var sessionKey: String,
    @SerialName("qq")
    private var qq: Long
)
