package com.hcyacg.entity.response

import com.hcyacg.message.MiraiMessageChain
import com.hcyacg.message.MiraiMessageChainSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 10:45
 * @Description
 **/
@Serializable
internal data class RoamingMessagesResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: List<@Serializable(with = MiraiMessageChainSerializer::class) MiraiMessageChain>,
    @SerialName("msg")
    val msg: String
)
