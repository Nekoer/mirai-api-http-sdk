package com.hcyacg.message.messages

import com.hcyacg.message.MiraiMessageChain
import com.hcyacg.message.MiraiMessageChainSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/16/2023 14:01
 * @Description
 **/
@Serializable
data class GroupSyncMessage(
    @SerialName("messageChain")
    val messageChain: List< @Serializable(with = MiraiMessageChainSerializer::class)  MiraiMessageChain>,
    @SerialName("subject")
    val subject: GroupSyncMessageSubject,
    @SerialName("type")
    val type: String
): MiraiMessageChain()

@Serializable
data class GroupSyncMessageSubject(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("permission")
    val permission: String
)
