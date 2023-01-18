package com.hcyacg.service

import org.slf4j.Logger

/**
 * @Author Nekoer
 * @Date  1/18/2023 00:03
 * @Description
 **/
interface StrangerService : MiraiBaseService {
    val log: Logger
    fun sendNudge(qq: Long): Boolean
}
