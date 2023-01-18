package com.hcyacg.entity.model

import com.hcyacg.message.MiraiMessageChain
import com.hcyacg.message.MiraiMessageChainSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author Nekoer
 * @Date  1/16/2023 19:17
 * @Description
 **/
internal data class GroupTempMessageModel(
    @SerialName("messageChain")
    val messageChain: List<@Serializable(with = MiraiMessageChainSerializer::class) MiraiMessageChain>,
    @SerialName("qq")
    val qq: Long,
    @SerialName("group")
    val group: Long,
    @SerialName("quote")
    val quote: Long?
)
