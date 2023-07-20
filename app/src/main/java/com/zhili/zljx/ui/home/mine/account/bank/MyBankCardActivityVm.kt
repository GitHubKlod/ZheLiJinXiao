package com.zhili.zljx.ui.home.mine.account.bank

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.request
import com.klod.base.ext.requestNoCheck
import com.klod.base.state.ResultState
import com.zhili.zljx.bean.ErrorBean
import com.zhili.zljx.bean.request.CommonRequestBean
import com.zhili.zljx.bean.request.MyBankCardBean
import com.zhili.zljx.bean.request.UnBindCardBean
import com.zhili.zljx.network.apiServiceHb

/**
 *@Auther KLOD
 *2023/5/3 20:28
 */
class MyBankCardActivityVm :BaseViewModel(){



    var cardListState = MutableLiveData<ResultState<MutableList<MyBankCardBean>>>()
    var sendSmsState = MutableLiveData<ResultState<ErrorBean>>()
    var deleteCardState = MutableLiveData<ResultState<ErrorBean>>()


    /**
     * 获取银行卡列表
     */
    fun getBankCardList(){

        request({ apiServiceHb.getUserBankCardList(mutableMapOf())},cardListState)

    }
    /**
     * 删除银行卡
     */
    fun postDeleteBankCard(unBindCardBean: UnBindCardBean){


        requestNoCheck({ apiServiceHb.postDeleteBankCard(unBindCardBean)},deleteCardState)

    }
    /**
     * 删除银行卡   发送短信
     */
    fun sendSms(commonRequestBean: CommonRequestBean){


        requestNoCheck({ apiServiceHb.sendSms(commonRequestBean)},sendSmsState)

    }


}