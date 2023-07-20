package com.zhili.zljx.ui.home.search

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.requestNoCheck
import com.klod.base.livedata.OkLiveData
import com.klod.base.state.ResultState
import com.zhili.zljx.app.appViewModel
import com.zhili.zljx.bean.ActivityGroupBean
import com.zhili.zljx.network.apiServiceArticle

/**
 *@Auther KLOD
 *2023/4/24 19:45
 */
class GroupSearchActivityVm : BaseViewModel() {

    var searchContext = OkLiveData<String>()

    var groupDataState = MutableLiveData<ResultState<ActivityGroupBean>>()


    /**
     * 获取搜索结果
     */
    fun getSearchResult(cPage: Int, loadType:Int) {

        val map = mutableMapOf<String, Any>().apply {
            put("ProvinceNo", appViewModel.locationInfo.value?.provinceNo ?: 0)
            put("CityNo", appViewModel.locationInfo.value?.cityNo ?: 0)
            put("AreaNo", appViewModel.locationInfo.value?.areaNo ?: 0)
            put("KeyWord", searchContext.value?:"")
            put("LoadType", loadType)
            put("PageIndex", cPage)
            put("PageSize", pageSize)
        }

        requestNoCheck({ apiServiceArticle.getActivityGroupList(map) }, groupDataState)

    }

}