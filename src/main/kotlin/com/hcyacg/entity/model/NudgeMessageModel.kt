package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/16/2023 19:22
 * @Description
 **/
@Serializable
internal data class NudgeMessageModel(
    @SerialName("kind")
    val kind: String,
    @SerialName("subject")
    val subject: Long,
    @SerialName("target")
    val target: Long
)

@Serializable
internal enum class NudgeKind(
    val value: String,
) {
    @SerialName("GROUP")
    GROUP("GROUP"),

    @SerialName("STRANGER")
    STRANGER("STRANGER"),

    @SerialName("FRIEND")
    FRIEND("FRIEND"),
}
