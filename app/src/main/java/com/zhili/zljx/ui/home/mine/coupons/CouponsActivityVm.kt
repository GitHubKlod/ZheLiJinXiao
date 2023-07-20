package com.zhili.zljx.ui.home.mine.coupons

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.request
import com.klod.base.ext.requestNoCheck
import com.klod.base.state.ResultState
import com.klod.base.state.paresException
import com.zhili.zljx.bean.DiDiGrantLogBean
import com.zhili.zljx.bean.MyCouponListBean
import com.zhili.zljx.bean.RmbGrantLogBean

import com.zhili.zljx.network.apiServiceECNY

import com.zhili.zljx.network.apiServiceMarketPayment
import com.zhili.zljx.network.stateCallback.ECNYUiState

/**
 *@Auther KLOD
 *2023/4/3 16:37
 */
class CouponsActivityVm:BaseViewModel() {


    var couponListData = MutableLiveData<ResultState<MyCouponListBean>>()

    var ecnyListDataUiState = MutableLiveData<ECNYUiState<MutableList<RmbGrantLogBean.GrantLog.ECNY>>>()
    var didiListDataUiState = MutableLiveData<ECNYUiState<MutableList<DiDiGrantLogBean.GrantLog.ECNY>>>()
//    var ecnyListData = MutableLiveData<MutableList<RmbGrantLogBean.GrantLog.ECNY>>()

    /**
     * 获取消费券接口
     */
    fun getConsumerCouponList(index:Int,type:Int){

        val map = mutableMapOf<String,Any>().apply {
            put("LoadType",type)
            put("PageIndex",index)
            put("PageSize",pageSize)
        }
        requestNoCheck({ apiServiceMarketPayment.getCouponList(map)},couponListData)

    }
    /**
     * 获取数币中签列表
     */
    fun getRmbGrantLog(index:Int){

        val map = mutableMapOf<String,Any>().apply {
            put("PageIndex",index)
            put("PageSize",pageSize)
        }
//        ecnyListDataResultState

        request({ apiServiceECNY.getRmbGrantLog(map)},{
            //处理一下数据 , 把内部数据提取出来 ,
            val list = mutableListOf<RmbGrantLogBean.GrantLog.ECNY>()
            it.grantLogs.map { grantLog ->
                grantLog.ECNYs.map { ecny ->
                    ecny.titleName = grantLog.CityName+ grantLog.CountyName+ecny.BankName+ecny.PrizeTitle
                    list.add(ecny)
                }
            }
            val state=ECNYUiState(isSuccess = true, errorMsg ="", data = list,totalCount= it.totalCount )
            ecnyListDataUiState.value =state
        } ,{
            ecnyListDataUiState.value = ECNYUiState(isSuccess = false, errorMsg = it.errorMsg)
        })

    }

    /**
     * 获取滴滴券中签列表
     */
    fun getDiDiGrantLog(index:Int){

        val map = mutableMapOf<String,Any>().apply {
            put("PageIndex",index)
            put("PageSize",pageSize)
        }

        request({ apiServiceECNY.getDiDiGrantLog(map)}, {
            //处理一下数据 , 把内部数据提取出来 ,
            val list = mutableListOf<DiDiGrantLogBean.GrantLog.ECNY>()
            it.grantLogs.map { grantLog ->
                grantLog.ECNYs.map { ecny ->
                    list.add(ecny)
                }
            }
            val state=ECNYUiState(isSuccess = true, errorMsg ="", data = list,totalCount= it.totalCount )
            didiListDataUiState.value =state
        } ,{
            didiListDataUiState.value = ECNYUiState(isSuccess = false, errorMsg = it.errorMsg)
        })

    }


}