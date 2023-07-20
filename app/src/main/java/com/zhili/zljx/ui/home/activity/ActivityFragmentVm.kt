package com.zhili.zljx.ui.home.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.klod.base.BaseViewModel
import com.klod.base.appContext
import com.klod.base.ext.requestNoCheck
import com.klod.base.ext.util.logE
import com.klod.base.ext.util.logFromJson
import com.klod.base.state.ResultState
import com.klod.base.state.paresException
import com.klod.base.state.paresResult
import com.zhili.zljx.app.MyApplication
import com.zhili.zljx.app.appViewModel
import com.zhili.zljx.bean.ActivityGroupBean
import com.zhili.zljx.bean.ActivityHomeConfigBean
import com.zhili.zljx.bean.ArticleBean
import com.zhili.zljx.bean.LocationDataInfo
import com.zhili.zljx.network.apiServiceArticle
import com.zhili.zljx.network.stateCallback.LocationUiState
import com.zhili.zljx.ui.home.activity.adapter.ArticleAdapter
import com.zhili.zljx.utils.MMKVUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


/**
 *@Auther KLOD
 *2023/3/29 16:34
 */
class ActivityFragmentVm : BaseViewModel() {


    var pageConfig = MutableLiveData<ResultState<MutableList<ActivityHomeConfigBean>>>()
    var articleDataState = MutableLiveData<ResultState<ArticleBean>>()
    var groupDataState = MutableLiveData<ResultState<ActivityGroupBean>>()

    var locationUiState = MutableLiveData<LocationUiState<LocationDataInfo>>()






    /**
     * 获取活动圈
     */
    fun getActivityGroupList(pageSize: String="3") {


        var map = mutableMapOf<String, Any>().apply {
            put("ProvinceNo", appViewModel.locationInfo.value?.provinceNo ?: 0)
            put("CityNo", appViewModel.locationInfo.value?.cityNo ?: 0)
            put("AreaNo", appViewModel.locationInfo.value?.areaNo ?: 0)
            put("KeyWord", "")
            put("LoadType", "0")
            put("PageIndex", 1)
            put("PageSize", pageSize)
        }

        requestNoCheck({ apiServiceArticle.getActivityGroupList(map) }, groupDataState)

    }

    /**
     * 获取底部资讯
     */
    fun getArticleList(cPage: Int) {

        var map = mutableMapOf<String, Any>().apply {
            put("ProvinceNo", appViewModel.locationInfo.value?.provinceNo ?: 0)
            put("CityNo", appViewModel.locationInfo.value?.cityNo ?: 0)
            put("AreaNo", appViewModel.locationInfo.value?.areaNo ?: 0)
            put("PageIndex", cPage)
            put("PageSize", pageSize)
        }

        requestNoCheck({ apiServiceArticle.getArticleList(map) }, { articleBean ->


            articleBean.Rows.forEach {

                it.Content.logE()
                //提取content 里的图片地址(\.jpg|\.png|(
                val regex = Regex("""http(.*?)(?=")""")
                val matches =
                    regex.findAll(it.Content)
                val imgTags = matches.map { matchResult ->
                    matchResult.value
                }.toMutableList()
//                "提取的图片大小 ${imgTags.size}".logE()

                when (imgTags.size) {
                    0 -> {
                        it.itemType = ArticleAdapter.ArticleTypeText
                    }
                    1 -> {
                        it.itemType = ArticleAdapter.ArticleTypeSingleImg
                        it.imageUrl1 = imgTags[0]
                    }
                    3 -> {
                        it.itemType = ArticleAdapter.ArticleTypeImg3b
                        it.imageUrl1 = imgTags[0]
                        it.imageUrl2 = imgTags[1]
                        it.imageUrl3 = imgTags[2]
                    }
                    else -> {
                        it.itemType = ArticleAdapter.ArticleTypeImg3a
                        if (imgTags.size == 2) {
                            it.imageUrl1 = imgTags[0]
                            it.imageUrl2 = imgTags[1]
                        } else {
                            it.imageUrl1 = imgTags[0]
                            it.imageUrl2 = imgTags[1]
                            it.imageUrl3 = imgTags[2]
                        }
                    }
                }


            }


            articleDataState.paresResult(articleBean)
        }, {
            articleDataState.paresException(it)
        })

    }


