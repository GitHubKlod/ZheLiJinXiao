package com.zhili.zljx.ui.home.consume

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebBackForwardList

import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView

import android.widget.FrameLayout
import com.hwangjr.rxbus.RxBus
import com.just.agentweb.AgentWeb
import com.just.agentweb.AgentWebConfig
import com.just.agentweb.DefaultWebClient
import com.just.agentweb.WebChromeClient
import com.just.agentweb.WebViewClient
import com.klod.base.ext.util.logE

import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmFragment
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.databinding.FragmentConsumeBinding
import com.zhili.zljx.ext.checkConstraintSet
import com.zhili.zljx.ext.checkUrl
import com.zhili.zljx.utils.MMKVUtils
import com.zhili.zljx.widget.view.CoolIndicatorLayout

/**
 *@Auther KLOD
 *2023/3/29 16:26
 * 消费页面
 */
class ConsumeFragment : BaseMvvmFragment<ConsumeFragmentVm, FragmentConsumeBinding>() {

    private var mAgentWeb: AgentWeb? = null
    private var mWebView: WebView? = null
    override fun initView(savedInstanceState: Bundle?) {


    }

    override fun lazyLoadData() {

        loadWebView()

    }

    private fun loadWebView() {

        //注入Cookie
        AgentWebConfig.syncCookie(
            ".yiuxiu.com",
            "ZLJX_LOGINKEY=${MMKVUtils.getToken()?.access_token ?: ""}"
        );

        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(mViewBind.consumeAgentWeb, FrameLayout.LayoutParams(-1, -1))
//            .useDefaultIndicator()
            .setCustomIndicator(CoolIndicatorLayout(mContext))

//                .setReceivedTitleCallback(mCallback)
            .setWebChromeClient(mWebChromeClient)
            .setWebViewClient(mWebViewClient)
            .setMainFrameErrorView(R.layout.agentweb_error_page, R.id.reloadUrl)
            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
            .createAgentWeb()
            .ready()
            .go(Contacts.HomeConsumeUrl)
//

        "消费WebView地址  = ${Contacts.HomeConsumeUrl}".logE()

        mAgentWeb?.let {
            var webSetting: WebSettings? = it.agentWebSettings?.webSettings

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

    override fun createObserver() {


    }

    /**
     * JS 交互类
     */
    private class AndroidInterface {
        @JavascriptInterface //该注解一定要加让Javascript可以访问
        fun getData(url: String) {

        }
    }

    private val mWebViewClient: WebViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            //拦截操作
            val url = request?.url.toString()

            //查看加载的地址
            "消费WebView地址  = $url".logE()

            if (url.isNotEmpty()) {
                //检测跳转
                return mContext.checkUrl(url)
            }
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {

        }

        override fun onPageFinished(view: WebView?, url: String?) {
            //查看加载的地址
            "消费WebView地址  onPageFinished= $url".logE()

            url?.checkConstraintSet()

            super.onPageFinished(view, url)
        }
    }
    private val mWebChromeClient: WebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            //do you work
            super.onProgressChanged(view, newProgress)
        }
    }

    fun getCanBack(): Boolean {
        mWebView?.let {
            if (it.canGoBack()) {
                //获取webView的浏览记录
                val mWebBackForwardList: WebBackForwardList = it.copyBackForwardList()
                //这里的判断是为了让页面在有上一个页面的情况下，跳转到上一个html页面，而不是退出当前activity
                if (mWebBackForwardList.currentIndex > 0) {
                    val historyUrl =
                        mWebBackForwardList.getItemAtIndex(mWebBackForwardList.currentIndex - 1).url
                    if (historyUrl != it.url) {
                        it.goBack()
                        return true
                    }
                }
            } else {
                return false
            }
        }

        return false
    }
    fun getCurrentUrl() = mWebView?.url
//


}