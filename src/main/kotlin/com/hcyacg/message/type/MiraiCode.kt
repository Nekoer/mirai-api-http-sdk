package com.hcyacg.message.type
import com.hcyacg.message.MiraiMessageType
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


/**
 * @Author Nekoer
 * @Date  1/16/2023 13:53
 * @Description
 **/

/**
 * code	String	MiraiCode
 */
@Serializable
data class MiraiCode(
    @SerialName("code")
    val code: String,
    @SerialName("type")
    val type: String
): MiraiMessageType()
