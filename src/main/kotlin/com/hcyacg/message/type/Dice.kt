package com.hcyacg.message.type
import com.hcyacg.message.MiraiMessageType
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


/**
 * @Author Nekoer
 * @Date  1/16/2023 13:49
 * @Description
 **/

/**
 * value	Int	点数
 */
@Serializable
data class Dice(
    @SerialName("type")
    val type: String,
    @SerialName("value")
    val value: Int
): MiraiMessageType()
