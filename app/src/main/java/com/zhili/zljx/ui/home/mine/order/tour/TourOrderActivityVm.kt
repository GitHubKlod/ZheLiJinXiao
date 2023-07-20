package com.zhili.zljx.ui.home.mine.order.tour

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.request
import com.klod.base.ext.requestNoCheck
import com.klod.base.state.ResultState
import com.zhili.zljx.bean.ErrorBean
import com.zhili.zljx.bean.TourOrderBean
import com.zhili.zljx.network.apiServiceMarketPayment
import com.zhili.zljx.network.apiServiceTour

/**
 *@Auther KLOD
 *2023/4/14 14:07
 */
class TourOrderActivityVm :BaseViewModel() {

    var tourOrderState = MutableLiveData<ResultState<TourOrderBean>>()
    var cancelOrderState = MutableLiveData<ResultState<ErrorBean>>()

    fun getTourOrderState(type:Int,index:Int){

        var map = mutableMapOf<String,Any>().apply {
            if(type!=-2)
                put("payStatus",type)
            put("pageIndex",index)
            put("pageSize",pageSize)
        }
        request({ apiServiceTour.getTourOrderList(map)},tourOrderState)

    }

    fun postCancelOrder(orderNo:String){

        val map = mutableMapOf<String,Any>().apply {
            put("orderNO",orderNo)
        }
        requestNoCheck({ apiServiceTour.postCancelOrder(map)},cancelOrderState,true)

    }




}