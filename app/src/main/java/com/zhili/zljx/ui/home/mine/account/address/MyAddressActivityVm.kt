package com.zhili.zljx.ui.home.mine.account.address

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.requestNoCheck
import com.klod.base.state.ResultState
import com.zhili.zljx.bean.ErrorBean
import com.zhili.zljx.bean.MyAddressBean
import com.zhili.zljx.network.apiServiceHb

/**
 *@Auther KLOD
 *2023/5/3 14:18
 */
class MyAddressActivityVm:BaseViewModel() {


    var postAddressState = MutableLiveData<ResultState<ErrorBean>>()
    var addressState = MutableLiveData<ResultState<MyAddressBean>>()


    /**
     * 获取用户地址
     */
    fun getUserAddress(){
        requestNoCheck({ apiServiceHb.getUserAddress(mutableMapOf())},addressState,true)
    }

    /**
     * 获取用户地址
     */
    fun postUserAddress(addressBean: MyAddressBean){

        val map = mutableMapOf<String,Any>().apply {
                put("address",addressBean.address)         //浙江省丽水市云和县
                put("city",addressBean.city)            //丽水市
                put("cityCode",addressBean.cityCode)        //331100
                put("county",addressBean.county)          //云和县
                put("countyCode",addressBean.countyCode)      //331125
                put("deliveryMode",addressBean.deliveryMode)    //送货上门
                put("province",addressBean.province)        //浙江省
                put("provinceCode",addressBean.provinceCode)    //330000
                put("recipient",addressBean.recipient)       //易昌渠
                put("subAddr",addressBean.subAddr)         //金华网络经济中心15楼
                put("telephone",addressBean.telephone)       //17871978642
        }

        requestNoCheck({ apiServiceHb.postUserAddress(addressBean)},postAddressState,true)

    }




}