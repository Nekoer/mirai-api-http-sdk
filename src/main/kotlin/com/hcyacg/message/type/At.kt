package com.hcyacg.message.type
import com.hcyacg.message.MiraiMessageType
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


/**
 * @Author Nekoer
 * @Date  1/16/2023 13:41
 * @Description
 **/

/**
 * target	Long	群员QQ号
 *
 * dispaly	String	At时显示的文字，发送消息时无效，自动使用群名片
 */
@Serializable
data class At(
    @SerialName("display")
    val display: String,
    @SerialName("target")
    val target: Int,
    @SerialName("type")
    val type: String
): MiraiMessageType()
