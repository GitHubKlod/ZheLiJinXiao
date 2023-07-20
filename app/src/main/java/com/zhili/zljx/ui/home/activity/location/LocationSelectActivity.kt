package com.zhili.zljx.ui.home.activity.location

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.OnItemClickListener
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.klod.base.ext.util.logE
import com.zhili.zljx.app.appViewModel

import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.bean.LocationDataInfo
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.databinding.ActivityAreaSelectBinding
import com.zhili.zljx.ext.goneView
import com.zhili.zljx.ext.toJson
import com.zhili.zljx.ui.home.activity.location.adapter.LocationCityAdapter
import com.zhili.zljx.ui.home.activity.location.adapter.LocationCityHistoryAdapter

import com.zhili.zljx.utils.MMKVUtils

/**
 *@Auther KLOD
 *2023/4/21 20:41
 */
class LocationSelectActivity :
    BaseMvvmActivity<LocationSelectActivityVm, ActivityAreaSelectBinding>() {

    //    330702	   婺城区
//    330703	   金东区
//    330723	   武义县
//    330726	   浦江县
//    330727	   磐安县
//    330781	   兰溪市
//    330782	   义乌市
//    330783	   东阳市
//    330784	   永康市
    private val cityData by lazy {
        mutableListOf(
            LocationDataInfo(
                address = MMKVUtils.getLocationInfo()?.address ?: "请点击重新获取位置",
                district = "开发区",
                provinceNo = 330000, cityNo = 330700, areaNo = 330792,
            ),
            LocationDataInfo(
                address = MMKVUtils.getLocationInfo()?.address ?: "请点击重新获取位置",
                district = "婺城区",
                provinceNo = 330000, cityNo = 330700, areaNo = 330702,
            ),
            LocationDataInfo(
                address = MMKVUtils.getLocationInfo()?.address ?: "请点击重新获取位置",
                district = "金义新区",
                provinceNo = 330000, cityNo = 330700, areaNo = 330703,
            ),
            LocationDataInfo(
                address = MMKVUtils.getLocationInfo()?.address ?: "请点击重新获取位置",
                district = "武义县",
                provinceNo = 330000, cityNo = 330700, areaNo = 330723,
            ),
            LocationDataInfo(
                address = MMKVUtils.getLocationInfo()?.address ?: "请点击重新获取位置",
                district = "浦江县",
                provinceNo = 330000, cityNo = 330700, areaNo = 330726,
            ),
            LocationDataInfo(
                address = MMKVUtils.getLocationInfo()?.address ?: "请点击重新获取位置",
                district = "磐安县",
                provinceNo = 330000, cityNo = 330700, areaNo = 330727,
            ),
            LocationDataInfo(
                address = MMKVUtils.getLocationInfo()?.address ?: "请点击重新获取位置",
                district = "兰溪市",
                provinceNo = 330000, cityNo = 330700, areaNo = 330781,
            ),
            LocationDataInfo(
                address = MMKVUtils.getLocationInfo()?.address ?: "请点击重新获取位置",
                district = "义乌市",
                provinceNo = 330000, cityNo = 330700, areaNo = 330782,
            ),
            LocationDataInfo(
                address = MMKVUtils.getLocationInfo()?.address ?: "请点击重新获取位置",
                district = "东阳市",
                provinceNo = 330000, cityNo = 330700, areaNo = 330783,
            ),
            LocationDataInfo(
                address = MMKVUtils.getLocationInfo()?.address ?: "请点击重新获取位置",
                district = "永康市",
                provinceNo = 330000, cityNo = 330700, areaNo = 330784,
            ),
        )
    }



    private var historyAdapter = LocationCityHistoryAdapter()
    private var cityAdapter = LocationCityAdapter()


    private var historyData = mutableListOf<LocationDataInfo>()
    private var historyData1 = mutableListOf<LocationDataInfo>()

    override fun initView(savedInstanceState: Bundle?) {



        historyData = Gson().fromJson(
            MMKVUtils.getString(Contacts.CitySelectHistory, "[]"),
            object : TypeToken<MutableList<LocationDataInfo>>() {}.type
        )
        historyData1.addAll(historyData)

        mViewBind.apply {
            titleBar.titleTv.text = "选择地区"
            titleBar.ivBack.setOnClickListener { finish() }
            tv1.goneView(historyData.isEmpty())
        }
        //历史记录
        mViewBind.selectHistoryRv.apply {

            //设置布局管理器  流式布局 盒子布局
            val flexBoxLayoutManager = FlexboxLayoutManager(context).apply {
                //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical和horizontal。
                flexDirection = FlexDirection.ROW //主轴为水平方向，起点在左端。
                //flexWrap 默认情况下 Flex 跟 LinearLayout 一样，都是不带换行排列的，但是flexWrap属性可以支持换行排列。
                flexWrap = FlexWrap.WRAP //按正常方向换行
                //justifyContent 属性定义了项目在主轴上的对齐方式。
                justifyContent = JustifyContent.FLEX_START //交叉轴的起点对齐。
            }
            layoutManager = flexBoxLayoutManager

            adapter = historyAdapter
            historyAdapter.submitList(historyData1)
        }
        //城市选择
        mViewBind.selectRv.apply {
            layoutManager = LinearLayoutManager(this@LocationSelectActivity)

            adapter  = cityAdapter

            cityAdapter.submitList(cityData)
        }

        //点击选择了 地区
        cityAdapter.setOnItemClickListener(clickListener)
        historyAdapter.setOnItemClickListener(clickListener)

    }

    private val clickListener = object : OnItemClickListener<LocationDataInfo>{
        override fun invoke(adapter: BaseQuickAdapter<LocationDataInfo, *>, view: View, position: Int) {


            //历史记录去重
            historyData = historyData.filter {
                it.district!=adapter.items[position].district
            }.toMutableList()
            //添加到历史记录
            historyData.add(0,adapter.items[position])
            //保存历史记录
            MMKVUtils.putString(Contacts.CitySelectHistory, historyData.toJson())
//            "${MMKVUtils.getLocationInfo()?.address ?: "请点击重新获取位置"}AAA".logE()

//            showToast("选择了"+adapter.items[position].district+adapter.items[position].address)

            //保存选中的区域 让其他观察者监听到
            appViewModel.locationInfo.value = adapter.items[position]

            finish()

        }

    }

    override fun createObserver() {


    }
}