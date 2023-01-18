package com.hcyacg.entity.model

import com.hcyacg.message.MiraiMessageChain
import com.hcyacg.message.MiraiMessageChainSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 23:35
 * @Description
 **/
@Serializable
data class CmdExecute(
    @SerialName("command")
    val command: List<@Serializable(with = MiraiMessageChainSerializer::class) MiraiMessageChain>,
)
