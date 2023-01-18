package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 10:42
 * @Description
 **/
@Serializable
internal data class RoamingMessagesModel(
    @SerialName("timeStart")
    val timeStart: Long,
    @SerialName("timeEnd")
    val timeEnd: Long,
    @SerialName("target")
    val target: Long,


    )
