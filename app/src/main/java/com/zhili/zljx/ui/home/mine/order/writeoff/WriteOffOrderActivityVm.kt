package com.zhili.zljx.ui.home.mine.order.writeoff

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.requestNoCheck
import com.klod.base.state.ResultState
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.bean.ErrorBean
import com.zhili.zljx.bean.WriteOffOrderBean
import com.zhili.zljx.network.apiServiceMarketPayment

/**
 *@Auther KLOD
 *2023/4/13 14:24
 */
class WriteOffOrderActivityVm :BaseViewModel() {

    var woOrderState = MutableLiveData<ResultState<WriteOffOrderBean>>()
    var cancelOrderState = MutableLiveData<ResultState<ErrorBean>>()

    /**
     * 获取核销订单
     */
    fun getWriteOffOrderList(type: Int, cPage: Int) {


        val map = mutableMapOf<String,Any>().apply {
            if(type!=0)
                put("Status",type)
            put("PageIndex",cPage)
            put("PageSize",pageSize)
        }

        requestNoCheck({ apiServiceMarketPayment.getWriteOffOrderList(map)},woOrderState)

    }

    fun postCancelOrder(orderNo:String){

        val map = mutableMapOf<String,Any>().apply {
            put("OrderNo",orderNo)
        }
        requestNoCheck({ apiServiceMarketPayment.postCancelOrder(map)},cancelOrderState,true)

    }


}