package com.hcyacg.message.type
import com.hcyacg.message.MiraiMessageType
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


/**
 * @Author Nekoer
 * @Date  1/16/2023 13:45
 * @Description
 **/
@Serializable
data class FlashImage(
    @SerialName("base64")
    val base64: String,
    @SerialName("imageId")
    val imageId: String,
    @SerialName("path")
    val path: String,
    @SerialName("type")
    val type: String,
    @SerialName("url")
    val url: String
): MiraiMessageType()
