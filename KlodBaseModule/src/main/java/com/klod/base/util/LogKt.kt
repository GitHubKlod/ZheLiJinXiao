package com.klod.base.util

import android.util.Log
import com.klod.base.BuildConfig

fun String.eLog(){
    if(BuildConfig.DEBUG) {
        Log.e("查看数据", this)
    }
}
fun Int.eLog(){
    if(BuildConfig.DEBUG) {
        Log.e("查看数据", "$this")
    }
}

object LogKt {

    fun eLog(content :String){
        if(BuildConfig.DEBUG){
            Log.e("查看数据",content)
        }
    }

}