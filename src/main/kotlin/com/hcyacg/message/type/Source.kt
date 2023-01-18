package com.hcyacg.message.type
import com.hcyacg.message.MiraiMessageType
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


/**
 * @Author Nekoer
 * @Date  1/16/2023 13:39
 * @Description
 **/
@Serializable
data class Source(
    /**
     * 消息的识别号，用于引用回复（Source类型永远为chain的第一个元素）
     */
    @SerialName("id")
    val id: Int,
    /**
     * 时间戳
     */
    @SerialName("time")
    val time: Int,
    @SerialName("type")
    val type: String
): MiraiMessageType()
