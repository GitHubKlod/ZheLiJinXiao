package com.zhili.zljx.ui.home.mine.order.detail

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.request
import com.klod.base.ext.requestNoCheck
import com.klod.base.state.ResultState
import com.zhili.zljx.bean.ErrorBean
import com.zhili.zljx.bean.TourOrderDetailBean
import com.zhili.zljx.bean.WriteOffOrderDetailBean
import com.zhili.zljx.network.apiServiceMarketPayment
import com.zhili.zljx.network.apiServiceTour

/**
 *@Auther KLOD
 *2023/4/15 15:52
 */
class OrderDetailActivityVm :BaseViewModel() {

    var cancelOrderState = MutableLiveData<ResultState<ErrorBean>>()
    var cancelTourOrderState = MutableLiveData<ResultState<ErrorBean>>()


    var orderDetailState = MutableLiveData<ResultState<WriteOffOrderDetailBean>>()
    var tourOrderDetailState = MutableLiveData<ResultState<TourOrderDetailBean>>()


    /**
     * 获取核销订单详情
     */
    fun getWriteOffOrderDetail(orderNo:String){
        var map = mutableMapOf<String,Any>().apply {
            put("OrderNo",orderNo)
        }
        requestNoCheck({ apiServiceMarketPayment.getWriteOffOrderDetail(map)},orderDetailState,true)
    }

    /**
     * 取消核销订单
     */
    fun postCancelWriteOffOrder(orderNo:String){

        val map = mutableMapOf<String,Any>().apply {
            put("OrderNo",orderNo)
        }
        requestNoCheck({ apiServiceMarketPayment.postCancelOrder(map)},cancelOrderState,true)

    }

    /**
     * 获取文旅订单详情
     */
    fun getTourOrderDetail(orderNo:String){
        var map = mutableMapOf<String,Any>().apply {
            put("OrderNo",orderNo)
        }
        request({ apiServiceTour.getTourOrderDetailInfo(map)},tourOrderDetailState,true)
    }

    /**
     * 取消文旅核销订单
     */
    fun postTourCancelOrder(orderNo:String){

        val map = mutableMapOf<String,Any>().apply {
            put("orderNO",orderNo)
        }
        requestNoCheck({ apiServiceTour.postCancelOrder(map)},cancelTourOrderState,true)

    }


}