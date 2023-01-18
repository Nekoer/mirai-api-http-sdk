package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 10:51
 * @Description
 **/

@Serializable
data class FileListModel(
    @SerialName("group")
    val group: Long?,
    @SerialName("id")
    val id: String?,
    @SerialName("offset")
    val offset: Long?,
    @SerialName("path")
    val path: String?,
    @SerialName("qq")
    val qq: Long?,
    @SerialName("size")
    val size: Long?,
    @SerialName("target")
    val target: Long?,
    @SerialName("withDownloadInfo")
    val withDownloadInfo: Boolean?
)
