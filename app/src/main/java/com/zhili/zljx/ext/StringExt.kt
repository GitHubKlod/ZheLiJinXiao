package com.zhili.zljx.ext

import android.content.ClipData
import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import com.klod.base.ext.util.clipboardManager

/**
 *@Auther KLOD
 *2023/4/13 13:10
 */
/**
 * 复制文本到粘贴板
 */
fun String.copyToClipboard(content: Context, label: String = "ZLJX") {
    val clipData = ClipData.newPlainText(label, this)
    content.clipboardManager?.setPrimaryClip(clipData)
}

/**
 * 处理价格 大小文字
 */
fun Double.priceStyle(df:String = "0.00",size:Int = 18):SpannableString{
    var sStr = SpannableString("￥${this.df(df)}")
    sStr.setSpan(
        AbsoluteSizeSpan(size, true),
        1,
        sStr.indexOf("."),
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return  sStr
}

/**
 * 处理简单的 文字变色
 */
fun String.colorChange(color:Int,start:Int,end:Int):SpannableString{
    val spannableString = SpannableString(this)
    spannableString.setSpan(ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannableString
}
