package com.klod.base.network

import com.google.gson.Gson
import com.klod.base.ext.util.logE
import retrofit2.HttpException

/**
 * String -> Bean
 */
private fun <T> String.fromJson(bean: Class<T>): T = Gson().fromJson(this, bean)

/**
 * 作者　: KLOD
 * 时间　: 2023/4/17
 * 描述　:自定义错误信息异常
 */
class AppException : Exception {

    var errorMsg: String //错误消息
    var errCode: Int = 0 //错误码
    var errorLog: String? //错误日志
    var throwable: Throwable? = null

    constructor(
        errCode: Int,
        error: String?,
        errorLog: String? = "",
        throwable: Throwable? = null
    ) : super(error) {


        this.errorMsg = if (throwable is HttpException) {
            var err = throwable.response()?.errorBody()?.string() ?: ""
//            "$err ".logE()
            if (err.isEmpty() || err.first().toString() != "{" || err.last().toString() != "}") {
                """{"data":{},"errorCode":555,"errorMsg":"请求失败，请稍后再试"}""".fromJson(
                    NetWorkErrorBean::class.java
                ).errMsg

            } else {
                try {
                    err.fromJson(NetWorkErrorBean::class.java).errMsg ?: (error
                        ?: "请求失败，请稍后再试")
                }catch (e:Exception){
                    """{"data":{},"errorCode":555,"errorMsg":"请求失败，请稍后再试"}"""
                }
            }

//            throwable.response()?.errorBody()?.string()?.fromJson(NetWorkErrorBean::class.java)?.errMsg?:(error?:"请求失败，请稍后再试")
        } else {
//            "不是http ".logE()

            error ?: "请求失败，请稍后再试"
        }
        this.errCode = errCode
        this.errorLog = errorLog ?: this.errorMsg
        this.throwable = throwable
    }

    constructor(error: Error, e: Throwable?) {
        errCode = error.getKey()
        errorMsg = if (e is HttpException) {

            val err = e.response()?.errorBody()?.string() ?: ""
            "$err ".logE()
            if (err.isEmpty() || err.first().toString() != "{" || err.last().toString() != "}") {
                """{"data":{},"errorCode":555,"errorMsg":"请求失败，请稍后再试"}""".fromJson(
                    NetWorkErrorBean::class.java
                ).errMsg ?: error.getValue()

            } else {
                try {
                    err.fromJson(NetWorkErrorBean::class.java).errMsg ?: error.getValue()
                }catch (e:Exception){
                    """{"data":{},"errorCode":555,"errorMsg":"请求失败，请稍后再试"}"""
                }
            }

        } else {
//            "不是http ".logE()

            error.getValue()
        }

        errorLog = e?.message
        throwable = e
    }
}