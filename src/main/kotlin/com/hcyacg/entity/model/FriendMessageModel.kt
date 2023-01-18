package com.hcyacg.entity.model

import com.hcyacg.message.MiraiMessageChain
import com.hcyacg.message.MiraiMessageChainSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/16/2023 18:48
 * @Description
 **/
@Serializable
internal data class FriendMessageModel(
    @SerialName("messageChain")
    val messageChain: List<@Serializable(with = MiraiMessageChainSerializer::class) MiraiMessageChain>,
    @SerialName("target")
    val target: Long,
    @SerialName("quote")
    val quote: Long?
)
