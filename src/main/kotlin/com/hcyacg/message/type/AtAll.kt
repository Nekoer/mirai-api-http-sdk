package com.hcyacg.message.type
import com.hcyacg.message.MiraiMessageType
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


/**
 * @Author Nekoer
 * @Date  1/16/2023 13:42
 * @Description
 **/
@Serializable
data class AtAll(
    @SerialName("type")
    val type: String
): MiraiMessageType()
