package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 14:07
 * @Description
 **/
@Serializable
data class UploadVoiceResponse(
    @SerialName("url")
    val url: String,
    @SerialName("voiceId")
    val voiceId: String
)
