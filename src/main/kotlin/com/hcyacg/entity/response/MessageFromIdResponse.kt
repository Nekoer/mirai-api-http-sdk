package com.hcyacg.entity.response

import com.hcyacg.message.MiraiMessageChain
import com.hcyacg.message.MiraiMessageChainSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/16/2023 16:33
 * @Description
 **/
@Serializable
internal data class MessageFromIdResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: MessageFromIdResponseData,
    @SerialName("msg")
    val msg: String
)

@Serializable
internal data class MessageFromIdResponseData(
    @SerialName("messageChain")
    val messageChain: List<@Serializable(with = MiraiMessageChainSerializer::class) MiraiMessageChain>,
    @SerialName("sender")
    val sender: Sender,
    @SerialName("type")
    val type: String
)


@Serializable
internal data class Sender(
    @SerialName("id")
    val id: Long,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("remark")
    val remark: String
)
