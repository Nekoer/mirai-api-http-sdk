package com.hcyacg.service

import com.hcyacg.entity.model.CmdRegisterModel
import com.hcyacg.message.MiraiMessageChain
import org.slf4j.Logger

/**
 * @Author Nekoer
 * @Date  1/18/2023 00:03
 * @Description
 **/
interface ConsoleService : MiraiBaseService {

    val log: Logger
    fun cmdExecute(messageChain: List<MiraiMessageChain>): Boolean

    fun cmdRegister(cmdRegisterModel: CmdRegisterModel): Boolean
}
