package com.hcyacg.message.type
import com.hcyacg.message.MiraiMessageType
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


/**
 * @Author Nekoer
 * @Date  1/16/2023 13:43
 * @Description
 **/
/**
 * text	String	文字消息
 */
@Serializable
data class Plain(
    @SerialName("text")
    val text: String,
    @SerialName("type")
    val type: String
): MiraiMessageType()
