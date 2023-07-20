package com.zhili.zljx.ui.common

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import com.just.agentweb.AgentWeb
import com.klod.base.ext.parseState
import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.databinding.ActivityHtmlCodeBinding
import com.zhili.zljx.ext.fromHtml
import com.zhili.zljx.ext.parseColor

/**
 *@Auther KLOD
 *2023/4/11 11:32
 */
class HtmlTvActivity : BaseMvvmActivity<HtmlTvActivityVm, ActivityHtmlCodeBinding>() {


    private var mAgentWeb: AgentWeb? = null

    override fun initView(savedInstanceState: Bundle?) {


        mViewModel.getHtmlCode(1)
    }

    override fun createObserver() {

        mViewModel.htmlContentResultState.observe(this) { resultState ->
            parseState(resultState, {

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
                    <br/>
                    ${it.errorMsg}
                    </body>
                    </html>
                """
                AgentWeb.with(this) //传入Activity or Fragment
                    .setAgentWebParent(
                        mViewBind.webViewContainer, FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    ) //传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                    .useDefaultIndicator(R.color.transparent.parseColor(this))
                    .createAgentWeb()
                    .ready()
                    .get()
                    .urlLoader
                    .loadData(text, "text/html; charset=UTF-8", null)

//                mAgentWeb?.urlLoader?.
            }, { appException ->
                showToast(appException.errorMsg)
            })
        }

    }
}