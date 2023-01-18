package com.hcyacg.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @Author Nekoer
 * @Date  1/17/2023 10:52
 * @Description
 **/
@Serializable
internal data class FileListResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: List<FileListResponseData>,
    @SerialName("msg")
    val msg: String
)

@Serializable
data class FileListResponseData(
    @SerialName("contact")
    val contact: Contact,
    @SerialName("downloadInfo")
    val downloadInfo: DownloadInfo?,
    @SerialName("id")
    val id: String,
    @SerialName("isDictionary")
    val isDictionary: Boolean,
    @SerialName("isDirectory")
    val isDirectory: Boolean,
    @SerialName("isFile")
    val isFile: Boolean,
    @SerialName("lastModifyTime")
    val lastModifyTime: Long?,
    @SerialName("md5")
    val md5: String?,
    @SerialName("name")
    val name: String,
    @SerialName("parent")
    val parent: FileListResponseData?,
    @SerialName("path")
    val path: String,
    @SerialName("sha1")
    val sha1: String?,
    @SerialName("size")
    val size: Long,
    @SerialName("uploadTime")
    val uploadTime: Long?,
    @SerialName("uploaderId")
    val uploaderId: Long?
)

@Serializable
data class Contact(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("permission")
    val permission: String
)

@Serializable
data class DownloadInfo(
    @SerialName("downloadTimes")
    val downloadTimes: Long,
    @SerialName("lastModifyTime")
    val lastModifyTime: Long,
    @SerialName("md5")
    val md5: String,
    @SerialName("sha1")
    val sha1: String,
    @SerialName("uploadTime")
    val uploadTime: Long,
    @SerialName("uploaderId")
    val uploaderId: Long,
    @SerialName("url")
    val url: String
)
