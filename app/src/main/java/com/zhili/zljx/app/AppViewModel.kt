package com.zhili.zljx.app

import com.zhili.zljx.utils.MMKVUtils
import com.klod.base.BaseViewModel
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import com.zhili.zljx.bean.LocationDataInfo
import com.zhili.zljx.bean.TokenBean
import com.zhili.zljx.bean.UserInfo


/**
 * 作者　: KLOD
 * 时间　: 2023/4/23
 * 描述　:APP全局的ViewModel，可以存放公共数据，当他数据改变时，所有监听他的地方都会收到回调,也可以做发送消息
 * 比如 全局可使用的 地理位置信息，账户信息,App的基本配置等等，
 */

class AppViewModel : BaseViewModel() {

    //App的账户信息
    var userInfo = UnPeekLiveData.Builder<UserInfo?>().setAllowNullValue(true).create()
    //位置信息
    var locationInfo = UnPeekLiveData.Builder<LocationDataInfo?>().setAllowNullValue(true).create()
//    var tokenInfo = UnPeekLiveData.Builder<TokenBean>().setAllowNullValue(true).create()

    //App主题颜色 中大型项目不推荐以这种方式改变主题颜色，比较繁琐耦合，且容易有遗漏某些控件没有设置主题色
//    var appColor = OkLiveData<Int>()

    //App 列表动画
//    var appAnimation = OkLiveData<Int>()

    init {
        //默认值保存的账户信息，没有登陆过则为null
        userInfo.value = MMKVUtils.getUser()

        locationInfo.value = MMKVUtils.getLocationInfo()

//        tokenInfo.value = MMKVUtils.getTokenMVVM()
        //默认值颜色
//        appColor.value = SettingUtil.getColor(appContext)
        //初始化列表动画
//        appAnimation.value = SettingUtil.getListMode()
    }
}