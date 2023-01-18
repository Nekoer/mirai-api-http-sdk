package com.hcyacg.message.type

import com.hcyacg.message.MiraiMessageType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/16/2023 13:43
 * @Description
 **/

/**
 * imageId	String	图片的imageId，群图片与好友图片格式不同。不为空时将忽略url属性
 *
 * url	String	图片的URL，发送时可作网络图片的链接；接收时为腾讯图片服务器的链接，可用于图片下载
 *
 * path	String	图片的路径，发送本地图片，路径相对于 JVM 工作路径（默认是当前路径，可通过 -Duser.dir=...指定），也可传入绝对路径。
 *
 * base64	String	图片的 Base64 编码
 */
@Serializable
data class Image(
    @SerialName("base64")
    val base64: String?,
    @SerialName("imageId")
    val imageId: String,
    @SerialName("path")
    val path: String,
    @SerialName("type")
    val type: String,
    @SerialName("url")
    val url: String
): MiraiMessageType()
