package com.hcyacg.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 22:49
 * @Description
 **/
@Serializable
data class GroupAnnoModel(
    @SerialName("content")
    val content: String,
    @SerialName("imageUrl")
    val imageUrl: String? = null,
    @SerialName("imagePath")
    val imagePath: String? = null,
    @SerialName("imageBase64")
    val imageBase64: String? = null,
    @SerialName("pinned")
    val pinned: Boolean,
    @SerialName("sendToNewMember")
    val sendToNewMember: Boolean,
    @SerialName("showEditCard")
    val showEditCard: Boolean,
    @SerialName("showPopup")
    val showPopup: Boolean,
    @SerialName("requireConfirmation")
    val requireConfirmation: Boolean,
    @SerialName("target")
    val groupId: Long
)
