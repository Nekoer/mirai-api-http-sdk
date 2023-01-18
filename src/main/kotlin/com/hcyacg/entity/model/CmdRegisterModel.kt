package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 23:39
 * @Description
 **/
@Serializable
data class CmdRegisterModel(
    @SerialName("alias")
    val alias: List<String>,
    @SerialName("description")
    val description: String,
    @SerialName("name")
    val name: String,
    @SerialName("usage")
    val usage: String
)
