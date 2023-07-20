package com.zhili.zljx.ui.home.mine.setting

import android.os.Bundle
import com.klod.base.BaseViewModel
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.databinding.ActivityChangePhoneBinding
import com.zhili.zljx.ext.callPhone

/**
 *@Auther KLOD
 *2023/4/7 12:55
 */
class ChangePhoneActivity : BaseMvvmActivity<ChangePhoneActivityVm, ActivityChangePhoneBinding>() {
    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.apply {

            titleBar.titleTv.text = "更换手机号"
            titleBar.ivBack.setOnClickListener { finish() }

            //拨打客服电话
            callService.setOnClickListener {

                try {
                    Contacts.CallService.callPhone(this@ChangePhoneActivity)
                } catch (e: Exception) {
                    showLongToast(
                        "拨打电话失败,请手动拨打\n${
                            Contacts.CallService.replace(
                                "tel:",
                                ""
                            )
                        }"
                    )
                }

            }

        }

    }

    override fun createObserver() {


    }
}