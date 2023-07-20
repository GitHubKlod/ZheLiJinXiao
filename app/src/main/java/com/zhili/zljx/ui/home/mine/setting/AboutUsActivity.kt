package com.zhili.zljx.ui.home.mine.setting

import android.os.Bundle
import com.blankj.utilcode.util.AppUtils
import com.klod.base.ext.parseState
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.bean.AppVersionInfoBean
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.databinding.ActivityAboutUsBinding
import com.zhili.zljx.ext.callPhone
import com.zhili.zljx.ext.startAC
import com.zhili.zljx.ui.common.WebViewActivity

/**
 *@Auther KLOD
 *2023/4/26 15:40
 */
class AboutUsActivity : BaseMvvmActivity<AboutUsActivityVm, ActivityAboutUsBinding>() {
    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.apply {
            titleBar.titleTv.text = "关于我们"
            titleBar.ivBack.setOnClickListener { finish() }

            callService.setValueTvText(Contacts.CallServiceNumber)

            updateVersion.setValueTvText("当前版本: " + AppUtils.getAppVersionName())

            callService.setOnClickListener {

                Contacts.CallService.callPhone(this@AboutUsActivity)

            }


            //用户协议
            userPolicy.setOnClickListener {
                startAC<WebViewActivity> {
                    putExtra("url", Contacts.userPolicy)
                    putExtra("title", "用户协议")
                }
            }
            //隐私政策
            privatePolicy.setOnClickListener {
                startAC<WebViewActivity> {
                    putExtra("url", Contacts.privatePolicy)
                    putExtra("title", "隐私政策")

                }
            }

            updateVersion.setOnClickListener {
                mViewModel.getVersionInfo()
            }
        }

    }

    override fun createObserver() {


        mViewModel.versionState.observe(this) { resultState ->

            parseState(resultState, {
                setVersionInfo(it)
            }, {
                showToast(it.errorMsg)
            })

        }


    }


    /**
     * 设置版本信息
     */
    private fun setVersionInfo(it: AppVersionInfoBean) {

        //弹窗提示 当前code > = 发布的版本 那就是说明是更新的版本无需更新
        if (AppUtils.getAppVersionCode() >= it.MinVersion) {
            showToast("当前已是最新版")
        } else {
//            showUpdateAppDialog("", false, "", "")
            showUpdateAppDialog(
                it.VersionNo,
                it.IsForceUpdate == 1,
                it.ChangeLog
            )
        }

    }
}