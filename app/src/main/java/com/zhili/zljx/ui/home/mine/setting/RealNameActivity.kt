package com.zhili.zljx.ui.home.mine.setting

import android.os.Bundle
import com.klod.base.BaseViewModel
import com.klod.base.ext.parseState
import com.zhili.zljx.app.appViewModel
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.databinding.ActivityRealNameBinding
import com.zhili.zljx.ext.isIDCardNum
import com.zhili.zljx.ext.isPhoneNum

/**
 *@Auther KLOD
 *2023/4/7 16:52
 */
class RealNameActivity : BaseMvvmActivity<RealNameActivityVm, ActivityRealNameBinding>() {
    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.apply {
            titleBar.ivBack.setOnClickListener { finish() }
            titleBar.titleTv.text = "实名认证"

            //实名认证提交
            submitBtn.setOnClickListener {

                when {

                    realName.text.toString().isEmpty() -> {
                        showToast("请输入真实姓名")
                    }

                    !idCard.text.toString().isIDCardNum() -> {
                        showToast("请输入正确的身份证")
                    }

                    else -> {
                        mViewModel.postRealName(realName.text.toString(), idCard.text.toString())
                    }

                }

            }
        }

    }

    override fun createObserver() {

        mViewModel.rnState.observe(this) { resultState ->

            parseState(resultState, {
                var userInfo = appViewModel.userInfo.value
                userInfo?.IsRealName = 1
                appViewModel.userInfo.value = userInfo
                finish()
            }, {
                showToast(it.errorMsg)
            })

        }

    }


}