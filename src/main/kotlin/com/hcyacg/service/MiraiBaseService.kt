package com.hcyacg.service

import io.ktor.client.*
import kotlinx.serialization.json.Json

/**
 * @Author Nekoer
 * @Date  1/17/2023 23:56
 * @Description
 **/
interface MiraiBaseService {
    val miraiHost: String
    val miraiPort: Int
    val sessionKey: String?
    val defaultSessionStatus: String
    val client: HttpClient
    val json: Json
}
