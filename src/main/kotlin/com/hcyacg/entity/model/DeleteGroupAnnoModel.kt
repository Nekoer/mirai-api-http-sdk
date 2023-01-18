package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 23:04
 * @Description
 **/
@Serializable
data class DeleteGroupAnnoModel(
    @SerialName("id")
    val id: Long,
    @SerialName("fid")
    val fid: String
)
