package com.zhili.zljx.ui.common

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import com.just.agentweb.AgentWeb
import com.just.agentweb.AgentWebConfig
import com.just.agentweb.WebChromeClient
import com.just.agentweb.WebViewClient
import com.klod.base.ext.util.logE
import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.databinding.ActivityWebviewBinding
import com.zhili.zljx.ext.checkUrl
import com.zhili.zljx.ext.parseColor
import com.zhili.zljx.utils.MMKVUtils
import com.zhili.zljx.widget.view.CoolIndicatorLayout


/**
 *@Auther KLOD
 *2023/3/30 11:24
 */
class WebViewActivity :BaseMvvmActivity<WebViewActivityVm,ActivityWebviewBinding>() {

    private val webViewUrl1 by lazy {
        intent.getStringExtra("url")?:""
    }
    private var webViewUrl = ""
    private val webViewTitle by lazy {
        intent.getStringExtra("title")?:""
    }
    private var mAgentWeb: AgentWeb? = null
    private var mWebView: WebView? = null
    override fun initView(savedInstanceState: Bundle?) {

        onBackPressedDispatcher.addCallback(this, callback)


        if(webViewUrl1.contains("jxhb")){
            webViewUrl = webViewUrl1+"?id_token=${MMKVUtils.getToken()?.access_token ?: ""}"
        }else{
            webViewUrl = webViewUrl1
        }


        "网页Activity = $webViewUrl".logE()

        mViewBind.apply {
            titleTv.text = webViewTitle
            //返回上一页
            ivBack.setOnClickListener {
                if (mAgentWeb?.back()!=true){
                    finish()
                }
            }
            //刷新点击
            ivRefresh.setOnClickListener {
                mAgentWeb?.urlLoader?.reload()
            }
            //直接关闭
            ivClose.setOnClickListener {
                finish()
            }

        }

        AgentWebConfig.syncCookie(".yiuxiu.com","ZLJX_LOGINKEY=${MMKVUtils.getToken()?.access_token?:""}");
        mAgentWeb = AgentWeb.with(this) //传入Activity or Fragment
            .setAgentWebParent(
                mViewBind.webViewContent, FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                )
            ) //传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
//            .useDefaultIndicator(Color.GREEN)

            .setCustomIndicator(CoolIndicatorLayout(this))

            .setWebChromeClient(mWebChromeClient)
            .setWebViewClient(mWebViewClient)
            .createAgentWeb()
            .ready()
            .go(webViewUrl)

        mAgentWeb?.let {
            var webSetting : WebSettings? =  it.agentWebSettings?.webSettings
            webSetting?.apply {
                userAgentString = Contacts.UserAgentAndroid
                javaScriptEnabled = true
            }
            mWebView = it.webCreator?.webView
            //设置webview取消右边滚动条和水波纹效果
            it.webCreator.webView.overScrollMode = View.OVER_SCROLL_NEVER
            it.webCreator.webView.isVerticalScrollBarEnabled = false
            it.webCreator.webView.isHorizontalScrollBarEnabled = false
            it.jsInterfaceHolder.addJavaObject("android", AndroidInterface())
        }

    }

    private val mWebViewClient: WebViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            //拦截操作

            val url = view?.url?:""
            url.logE()

            if(url.isNotEmpty()){
                //检测跳转
                return checkUrl(url)
            }
            return super.shouldOverrideUrlLoading(view, request)
        }
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            //do you  work
        }
    }
    private val mWebChromeClient: WebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            //do you work
            super.onProgressChanged(view, newProgress)
        }

        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)

            mViewBind.titleTv.text = title?:""
            //跑马灯效果
            mViewBind.titleTv.isSelected = true

        }
    }
    /**
     * JS 交互类
     */
    class AndroidInterface{
        @JavascriptInterface //该注解一定要加让Javascript可以访问
        fun getData(url: String) {
        }
    }
    override fun createObserver() {



    }
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 在这里处理回退事件
            if (mAgentWeb?.back()!=true){
                finish()
            }
        }
    }

}