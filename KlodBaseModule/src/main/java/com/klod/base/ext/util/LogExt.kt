package com.klod.base.ext.util

import com.klod.base.BuildConfig
import com.orhanobut.logger.Logger

const val TAG = "JetpackMvvm"

/**
 *
 * 是否需要开启打印日志，默认打开，1.1.7-1.1.8版本是默认关闭的(1.0.0-1.1.6没有这个字段，框架在远程依赖下，直接不打印log)，但是默认关闭后很多人反馈都没有日志，好吧，我的我的
 * 根据true|false 控制网络请求日志和该框架产生的打印
 */
var jetpackMvvmLog = BuildConfig.DEBUG

private enum class LEVEL {
    V, D, I, W, E,Json
}

fun String.logV(tag: String = TAG) =
    log(LEVEL.V,  this)
fun String.logFromJson(tag: String = TAG) =
    log(LEVEL.Json,  this)
fun String.logD(tag: String = TAG) =
    log(LEVEL.D,  this)
fun String.logI(tag: String = TAG) =
    log(LEVEL.I,  this)
fun String.logW(tag: String = TAG) =
    log(LEVEL.W,  this)
fun String.logE() =
    log(LEVEL.E,  this)

private fun log(level: LEVEL, message: String?) {
    if (!jetpackMvvmLog) return
    when (level) {
        LEVEL.V -> Logger.v( message?:"打印了一个Null")
        LEVEL.D -> Logger.d( message?:"打印了一个Null")
        LEVEL.I -> Logger.i( message?:"打印了一个Null")
        LEVEL.W -> Logger.w( message?:"打印了一个Null")
        LEVEL.E -> Logger.e( message?:"打印了一个Null")
        LEVEL.Json -> Logger.json(message?:"""{"msg":"打印了一个Null"}""")
    }
}