package com.zhili.zljx.ui.home.mine.setting

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.request
import com.klod.base.state.ResultState
import com.zhili.zljx.bean.AppVersionInfoBean
import com.zhili.zljx.network.apiServiceConfig

/**
 *@Auther KLOD
 *2023/4/26 15:40
 */
class AboutUsActivityVm  :BaseViewModel(){


    var versionState = MutableLiveData<ResultState<AppVersionInfoBean>>()

    fun getVersionInfo(){

        val map = mutableMapOf<String,Any>().apply {
            put("SystemType","26")
            put("VersionType","1")
        }

        request({ apiServiceConfig.postGroupLike(map)},versionState)



    }

}