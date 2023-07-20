package com.zhili.zljx.ui.home.mine

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.request
import com.klod.base.ext.requestNoCheck
import com.klod.base.state.ResultState
import com.zhili.zljx.R
import com.zhili.zljx.bean.EnergyPointBean
import com.zhili.zljx.bean.MyBottomGameBean
import com.zhili.zljx.bean.PhoneCodeBean
import com.zhili.zljx.bean.UserInfo
import com.zhili.zljx.bean.base.ApiResponse
import com.zhili.zljx.network.apiServiceHb
import com.zhili.zljx.network.apiServiceMarket

/**
 *@Auther KLOD
 *2023/3/29 16:36
 */
class MineFragmentVm : BaseViewModel() {

    var userInfo = MutableLiveData<ResultState<UserInfo>>()
    var energyPointState = MutableLiveData<ResultState<EnergyPointBean>>()


    var gameData = MutableLiveData<MutableList<MyBottomGameBean>>().apply {
        value = mutableListOf(

            MyBottomGameBean(MineGameAdapter.Game1Type,0, R.mipmap.img_my_tour_coupon_ad,""),
            MyBottomGameBean(MineGameAdapter.Game2Type,R.mipmap.icon_my_game_1, R.mipmap.img_my_game1,"宝箱活动"),
            MyBottomGameBean(MineGameAdapter.Game2Type,R.mipmap.icon_my_game_2, R.mipmap.img_my_game2,"竞猜赢红包"),
            MyBottomGameBean(MineGameAdapter.Game2Type,R.mipmap.icon_my_game_3, R.mipmap.img_my_game3,"能量PK")

        )
    }

    /**
     * 获取个人信息
     */
    fun getMyInfo() {
        requestNoCheck({ apiServiceMarket.getUserInfo( mutableMapOf()) },userInfo)
    }
    /**
     * 获取能量点信息
     */
    fun getEnergyPointInfo() {
        requestNoCheck({ apiServiceHb.getEnergyPointInfo( mutableMapOf()) },energyPointState)
    }

}