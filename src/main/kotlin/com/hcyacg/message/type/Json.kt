package com.hcyacg.message.type
import com.hcyacg.message.MiraiMessageType
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


/**
 * @Author Nekoer
 * @Date  1/16/2023 13:47
 * @Description
 **/

/**
 * json	String	Json文本
 */
@Serializable
data class Json(
    @SerialName("json")
    val json: String,
    @SerialName("type")
    val type: String
): MiraiMessageType()
