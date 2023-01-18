package com.hcyacg.message.type
import com.hcyacg.message.MiraiMessageType
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


/**
 * @Author Nekoer
 * @Date  1/16/2023 13:42
 * @Description
 **/

/**
 * faceId	Int	QQ表情编号，可选，优先高于name
 *
 * name	String	QQ表情拼音，可选
 */
@Serializable
data class Face(
    @SerialName("faceId")
    val faceId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String
): MiraiMessageType()
