package com.hcyacg.message.type
import com.hcyacg.message.MiraiMessageType
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


/**
 * @Author Nekoer
 * @Date  1/16/2023 13:49
 * @Description
 **/

/**
 * kind	String	类型
 *
 * title	String	标题
 *
 * summary	String	概括
 *
 * jumpUrl	String	跳转路径
 *
 * pictureUrl	String	封面路径
 *
 * musicUrl	String	音源路径
 *
 * brief	String	简介
 */
@Serializable
data class MusicShare(
    @SerialName("brief")
    val brief: String,
    @SerialName("jumpUrl")
    val jumpUrl: String,
    @SerialName("kind")
    val kind: String,
    @SerialName("musicUrl")
    val musicUrl: String,
    @SerialName("pictureUrl")
    val pictureUrl: String,
    @SerialName("summary")
    val summary: String,
    @SerialName("title")
    val title: String,
    @SerialName("type")
    val type: String
): MiraiMessageType()
