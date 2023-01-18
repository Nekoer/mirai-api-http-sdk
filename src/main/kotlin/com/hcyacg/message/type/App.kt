package com.hcyacg.message.type
import com.hcyacg.message.MiraiMessageType
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


/**
 * @Author Nekoer
 * @Date  1/16/2023 13:48
 * @Description
 **/

/**
 * content	String	内容
 */
@Serializable
data class App(
    @SerialName("content")
    val content: String,
    @SerialName("type")
    val type: String
): MiraiMessageType()
