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
 * id	Int	商城表情唯一标识
 *
 * name	String	表情显示名称
 *
 * 目前商城表情仅支持接收和转发，不支持构造发送
 */
@Serializable
data class MarketFace(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String
): MiraiMessageType()
