package com.zhili.zljx.ui.home.mine.account

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.requestNoCheck
import com.klod.base.state.ResultState
import com.zhili.zljx.bean.MyAccountCapitalBean
import com.zhili.zljx.bean.MyAccountRedDotBean
import com.zhili.zljx.network.apiServiceHb

/**
 *@Auther KLOD
 *2023/4/11 10:11
 */
class MyAccountActivityVm : BaseViewModel() {

    var redDotResultState = MutableLiveData<ResultState<MyAccountRedDotBean>>()
    var capitalResultState = MutableLiveData<ResultState<MyAccountCapitalBean>>()

    fun getMyRedDot() {


        requestNoCheck({ apiServiceHb.getMyRedDot(mutableMapOf()) }, redDotResultState)

    }

    fun getMyCapital() {


        requestNoCheck({ apiServiceHb.getMyCapital(mutableMapOf()) }, capitalResultState)

    }

}