package com.zhili.zljx.network.interceptor

import android.content.Intent
import com.klod.base.ext.util.logD
import com.zhili.zljx.app.ActivityManege
import com.zhili.zljx.app.MyApplication.Companion.appContext
import com.zhili.zljx.app.appViewModel
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.ext.startAC
import com.zhili.zljx.ui.login.LoginActivity
import com.zhili.zljx.utils.MMKVUtils


import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException

/**
 * 作者　: KLOD
 * 时间　: 2022/1/13
 * 描述　: token过期拦截器
 */
class TokenOutInterceptor : Interceptor {

//    val gson: Gson by lazy { Gson() }

    @kotlin.jvm.Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        return if (response.body != null && response.body!!.contentType() != null) {
            val mediaType = response.body!!.contentType()
            var string = response.body!!.string()

            val responseBody = string.toResponseBody(mediaType)

            //判断逻辑 模拟一下
            if (response.code == 401) {
                //如果是普通的activity话 可以直接跳转，如果是navigation中的fragment，可以发送通知跳转
                MMKVUtils.removeValueForKey(Contacts.UserInfo)
                MMKVUtils.removeValueForKey(Contacts.TokenInfo)
                MMKVUtils.putBoolean(Contacts.IsLogin,false)
                appViewModel.userInfo.postValue(null)
                appContext.startAC<LoginActivity> {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                ActivityManege.finishActivityExcept(LoginActivity::class.java)
            }
            response.newBuilder().body(responseBody).build()
        }
//        else if(response.code==204) {
//
//            "接口容错机制 空响应体token ".logD()
//            val responseBody =  """{"data":{},"errorCode":555   ,"errorMsg":"啥也没有"}"""
//                .toResponseBody("application/json; charset=utf-8".toMediaType())
//            response.newBuilder().body(responseBody).build()
////            response
//        }
        else {
            "接口容错机制 最终执行token".logD()

            response
        }
    }
}