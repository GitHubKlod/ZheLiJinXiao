package com.zhili.zljx.network.interceptor


import com.zhili.zljx.utils.KlodUtils
import com.zhili.zljx.utils.MMKVUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * 自定义头部参数拦截器，传入heads
 */
class MyHeadInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val token = MMKVUtils.getToken()
        builder.addHeader("authorization", "${token?.token_type} ${token?.access_token}").build()
//        builder.addHeader("Content-Type", "application/x-www-form-urlencoded").build()
//        builder.addHeader("Content-Type", "application/json; charset=utf-8").build()
        builder.addHeader("device", "Android").build()
        builder.addHeader("api-version", "1.0").build()
        //随机数
        builder.addHeader("requestId", KlodUtils.getRandomStringByTimestamp(System.currentTimeMillis())).build()


        return chain.proceed(builder.build())
    }

}