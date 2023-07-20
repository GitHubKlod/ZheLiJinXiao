package com.zhili.zljx.widget.dialog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.just.agentweb.AgentWeb
import com.klod.base.dialog.BaseDialog
import com.klod.base.dialog.OnDialogClickListener
import com.zhili.zljx.R
import com.zhili.zljx.bean.CheckSMSBean
import com.zhili.zljx.databinding.DialogCheckSmsBinding
import com.zhili.zljx.ext.fromJson
import com.zhili.zljx.ext.parseColor
import java.util.Locale


class CheckSMSDialog(context: Context, private val activity: AppCompatActivity, private val listener: OnDialogClickListener) :
    BaseDialog<DialogCheckSmsBinding>(context){


    override fun initView(savedInstanceState: Bundle?) {

        initDialog()

        var agentWeb = AgentWeb.with(activity) //传入Activity or Fragment
            .setAgentWebParent(mViewBind.dialogWebViewLayout, FrameLayout.LayoutParams(
             ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)) //传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
            .useDefaultIndicator(R.color.transparent.parseColor(context))
            .createAgentWeb()
            .ready()
            .go("file:///android_asset/web_check_msg.html")

        agentWeb.jsInterfaceHolder.addJavaObject("android", AndroidInterface(listener))
        //agentWeb.webCreator.webView.scrollBarSize = 0
        //设置webview取消右边滚动条和水波纹效果
        agentWeb.webCreator.webView.overScrollMode = View.OVER_SCROLL_NEVER
        agentWeb.webCreator.webView.isVerticalScrollBarEnabled = false
        agentWeb.webCreator.webView.isHorizontalScrollBarEnabled = false

        agentWeb.agentWebSettings.webSettings.apply {
            cacheMode = WebSettings.LOAD_NO_CACHE
//            useWideViewPort = true
//            loadWithOverviewMode = true
        }


    }

    class AndroidInterface(private val listener: OnDialogClickListener){
        @JavascriptInterface //该注解一定要加让Javascript可以访问
        fun getData(url: String) {
            if(url.isEmpty()){
                listener.onClick(0,"{}")
            }else{
                listener.onClick(0,url.fromJson(CheckSMSBean::class.java))
            }
        }
    }

    override fun initClickListener() {

        mViewBind.closeDialog.setOnClickListener { dismiss() }

    }


    //Dialog初始化
    private fun initDialog() {

        window?.setGravity(Gravity.CENTER)
        window?.setWindowAnimations(R.style.picker_view_slide_anim)
        setCanceledOnTouchOutside(true)
        //设置dialog没有边距
        window?.decorView?.setPadding(0, 0, 0, 0)
        val lp = window?.attributes
        lp?.width = WindowManager.LayoutParams.MATCH_PARENT
        lp?.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.attributes = lp

    }


}