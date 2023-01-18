package com.hcyacg.message.type
import com.hcyacg.message.MiraiMessageType
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


/**
 * @Author Nekoer
 * @Date  1/16/2023 13:52
 * @Description
 **/

/**
 * id	String	文件识别id
 *
 * name	String	文件名
 *
 * size	Long	文件大小
 */
@Serializable
data class File(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("size")
    val size: Int,
    @SerialName("type")
    val type: String
): MiraiMessageType()
