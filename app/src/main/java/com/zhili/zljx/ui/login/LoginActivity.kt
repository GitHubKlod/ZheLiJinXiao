package com.zhili.zljx.ui.login

import android.content.Intent
import android.os.Bundle
import com.klod.base.dialog.OnDialogClickListener
import com.klod.base.ext.parseState
import com.zhili.zljx.app.ActivityManege
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.bean.CheckSMSBean
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.databinding.ActivityLoginBinding
import com.zhili.zljx.ext.isPhoneNum
import com.zhili.zljx.ext.startAC
import com.zhili.zljx.ui.common.WebViewActivity
import com.zhili.zljx.ui.home.MainActivity
import com.zhili.zljx.utils.MMKVUtils
import com.zhili.zljx.widget.dialog.CheckSMSDialog

/**
 *@Auther KLOD
 *2023/3/29 20:53
 */
class LoginActivity : BaseMvvmActivity<LoginActivityVm, ActivityLoginBinding>() {

    private var checkSMSDialog: CheckSMSDialog? = null

//    on


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.toolBar.titleTv.text = "登录"

        mViewBind.toolBar.ivBack.setOnClickListener { finish() }

        //获取验证码点击
        mViewBind.getCode.setOnClickListener {

            if (mViewBind.loginPhoneEt.text.toString().isPhoneNum()) {
                showCheckSMSDialog()
            } else {
                showToast("请输入正确的手机号")
            }
        }
        //用户协议
        mViewBind.privatePolicy.setOnLink1ClickListener {
            startAC<WebViewActivity> {
                putExtra("url", Contacts.userPolicy)
                putExtra("title", "用户协议")
            }
        }
        //隐私政策
        mViewBind.privatePolicy.setOnLink3ClickListener {
            startAC<WebViewActivity> {
                putExtra("url", Contacts.privatePolicy)
                putExtra("title", "隐私政策")

            }
        }


        //点击登录
        mViewBind.loginBtn.setOnClickListener {

            when {
                !mViewBind.loginPhoneEt.text.toString().isPhoneNum() -> {
                    showToast("请输入正确的手机号")
                }

                mViewBind.loginCodeEt.text.isEmpty() -> {
                    showToast("请输入验证码")
                }

                !mViewBind.loginCheckBox.isChecked -> {
                    showToast("请先阅读并同意隐私政策和用户协议")
                }

                else -> {
                    mViewModel.login(
                        mViewBind.loginPhoneEt.text.toString(),
                        mViewBind.loginCodeEt.text.toString()
                    )
                }
            }

        }


    }

    private fun showCheckSMSDialog() {

//        if(checkSMSDialog==null){

        checkSMSDialog = CheckSMSDialog(this, this, object : OnDialogClickListener {
            override fun onClick(index: Int, data: Any) {

                checkSMSDialog?.dismiss()

                var bean = data as CheckSMSBean


                if (bean.code == 0) {
//                        showToast("验证成功")
                    mViewModel.getCode(
                        mViewBind.loginPhoneEt.text.toString(),
                        bean.sessionId,
                        bean.sig,
                        bean.nc_token
                    )
                } else {
                    showToast("验证失败${bean.code}")
                }
            }
        })
//        }

        checkSMSDialog?.show()

    }

    override fun createObserver() {

        mViewModel.codeData.observe(this) {
            parseState(it, {
                mViewBind.getCode.startCountDown()
            }, { it1 ->
                showToast(it1.errorMsg)
            })
        }
        mViewModel.tokenData.observe(this) { resultState ->
            parseState(resultState, {

                //保存Token信息 个人信息可以去个人页面拿 或者 这里再请求一下个人信息接口
                MMKVUtils.setToken(it)
//                appViewModel.tokenInfo.value = it
                MMKVUtils.putBoolean(Contacts.IsLogin,true)

                startAC<MainActivity> {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                ActivityManege.finishActivityExcept(MainActivity::class.java)


            }, { it1 ->
                showToast(it1.errorMsg)
            })
        }

    }


}