package com.hcyacg.message.event.friend
import com.hcyacg.message.event.BotEvent
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


/**
 * @Author Nekoer
 * @Date  1/16/2023 15:43
 * @Description
 **/
@Serializable
data class FriendNickChangedEvent(
    @SerialName("friend")
    val friend: Friend,
    @SerialName("from")
    val from: String,
    @SerialName("to")
    val to: String,
    @SerialName("type")
    val type: String
): BotEvent()

