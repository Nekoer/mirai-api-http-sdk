package com.hcyacg.config


import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ch.qos.logback.classic.Level


/**
 * @Author Nekoer
 * @Date  1/16/2023 00:17
 * @Description
 **/
open class LogLevelConfig {

    companion object{
        /**
         * 设置全局Logger 动态等级
         */
        fun setLevel(level: Level) {
            val rootLogger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as ch.qos.logback.classic.Logger
            rootLogger.level = level
        }
    }


}
