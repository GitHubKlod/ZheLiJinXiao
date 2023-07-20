package com.zhili.zljx.ui.common

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.requestNoCheck
import com.klod.base.state.ResultState
import com.zhili.zljx.bean.ErrorBean
import com.zhili.zljx.network.apiServiceHb

/**
 *@Auther KLOD
 *2023/4/11 11:32
 */
class HtmlTvActivityVm :BaseViewModel() {

    var htmlContentResultState = MutableLiveData<ResultState<ErrorBean>>()

    fun getHtmlCode(flag:Int){

        var map = mutableMapOf<String,Any>().apply {
            put("flag",flag)

        }
        requestNoCheck({ apiServiceHb.getHtmlCode(map)},htmlContentResultState)

    }

}