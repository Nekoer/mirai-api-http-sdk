package com.hcyacg.entity.response

import com.hcyacg.message.MiraiMessageChain
import com.hcyacg.message.MiraiMessageChainSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/16/2023 14:08
 * @Description
 **/
@Serializable
internal data class MessageResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: List<@Serializable(with = MiraiMessageChainSerializer::class) MiraiMessageChain>,
    @SerialName("msg")
    val msg: String
)
