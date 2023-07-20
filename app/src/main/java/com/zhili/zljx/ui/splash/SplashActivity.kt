package com.zhili.zljx.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import com.zhili.zljx.app.ActivityManege
import com.zhili.zljx.app.MyApplication
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.databinding.ActivitySplashBinding
import com.zhili.zljx.ext.startAC
import com.zhili.zljx.ui.common.WebViewActivity
import com.zhili.zljx.ui.home.MainActivity
import com.zhili.zljx.utils.MMKVUtils

/**
 *@Auther KLOD
 *2023/3/29 16:55
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseMvvmActivity<SplashActivityVm, ActivitySplashBinding>() {

    override fun initView(savedInstanceState: Bundle?) {


        if (MMKVUtils.getBoolean(Contacts.IS_AGREE_AGREEMENT, false)) {
            //统一协议
            agreeAgreement()
        } else {
            //没同意协议 不做操作等用户点击


        }

        mViewBind.apply {
            //用户协议
            registerAgreementDialogContent.setOnLink1ClickListener {
                startAC<WebViewActivity> {
                    putExtra("url", Contacts.userPolicy)
                    putExtra("title", "用户协议")
                }
            }
            //隐私政策
            registerAgreementDialogContent.setOnLink2ClickListener {
                startAC<WebViewActivity> {
                    putExtra("url", Contacts.privatePolicy)
                    putExtra("title", "隐私政策")
                }
            }

            registerAgreementDialogCancel.setOnClickListener {
                //不同意隐私政策 推出应用程序
                ActivityManege.appExit(this@SplashActivity)

            }

            registerAgreementDialogOk.setOnClickListener {
                //同意隐私政策
                agreeAgreement()
                MMKVUtils.putBoolean(Contacts.IS_AGREE_AGREEMENT, true)

            }

        }


    }

    /**
     * 同意协议 后跳转
     */
    private fun agreeAgreement() {
        //同意过协议
        MyApplication.initDangerousSdk()
        //跳转主页
        startAC<MainActivity> { }
    }

    override fun createObserver() {


    }
}