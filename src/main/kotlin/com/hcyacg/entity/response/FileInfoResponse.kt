package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 12:23
 * @Description
 **/
@Serializable
internal data class FileInfoResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: FileListResponseData,
    @SerialName("msg")
    val msg: String
)
