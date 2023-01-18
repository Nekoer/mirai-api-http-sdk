package com.hcyacg.message.type
import com.hcyacg.message.MiraiMessageChain
import com.hcyacg.message.MiraiMessageChainSerializer
import com.hcyacg.message.MiraiMessageType
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


/**
 * @Author Nekoer
 * @Date  1/16/2023 13:50
 * @Description
 **/

/**
 * nodeList	object	消息节点
 *
 * senderId	Long	发送人QQ号
 *
 * time	Int	发送时间
 *
 * senderName	String	显示名称
 *
 * messageChain	Array	消息数组
 *
 * messageId	Int	可以只使用消息messageId，从当前对话上下文缓存中读取一条消息作为节点
 *
 * messageRef	object	引用缓存中其他对话上下文的消息作为节点
 *
 * messageRef.messageId	Int	引用的 messageId
 *
 * messageRef.target	Int	引用的上下文目标，群号、好友账号
 *
 * (senderId, time, senderName, messageChain), messageId, messageRef 是三种不同构造引用节点的方式，选其中一个/组传参即可
 */
@Serializable
data class ForwardMessage(
    @SerialName("nodeList")
    val nodeList: List<Node>,
    @SerialName("type")
    val type: String
): MiraiMessageType()

@Serializable
data class Node(
    @SerialName("messageChain")
    val messageChain: List<@Serializable(with = MiraiMessageChainSerializer::class)MiraiMessageChain>,
    @SerialName("messageId")
    val messageId: Int,
    @SerialName("messageRef")
    val messageRef: MessageRef,
    @SerialName("senderId")
    val senderId: Int,
    @SerialName("senderName")
    val senderName: String,
    @SerialName("time")
    val time: Int
)

@Serializable
data class MessageRef(
    @SerialName("messageId")
    val messageId: Int,
    @SerialName("target")
    val target: Int
)
