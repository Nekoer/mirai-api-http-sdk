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
 * xml	String	XML文本
 */
@Serializable
data class Xml(
    @SerialName("type")
    val type: String,
    @SerialName("xml")
    val xml: String
): MiraiMessageType()
