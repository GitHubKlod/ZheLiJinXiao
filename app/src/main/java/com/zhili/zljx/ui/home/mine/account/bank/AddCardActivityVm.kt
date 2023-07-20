package com.zhili.zljx.ui.home.mine.account.bank

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.requestNoCheck
import com.klod.base.state.ResultState
import com.zhili.zljx.bean.request.BankCardJsonBean
import com.zhili.zljx.bean.BankMyInfoBean
import com.zhili.zljx.bean.BanksDataBean
import com.zhili.zljx.bean.ErrorBean
import com.zhili.zljx.bean.request.CommonRequestBean
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.ext.fromJson
import com.zhili.zljx.network.apiServiceHb

/**
 *@Auther KLOD
 *2023/5/4 10:28
 */
class AddCardActivityVm :BaseViewModel(){


    var submitCardState = MutableLiveData<ResultState<ErrorBean>>()
    var sendSmsState = MutableLiveData<ResultState<ErrorBean>>()
    var myInfoState = MutableLiveData<ResultState<BankMyInfoBean>>()

    var bansDate = MutableLiveData<BanksDataBean>()

    /**
     * 添加银行卡
     */
    fun postCard(cardBean: BankCardJsonBean){


        requestNoCheck({ apiServiceHb.postAddBankCard(cardBean)},submitCardState,true)

    }
    /**
     * 添加银行卡   发送短信
     */
    fun sendSms(commonRequestBean: CommonRequestBean){


        requestNoCheck({ apiServiceHb.sendSms(commonRequestBean)},sendSmsState,true)

    }


    /**
     * 初始化银行数据
     */
    fun getBanksData(){

        bansDate.value = Contacts.banksDate.fromJson(BanksDataBean::class.java)

    }


    /**
     * 获取 我的基本信息
     */


    fun getMyInfo(){


        requestNoCheck({ apiServiceHb.getMyInfo(mutableMapOf())},myInfoState)


    }


}