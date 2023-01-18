package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author Nekoer
 * @Date  1/15/2023 20:45
 * @Description
 **/
@Serializable
internal data class VerifyModel(
    @SerialName("verifyKey")
    private var verifyKey: String
)
