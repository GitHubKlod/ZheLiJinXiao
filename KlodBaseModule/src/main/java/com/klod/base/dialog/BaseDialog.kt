package com.klod.base.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.hjq.toast.Toaster
import com.klod.base.R
import com.klod.base.ext.util.inflateBindingWithGeneric

/**
 *@Auther KLOD
 *2023/3/31 15:00
 */
abstract class BaseDialog<VB: ViewBinding> (context: Context): Dialog(context, R.style.DialogStyle) {

    lateinit var mViewBind: VB
    abstract fun initView(savedInstanceState: Bundle?)
    abstract fun initClickListener()
    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBind = inflateBindingWithGeneric(layoutInflater)
        setContentView(mViewBind.root)

        mContext = context

        initView(savedInstanceState)
        initClickListener()

    }


    fun showToast(msg:String){
        Toaster.showShort(msg)
    }

}