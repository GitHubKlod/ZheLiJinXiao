package com.zhili.zljx.widget.dialog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.JavascriptInterface
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.just.agentweb.AgentWeb
import com.klod.base.dialog.BaseDialog
import com.klod.base.dialog.OnDialogClickListener
import com.zhili.zljx.R
import com.zhili.zljx.bean.CheckSMSBean
import com.zhili.zljx.databinding.DialogChangeNicknameBinding
import com.zhili.zljx.databinding.DialogCheckSmsBinding
import com.zhili.zljx.ext.fromJson
import com.zhili.zljx.ext.parseColor
import java.util.Locale


class ChangeNicknameDialog(context: Context,private var nickname:String, private val listener: OnDialogClickListener) :
    BaseDialog<DialogChangeNicknameBinding>(context){


    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.newNickName.setText(nickname)

        initDialog()

        initClickListener()

    }


    override fun initClickListener() {


        mViewBind.apply {
            closeDialog.setOnClickListener { dismiss() }

            //保存
            saveChange.setOnClickListener {
                if(newNickName.text.toString().isEmpty()){
                    showToast("请输入昵称")
                }else{
                    listener.onClick(1, newNickName.text.toString())
                }
            }

        }
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