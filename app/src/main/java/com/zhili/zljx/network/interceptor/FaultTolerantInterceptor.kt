package com.zhili.zljx.network.interceptor

import com.klod.base.ext.util.logD
import com.klod.base.ext.util.logFromJson



import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException

/**
 * 作者　: KLOD
 * 时间　: 2023/4/14
 * 描述　: 接口容错
 */
class FaultTolerantInterceptor : Interceptor {

    @kotlin.jvm.Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        return if (response.body != null && response.body!!.contentType() != null) {
            val mediaType = response.body!!.contentType()
            var string = response.body!!.string()


            if(string.first().toString() == "[" && string.last().toString() == "]"){
                string =  """{"data":$string,"errorCode":-1,"status":1,"errorMsg":""}"""
            }else if (string.first().toString() != "{" || string.last().toString() != "}") {
                //这里的errorMsg要和NetWorkErrorBean里的判断对应 不然不会显示toast
                if (response.code == 200) {
                    string = when {
//                        string.contains("成功") ->
//                            """{"data":{},"errorCode":1,"errorMsg":"$string"}"""
//                        string=="ok"||string=="OK"||string=="Ok" ->
//                            """{"data":{},"errorCode":1,"errorMsg":"$string"}"""
                        string.contains("https:") ->
                            """{"data":{},"errorCode":-1,"status":1,"errorMsg":"${
                                string.replace(
                                    "\"",
                                    "\\\""
                                )
                            }"}"""

                        else ->
                            """{"data":{},"errorCode":-1,"status":1,"errorMsg":"$string"}"""
                    }
                } else {
                    string = """{"data":{},"errorCode":-1,"status":555,"errorMsg":"$string"}"""
                }
            }


            val responseBody = string.toResponseBody(mediaType)

            //打印日志
            "接口容错机制 Type=$mediaType  Code = ${response.code}   Url = ${response.request.url}".logD()
            string.logFromJson()

            response.newBuilder().body(responseBody).build()
        }
//        else if(response.code==204) {
//
//
//            "接口容错机制 空响应体".logD()
//
//            val responseBody =  """{"data":{},"errorCode":555,"errorMsg":"啥也没有"}"""
//                .toResponseBody("application/json; charset=utf-8".toMediaType())
//            response.newBuilder().body(responseBody).build()
////            response
//        }
        else{
            "接口容错机制 最终执行".logD()

            response

        }
    }
}