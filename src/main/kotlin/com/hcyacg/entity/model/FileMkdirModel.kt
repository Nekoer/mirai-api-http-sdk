package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 12:26
 * @Description
 **/
@Serializable
data class FileMkdirModel(
    @SerialName("directoryName")
    val directoryName: String,
    @SerialName("group")
    val group: Long?,
    @SerialName("id")
    val id: String?,
    @SerialName("path")
    val path: String?,
    @SerialName("qq")
    val qq: Long?,
    @SerialName("target")
    val target: Int?
)
