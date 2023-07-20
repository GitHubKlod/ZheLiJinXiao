package com.zhili.zljx.ui.home.mine.tour

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.request
import com.klod.base.state.ResultState
import com.zhili.zljx.bean.TourLineDetailBean
import com.zhili.zljx.network.apiServiceTour

/**
 *@Auther KLOD
 *2023/5/3 9:56
 */
class TourLineDetailActivityVm :BaseViewModel() {


    var lineDetailState = MutableLiveData<ResultState<TourLineDetailBean>>()


    /**
     * 获取旅游路线详情
     */
    fun getLineDetail(lineId:Int){

        val map = mutableMapOf<String,Any>().apply {
            put("ID",lineId)
        }

        request({ apiServiceTour.getTourLineDetail(map)},lineDetailState,true)

    }

}