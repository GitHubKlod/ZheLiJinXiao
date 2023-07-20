package com.zhili.zljx.ext

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.hjq.toast.Toaster
import com.hwangjr.rxbus.RxBus
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.ui.common.WebViewActivity

/**
 *@Auther KLOD
 *2023/3/30 13:16
 */

fun Context.checkUrl(url: String): Boolean {

    when {
        /**
         * 如果是电话意图
         */
        url.lowercase().contains("tel:") -> {
            url.callPhone(this)
            return true
        }

        //以下连接跳转单独的WebView
        url.contains("supermarket.yiuxiu.com/#/pages/recommendMall")
                || url.contains("car.yiuxiu.com")
                || url.contains("supermarket.yiuxiu.com/#/pages/default")
                || url.contains("supermarket.yiuxiu.com/#/pages_shop")

        -> {
            //跳转WebView吧
            startAC<WebViewActivity> {
                putExtra("url", url)
            }
            return true
        }
        url.contains("#/pagesMy/index") -> {
            RxBus.get().post("setMainFragmentSelect", "4")
            return true
        }

        else -> {
            return false
        }

    }
}

/**
 * 已知需要动态约束的界面 有些网址也要隐藏底部状态栏
 */
fun String.checkConstraintSet() {
    val flagList = listOf(
        Contacts.HomeMessageUrl,
        Contacts.HomeConsumeUrl,
        Contacts.HomeExhibitionUrl,
        "zljx3.yiuxiu.com/index.html",
        "#/pagesChat/friendList",
        "#/pagesDemo/2/activityList",
    )

    flagList.filter {
        this.contains(it)
    }.apply {
        if (isEmpty()) {
            RxBus.get().post("setConstraintSet", "toNav")
        } else {
            RxBus.get().post("setConstraintSet", "toBottom")
        }
    }

//    when {
//
//        //消息
//        this.contains(Contacts.HomeMessageUrl)->{
//            RxBus.get().post("setConstraintSet","toBottom")
//        }
//        //消费
//        this.contains( Contacts.HomeConsumeUrl)->{
//            RxBus.get().post("setConstraintSet","toBottom")
//        }
//        this.contains( "zljx3.yiuxiu.com/index.html")->{
//            RxBus.get().post("setConstraintSet","toBottom")
//        }
//        //展会
//        this.contains(Contacts.HomeExhibitionUrl)->{
//            RxBus.get().post("setConstraintSet","toBottom")
//        }
//        this.contains("#/pagesChat/friendList")->{
//            RxBus.get().post("setConstraintSet","toBottom")
//        }
//        this.contains("#/pagesDemo/2/activityList")->{
//            RxBus.get().post("setConstraintSet","toBottom")
//        }
//
//        else->{
//            RxBus.get().post("setConstraintSet","toNav")
//        }
//
//    }
}


fun String.callPhone(context: Context) {
    XXPermissions.with(context)
        .permission(Permission.CALL_PHONE)
        .request(object : OnPermissionCallback {
            override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                if (allGranted) {
                    try {
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse(this@callPhone)
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        Toaster.showLong(Contacts.CallServiceDesc)
                    }

                }
            }

            override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {

                Toaster.showShort("拨打电话权限开启后才能拨打电话");

            }
        })
}

