package com.zhili.zljx.ui.home.mine.setting

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.requestNoCheck
import com.klod.base.state.ResultState
import com.zhili.zljx.bean.ErrorBean
import com.zhili.zljx.network.apiServiceMarket

/**
 *@Auther KLOD
 *2023/4/7 16:52
 */
class RealNameActivityVm :BaseViewModel() {

    var rnState = MutableLiveData<ResultState<ErrorBean>>()

    fun postRealName(name:String,IDCard:String){

        val map = mutableMapOf<String,Any>().apply {
            put("RealName",name)
            put("IDCard",IDCard)
        }

        requestNoCheck({ apiServiceMarket.postRealName(map)},rnState,true)

    }

}