package com.zhili.zljx.ui.home.mine.tour

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.request
import com.klod.base.state.ResultState
import com.zhili.zljx.bean.TourAgencyClassBean
import com.zhili.zljx.bean.TourAgencyListBean
import com.zhili.zljx.network.apiServiceTour

/**
 *@Auther KLOD
 *2023/5/2 10:17
 */
class TourAgencyActivityVm :BaseViewModel() {

    var classDataState = MutableLiveData<ResultState<MutableList<TourAgencyClassBean>>>()
    var tourAgencyListState = MutableLiveData<ResultState<TourAgencyListBean>>()

    /**
     * 获取 旅行社分类
     */
    fun getTourAgencyClass(){

        request({ apiServiceTour.getTourAgencyClassList(mutableMapOf())},classDataState,true)

    }

    /**
     * 获取 旅行社列表
     */
    fun getTourAgencyList(id:Int,cPage:Int){

        val map = mutableMapOf<String,Any>().apply {
            put("AgencyType",id)

            put("pageIndex", cPage)
            put("pageSize" , pageSize)
        }

        request({ apiServiceTour.getTourAgencyList(map)},tourAgencyListState)

    }
}