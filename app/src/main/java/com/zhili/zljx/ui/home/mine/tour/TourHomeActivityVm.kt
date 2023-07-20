package com.zhili.zljx.ui.home.mine.tour

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.request
import com.klod.base.state.ResultState
import com.zhili.zljx.bean.TourAgencyClassBean
import com.zhili.zljx.bean.TourLineListBean
import com.zhili.zljx.bean.TourAgencyListBean
import com.zhili.zljx.bean.TourRegionCouponBean
import com.zhili.zljx.bean.TourRegionListBean
import com.zhili.zljx.bean.TourStrategyListBean
import com.zhili.zljx.network.apiServiceTour

/**
 *@Auther KLOD
 *2023/4/17 16:49
 */
class TourHomeActivityVm :BaseViewModel(){


    var classDataState = MutableLiveData<ResultState<MutableList<TourAgencyClassBean>>>()
    var tourAgencyListState = MutableLiveData<ResultState<TourAgencyListBean>>()
    var tourStrategyListState = MutableLiveData<ResultState<TourStrategyListBean>>()
    var tourLineListState = MutableLiveData<ResultState<TourLineListBean>>()

    var regionListState = MutableLiveData<ResultState<MutableList<TourRegionListBean>>>()
    var regionCouponListState = MutableLiveData<ResultState<TourRegionCouponBean>>()

    var vpHeight = MutableLiveData<Int>()

    /**
     * 获取 旅行社分类
     */
    fun getTourAgencyClass(){

        request({ apiServiceTour.getTourAgencyClassList(mutableMapOf())},classDataState)

    }

    /**
     * 获取 旅行社列表
     */
    fun getTourAgencyList(id:Int,cPage:Int){

        val map = mutableMapOf<String,Any>().apply {
            put("AgencyType",id)

            put("pageIndex", cPage)
            put("pageSize" , 3)
        }

        request({ apiServiceTour.getTourAgencyList(map)},tourAgencyListState)

    }

    /**
     * 获取 旅行社攻略
     */
    fun getTourStrategy(){

        val map = mutableMapOf<String,Any>().apply {
            put("pageIndex", 1)
            put("pageSize" , 50)
        }

        request({ apiServiceTour.getTourStrategyList(map)},tourStrategyListState)

    }

    /**
     * 获取 旅行社旅游路线
     */
    fun getTourLineList(cPage:Int){

        val map = mutableMapOf<String,Any>().apply {
            put("pageIndex", cPage)
            put("pageSize" , pageSize)
        }

        request({ apiServiceTour.getTourLineList(map)},tourLineListState)

    }

    /**
     * 获取文旅券地区列表
     */
    fun getTourRegionList(){

        val map = mutableMapOf<String,Any>()

        request({ apiServiceTour.getTourRegionList(map)},regionListState)

    }

    /**
     * 获取地区券入口列表
     */
    fun getTourRegionCouponList(regionId:Int){

        val map = mutableMapOf<String,Any>().apply {
            put("PageIndex", 1)
            put("RegionId", regionId)
            put("PageSize" , 50)
        }

        request({ apiServiceTour.getTourRegionCouponList(map)},regionCouponListState)

    }

}