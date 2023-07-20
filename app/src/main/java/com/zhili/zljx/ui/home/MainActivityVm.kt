package com.zhili.zljx.ui.home

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.request
import com.klod.base.state.ResultState
import com.zhili.zljx.bean.AppVersionInfoBean
import com.zhili.zljx.network.apiServiceConfig
import com.zhili.zljx.network.apiServiceIM

/**
 *@Auther KLOD
 *2023/3/29 15:00
 */
class MainActivityVm : BaseViewModel() {
    var versionState = MutableLiveData<ResultState<AppVersionInfoBean>>()
    var unReadCountState = MutableLiveData<ResultState<Int>>()

    /**
     * 获取版本信息
     */
    fun getVersionInfo(){

        val map = mutableMapOf<String,Any>().apply {
            put("SystemType","26")
            put("VersionType","1")
        }

        request({ apiServiceConfig.postGroupLike(map)},versionState)


    }
    /**
     * 获取未读数量
     */
    fun getTotalUnReadCount(){

        request({ apiServiceIM.getTotalUnReadCount()},unReadCountState)


    }
}