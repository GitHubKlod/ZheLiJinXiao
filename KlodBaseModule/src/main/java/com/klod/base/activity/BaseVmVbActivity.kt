package com.klod.base.activity

import android.app.usage.UsageEvents.Event
import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.klod.base.BaseViewModel
import com.klod.base.ext.util.inflateBindingWithGeneric


abstract class BaseVmVbActivity<VM : BaseViewModel, VB:ViewBinding> : BaseVmActivity<VM>() {

    override fun layoutId(): Int = 0

    lateinit var mViewBind: VB

    /**
     * 创建DataBinding
     */
    override fun initDataBind(): View? {
        mViewBind = inflateBindingWithGeneric(layoutInflater)
        return mViewBind.root

    }


}