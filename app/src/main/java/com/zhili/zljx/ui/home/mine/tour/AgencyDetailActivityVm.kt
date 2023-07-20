package com.zhili.zljx.ui.home.mine.tour

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.request
import com.klod.base.state.ResultState
import com.zhili.zljx.bean.AgencyDetailBean
import com.zhili.zljx.bean.TourLineListBean
import com.zhili.zljx.network.apiServiceTour

/**
 *@Auther KLOD
 *2023/5/2 13:19
 */
class AgencyDetailActivityVm :BaseViewModel(){


    var detailState = MutableLiveData<ResultState<AgencyDetailBean>>()
    var tourLineListState = MutableLiveData<ResultState<TourLineListBean>>()

    /**
     * 获取旅行社信息
     */
    fun getAgencyDetailInfo(agencyId:Int){

        val map = mutableMapOf<String,Any>().apply {
            put("ID",agencyId)
        }

        request({ apiServiceTour.getTourAgencyInfo(map)},detailState,true)


    }
    /**
     * 获取 旅行社旅游路线
     */
    fun getTourLineList(agencyId: Int,cPage:Int){

        val map = mutableMapOf<String,Any>().apply {
            put("AgencyID", agencyId)
            put("pageIndex", cPage)
            put("pageSize" , pageSize)
        }

        request({ apiServiceTour.getTourLineList(map)},tourLineListState)

    }
}