package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 14:01
 * @Description
 **/
@Serializable
data class UploadImageResponse(
    @SerialName("imageId")
    val imageId: String,
    @SerialName("url")
    val url: String
)
