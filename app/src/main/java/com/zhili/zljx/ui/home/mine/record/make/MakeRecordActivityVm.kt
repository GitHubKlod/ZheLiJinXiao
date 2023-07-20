package com.zhili.zljx.ui.home.mine.record.make

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.request
import com.klod.base.ext.requestNoCheck
import com.klod.base.state.ResultState
import com.zhili.zljx.bean.CouponMakeRecordBean
import com.zhili.zljx.bean.DiDiMakeRecordBean
import com.zhili.zljx.bean.ECNYMakeRecordBean
import com.zhili.zljx.network.apiServiceECNY
import com.zhili.zljx.network.apiServiceMarket

/**
 *@Auther KLOD
 *2023/4/12 15:02
 */
class MakeRecordActivityVm:BaseViewModel() {

    var makeRecordState = MutableLiveData<ResultState<CouponMakeRecordBean>>()

    var ecnyMakeRecordState = MutableLiveData<ResultState<ECNYMakeRecordBean>>()

    var didiMakeRecordState = MutableLiveData<ResultState<ECNYMakeRecordBean>>()


    /**
     * 获取普惠券 预约记录
     */
    fun getMakeRecordList(index:Int){

        val map = mutableMapOf<String,Any>().apply {

            put("PageIndex",index)
            put("PageSize",pageSize)
        }

        requestNoCheck({ apiServiceMarket.getMakeRecordList(map)},makeRecordState)

    }

    /**
     * 获取数币 预约记录
     */
    fun getECNYMakeRecordList(index:Int){

        val map = mutableMapOf<String,Any>().apply {

            put("PageIndex",index)
            put("PageSize",pageSize)
        }

        request({ apiServiceECNY.getECNYMakeRecordList(map)},ecnyMakeRecordState)

    }
    /**
     * 获取滴滴 预约记录
     */
    fun getDiDiMakeRecordList(index:Int){

        val map = mutableMapOf<String,Any>().apply {

            put("PageIndex",index)
            put("PageSize",pageSize)
        }

        request({ apiServiceECNY.getDiDiMakeRecordList(map)},didiMakeRecordState)

    }


}