    /**
     * 获取首页配置 直接请求到数据就行
     */
    fun getActivityPageConfig() {
        viewModelScope.launch(Dispatchers.IO) {
            val client = OkHttpClient().newBuilder().addInterceptor(
                ChuckerInterceptor.Builder(
                    MyApplication.appContext
                ).build()
            ).build()

//          https://img.yiuxiu.com/YiuXiuMarketPayment/MarketPayment/zljx3_ActivityIndex_330702.js?v=1682220891943
            val request = Request.Builder()
                .url("https://img.yiuxiu.com/YiuXiuMarketPayment/MarketPayment/zljx3_ActivityIndex_${appViewModel.locationInfo?.value?.areaNo?:330702}.js")
                .build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
//                    throw IOException()
                    "Unexpected code $response".logE()
                    return@use
                }
                val responseString = response.body!!.string()

                val pattern = "content:(.*)\\}\\]".toRegex()
                val result = pattern.find(responseString)?.value ?: ""

                val json = result.replace("""\"""", "\"")
                    .replace("""\\"""", "\\\"")
                    .replace("""\\n""", "")
                    .replace("'", "\"")
                    .replace("\n", "")
                    .replace(":\\\"\"", ":\"\"")
                    .replace("content: ", "")
//                    .replace(""""imgWidth":""""","""imgWidth":0""")
//                    .replace(""""imgHeight":""""","""imgHeight":0""")
                json.logFromJson()
                withContext(Dispatchers.Main) {
                    try {
                        var bean: MutableList<ActivityHomeConfigBean> =
                            Gson().fromJson(
                                json,
                                object : TypeToken<MutableList<ActivityHomeConfigBean>>() {}.type
                            )
                        pageConfig.paresResult(bean)
                    } catch (e: Exception) {
                        pageConfig.paresException(e)
                    }
                }
            }
        }


    }

    private val mLocationClient = AMapLocationClient(appContext)


    //异步获取定位结果
    private val mAMapLocationListener = AMapLocationListener {
        lateinit var lD: LocationDataInfo
        if (it != null) {
            if (it.errorCode == 0) {
                //解析定位结果
                "定位信息 $it".logE()
                if(it.address.isEmpty()){
                    locationUiState.value = LocationUiState(isSuccess = false, errorMsg = "获取位置异常,请点击重试")
                    return@AMapLocationListener
                }else{
                    lD = LocationDataInfo(it.address.replace("浙江省", ""),it.district, it.latitude, it.longitude)
                    lD.areaNo = it.adCode.toIntOrNull() ?: 330702         //区编号
                    lD.provinceNo = lD.areaNo.div(10000).times(10000)     //省编号
                    lD.cityNo = lD.areaNo.div(100).times(100)        //市编号

                    locationUiState.value = LocationUiState(isSuccess = true, data = lD)
                }

            } else {
//                lD = LocationDataInfo("获取位置失败,请点击重试", "婺城区",29.074632, 119.646499)
//                appViewModel.locationInfo.postValue(lD)
                "定位失败 ${it.errorCode}+ ${it.errorInfo}".logE()
                locationUiState.value = LocationUiState(isSuccess = false, errorMsg = "获取位置失败,请点击重试")

            }
        } else {
//            lD = LocationDataInfo("获取位置失败,请点击重试","婺城区",29.074632, 119.646499)
            "定位失败 空的".logE()
            locationUiState.value = LocationUiState(isSuccess = false, errorMsg = "获取位置失败,请点击重试")


        }


    }

    //声明AMapLocationClientOption对象
    var mLocationOption = AMapLocationClientOption()

    /**
     * 启动定位
     */
    fun getLocationInfo() {

        mLocationOption.isOnceLocation = true
        mLocationOption.isOnceLocationLatest = true
        mLocationOption.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        //设置定位回调监听
        mLocationClient.setLocationListener(mAMapLocationListener)
        //启动定位

        mLocationClient.setLocationOption(mLocationOption)
        mLocationClient.startLocation()
    }

    override fun onCleared() {
        mLocationClient.stopLocation()
        mLocationClient.onDestroy()

        super.onCleared()
    }

}