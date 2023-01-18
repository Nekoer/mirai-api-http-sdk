package com.hcyacg.message.event.bot
import com.hcyacg.message.event.BotEvent
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


/**
 * @Author Nekoer
 * @Date  1/16/2023 15:40
 * @Description
 **/
@Serializable
data class BotOnlineEvent(
    @SerialName("qq")
    val qq: Int,
    @SerialName("type")
    val type: String
): BotEvent()
