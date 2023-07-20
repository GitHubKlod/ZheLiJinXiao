package com.zhili.zljx.widget.dialog

import android.content.Context

import android.os.Bundle
import android.view.Gravity

import android.view.WindowManager

import com.klod.base.dialog.BaseDialog
import com.klod.base.dialog.OnDialogClickListener
import com.zhili.zljx.R
import com.zhili.zljx.bean.request.UnBindCardBean


import com.zhili.zljx.databinding.DialogUnbindBankCardBinding
import com.zhili.zljx.ext.isPhoneNum


class UnbindBankCardDialog(
    context: Context,
    private var cardId: Int,
    private val listener: OnDialogClickListener
) :
    BaseDialog<DialogUnbindBankCardBinding>(context) {


    override fun initView(savedInstanceState: Bundle?) {


        initDialog()

        initClickListener()

    }


    override fun initClickListener() {


        mViewBind.apply {
            closeDialog.setOnClickListener { dismiss() }

            getSmsCode.setOnClickListener {

                when {
                    !phoneEt.text.toString().isPhoneNum() -> {
                        showToast("请输入正确手机号")
                    }
                    else->{
                        listener.onClick(2, phoneEt.text.toString())
                    }
                }

            }

            //保存
            saveChange.setOnClickListener {
                when {
                    !phoneEt.text.toString().isPhoneNum() -> {
                        showToast("请输入正确手机号")
                    }

                    codeEt.text.toString().isEmpty() -> {
                        showToast("请输入验证码")
                    }

                    else -> {
                        val unBindCardBean = UnBindCardBean(
                            id = cardId,
                            phone = phoneEt.text.toString(),
                            code = codeEt.text.toString()
                            )
                        listener.onClick(1, unBindCardBean)
                    }
                }
            }

        }
    }

    fun startCountDown(){
        mViewBind.getSmsCode.startCountDown()
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