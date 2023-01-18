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
 * name	String	戳一戳的类型
 *
 * "Poke": 戳一戳
 *
 * "ShowLove": 比心
 *
 * "Like": 点赞
 *
 * "Heartbroken": 心碎
 *
 * "SixSixSix": 666
 *
 * "FangDaZhao": 放大招
 */
@Serializable
data class Poke(
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String
): MiraiMessageType()
