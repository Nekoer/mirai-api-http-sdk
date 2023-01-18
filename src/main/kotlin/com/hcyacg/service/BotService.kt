package com.hcyacg.service

import com.hcyacg.entity.response.BotProFile
import com.hcyacg.entity.response.UserProfile
import org.slf4j.Logger

/**
 * @Author Nekoer
 * @Date  1/18/2023 00:02
 * @Description
 **/
interface BotService : MiraiBaseService {
    val log: Logger

    fun botList(): List<Long>

    fun botProfile(): BotProFile?

    fun userProfile(target: Long): UserProfile?
}
