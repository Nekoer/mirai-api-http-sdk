package com.hcyacg.message.type
import com.hcyacg.message.MiraiMessageType
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


/**
 * @Author Nekoer
 * @Date  1/16/2023 13:40
 * @Description
 **/

/**
 * id	Int	被引用回复的原消息的messageId
 *
 * groupId	Long	被引用回复的原消息所接收的群号，当为好友消息时为0
 *
 * senderId	Long	被引用回复的原消息的发送者的QQ号
 *
 * targetId	Long	被引用回复的原消息的接收者者的QQ号（或群号）
 *
 * origin	List<Origin>	被引用回复的原消息的消息链对象
 */
@Serializable
data class Quote(
    @SerialName("groupId")
    val groupId: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("origin")
    val origin: List<Origin>,
    @SerialName("senderId")
    val senderId: Int,
    @SerialName("targetId")
    val targetId: Long,
    @SerialName("type")
    val type: String
): MiraiMessageType()

@Serializable
data class Origin(
    @SerialName("text")
    val text: String,
    @SerialName("type")
    val type: String
)
