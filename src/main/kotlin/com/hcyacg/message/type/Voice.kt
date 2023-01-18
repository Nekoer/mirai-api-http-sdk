package com.hcyacg.message.type
import com.hcyacg.message.MiraiMessageType
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


/**
 * @Author Nekoer
 * @Date  1/16/2023 13:45
 * @Description
 **/

/**
 * voiceId	String	语音的voiceId，不为空时将忽略url属性
 *
 * url	String	语音的URL，发送时可作网络语音的链接；接收时为腾讯语音服务器的链接，可用于语音下载
 *
 * path	String	语音的路径，发送本地语音，路径相对于 JVM 工作路径（默认是当前路径，可通过 -Duser.dir=...指定），也可传入绝对路径。
 *
 * base64	String	语音的 Base64 编码
 *
 * length	Long	返回的语音长度, 发送消息时可以不传
 *
 * 三个参数任选其一，出现多个参数时，按照voiceId > url > path > base64的优先级
 */
@Serializable
data class Voice(
    @SerialName("base64")
    val base64: String,
    @SerialName("length")
    val length: Int,
    @SerialName("path")
    val path: String,
    @SerialName("type")
    val type: String,
    @SerialName("url")
    val url: String,
    @SerialName("voiceId")
    val voiceId: String
): MiraiMessageType()
