package com.zhili.zljx.ui.home.article

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.FOCUS_BLOCK_DESCENDANTS
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.just.agentweb.AgentWeb
import com.just.agentweb.NestedScrollAgentWebView
import com.klod.base.ext.parseState
import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.bean.ArticleDetailBean
import com.zhili.zljx.databinding.ActivityArticleBinding
import com.zhili.zljx.ext.parseColor
import com.zhili.zljx.widget.view.NoScrollAgentWebView


/**
 *@Auther KLOD
 *2023/4/19 19:16
 */
class ArticleDetailActivity: BaseMvvmActivity<ArticleDetailActivityVm, ActivityArticleBinding>() {


    private val articleId by lazy {
        intent?.getStringExtra("articleId")?:""
    }

    override fun initView(savedInstanceState: Bundle?) {



        mViewBind.apply {

            titleBar.titleTv.text = "资讯详情"
            titleBar.ivBack.setOnClickListener { finish() }

        }

        mViewModel.getArticleDetail(articleId)
    }

    override fun createObserver() {

        mViewModel.articleSate.observe(this){ resultState ->
            parseState(resultState,{
                                   setArticleLayout(it)
            },{
                showToast(it.errorMsg)
            })
        }

    }

    private fun setArticleLayout(it: ArticleDetailBean) {



        mViewBind.apply {
            upTime.text = "创建于: ${it.CreateTime}"
            title.text = it.Title

        }



        val text = """
                    <!DOCTYPE html>
                    <html>
                    <head>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <style>
                    img {
                      max-width: 100%;
                      height: auto;
                    }
                    </style>
                    </head>
                    <body>
                    ${it.Content}
                    </body>
                    </html>
                """

        val nestedScrollAgentWebView = NoScrollAgentWebView(this)
        var agentWeb = AgentWeb.with(this) //传入Activity or Fragment
            .setAgentWebParent(
                mViewBind.webViewContainer, FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                )
            ) //传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
            .useDefaultIndicator(R.color.transparent.parseColor(this))
            .setWebView(nestedScrollAgentWebView)
            .createAgentWeb()
            .ready()
            .get()
//设置webview取消右边滚动条和水波纹效果
        agentWeb.webCreator.webView.overScrollMode = View.OVER_SCROLL_NEVER
        agentWeb.webCreator.webView.isVerticalScrollBarEnabled = false
        agentWeb.webCreator.webView.isHorizontalScrollBarEnabled = false
//        agentWeb.webCreator.webView.isNestedScrollingEnabled = false
//        agentWeb.webCreator.webView.descendantFocusability = FOCUS_BLOCK_DESCENDANTS
        agentWeb.agentWebSettings.webSettings.apply {
//            cacheMode = WebSettings.LOAD_NO_CACHE
//            useWideViewPort = true
//            loadWithOverviewMode = true
        }

        agentWeb.urlLoader
            .loadData(text, "text/html; charset=UTF-8", null)




    }
}