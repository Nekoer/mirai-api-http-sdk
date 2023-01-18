package com.hcyacg

import com.hcyacg.message.MiraiMessageChain
import com.hcyacg.service.*
import io.ktor.client.*
import kotlinx.serialization.json.Json
import org.slf4j.Logger

/**
 * @Author Nekoer
 * @Date  1/16/2023 11:25
 * @Description 客户端 基类接口
 **/
interface MiraiApiBaseClient {

    var sessionKey: String?
    val log: Logger
    val defaultSessionStatus: String
        get() = "SINGLE_SESSION"

    val json: Json

    val group: GroupService
    val friend: FriendService
    val bot: BotService
    val stranger: StrangerService
    val console: ConsoleService

    val client: HttpClient

    fun verify()

    fun about(): String?
    fun messageFromId(messageId: Int, target: Long): List<MiraiMessageChain>?
}
