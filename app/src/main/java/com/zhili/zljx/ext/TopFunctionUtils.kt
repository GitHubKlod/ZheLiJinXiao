package com.zhili.zljx.ext

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.BarUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.DecimalFormat
import java.util.regex.Pattern


/**
 * 格式化double
 */
fun Double.df(dfs:String="#.##")= DecimalFormat(dfs).format(this)?:""

fun String.fromHtml(): Spanned {

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(this)
    }

}

/**
 * 颜色转换  16进制字符串->int   #FFF0F3F4
 */
fun String.parseColor(): Int = Color.parseColor(this)

/**
 * 通过int获取Color资源
 */
fun Int.parseColor(context: Context): Int = ContextCompat.getColor(context, this)

/**
 * 通过int获取Drawable资源
 */
fun Int.parseDrawable(context: Context): Drawable? = ContextCompat.getDrawable(context, this)

/**
 * 通过int获取Drawable资源
 */
fun Int.parseColorStateList(context: Context): ColorStateList? =
    ContextCompat.getColorStateList(context, this)

/**
 * 通过int获取String资源
 */
fun Int.parseString(context: Context): String = context.resources.getString(this)


/**
 * 判断字符串是否手机号
 */
fun String.isPhoneNum(): Boolean {
    val compile = Pattern.compile("^(13|14|15|16|17|18|19)\\d{9}$")
    val matcher = compile.matcher(this)
    return matcher.matches()
}

/**
 * 判断字符串是否身份证
 */
fun String.isIDCardNum(): Boolean {
    val compile = Pattern.compile("(^\\d{15}$)|(^\\d{17}([0-9]|X|x)$)")
    val matcher = compile.matcher(this)
    return matcher.matches()
}

fun String.safePhone(): String {

    var ss =  if(this.length>=11){
        this.substring(0,3)+"****"+ this.substring(7,11)
    }
//    else if(this.length>=7){
//        this.substring(0,3)+"****"+ this.substring(7,11)
//    }
    else{
        this
    }
    return ss
}
fun String.safeIdCard(): String {

    var ss =  if(this.length==15){
        this.substring(0,6)+"******"+ this.substring(12,15)
    } else if(this.length>=18){
        this.substring(0,6)+"*********"+ this.substring(15,18)
    }
    else{
        this
    }
    return ss
}

/**
 * String -> Bean
 */
fun <T> String.fromJson(bean: Class<T>): T = Gson().fromJson(this, bean)

/**
 * Bean -> String
 */
fun Any.toJson(): String = Gson().toJson(this)

/**
 * String ->List
 */
fun <T> String.fromJsonToList(bean: Class<T>): MutableList<T> =
    Gson().fromJson(this, object : TypeToken<MutableList<T>>() {}.type)

/**
 * 计算总页数
 */
fun Int.getTotalPager10() = if (this % 10 == 0) this / 10 else this / 10 + 1

/**
 * 计算总页数20每页的
 */
fun Int.getTotalPager20() = if (this % 20 == 0) this / 20 else this / 20 + 1

/**
 * 显示View VISIBLE
 */
fun View.visibleView() {
    this.visibility = View.VISIBLE
}

/**
 * 显示View VISIBLE true显示 false gone
 */
fun View.visibleView(flag: Boolean) {
    this.visibility = if (flag) View.VISIBLE else View.GONE
}

/**
 * 隐藏View但占位 INVISIBLE
 */
fun View.invisibleView() {
    this.visibility = View.INVISIBLE
}
fun View.invisibleView(flag: Boolean) {
    this.visibility =  if(flag)  View.INVISIBLE else View.VISIBLE
}

/**
 * 隐藏View且不占位 GONE
 */
fun View.goneView() {
    this.visibility = View.GONE
}

/**
 * 隐藏View且不占位 GONE  true不显示 false 显示
 */
fun View.goneView(flag: Boolean) {
    this.visibility = if (flag) View.GONE else View.VISIBLE
}


/**
 * Activity跳转
 */
inline fun <reified T> Context.startAC(block: Intent.() -> Unit) {
    val intent = Intent(this, T::class.java)
    intent.block()
    startActivity(intent)
}


/**
 * 设置顶部自适应状态栏高度
 */
fun View.setMarginTopStatusBarHeight(deviation: Int) {

    when (this.layoutParams) {
        is ConstraintLayout.LayoutParams -> {
            val lp = this.layoutParams as ConstraintLayout.LayoutParams
            lp.topMargin = BarUtils.getStatusBarHeight() + deviation
            this.layoutParams = lp
        }

        is LinearLayout.LayoutParams -> {
            val lp = this.layoutParams as LinearLayout.LayoutParams
            lp.topMargin = BarUtils.getStatusBarHeight() + deviation
            this.layoutParams = lp
        }

        is RelativeLayout.LayoutParams -> {
            val lp = this.layoutParams as RelativeLayout.LayoutParams
            lp.topMargin = BarUtils.getStatusBarHeight() + deviation
            this.layoutParams = lp
        }
    }

}


/**
 * Retrofit请求封装(官方已经出了  弃用)
 */
//    suspend fun <T> Call<T>.await(): T {
//
//        return suspendCoroutine { continuation ->
//            enqueue(object : Callback<T> {
//                override fun onFailure(call: Call<T>, t: Throwable) {
//                    continuation.resumeWithException(t)
//                }
//
//                override fun onResponse(call: Call<T>, response: Response<T>) {
//                    var body = response.body()
////                body = null
//                    if (body == null) {
//                        continuation.resumeWithException(RuntimeException("Response Body Is NULL"))
//                    } else {
//                        continuation.resume(body)
//                    }
//
//                }
//            })
//        }
//    }

