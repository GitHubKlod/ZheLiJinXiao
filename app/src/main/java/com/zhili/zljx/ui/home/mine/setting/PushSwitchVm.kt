package com.zhili.zljx.ui.home.mine.setting

import com.klod.base.BaseViewModel
import com.klod.base.livedata.OkLiveData
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.utils.MMKVUtils


class PushSwitchVm: BaseViewModel() {


    val goods  = OkLiveData<Boolean>()
    val article  = OkLiveData<Boolean>()
    val jPush  = OkLiveData<Boolean>()

    val cnxh  = OkLiveData<Boolean>()
    val dxts  = OkLiveData<Boolean>()



    fun saveGoodsPushStatus(boolean: Boolean){
        MMKVUtils.putBoolean(Contacts.goodsPushSwitch,boolean)
        goods.value = boolean
    }
    fun saveArticlePushStatus(boolean: Boolean){
        MMKVUtils.putBoolean(Contacts.articlePushSwitch,boolean)
        article.value = boolean
    }
    fun saveJgPushStatus(boolean: Boolean){
        MMKVUtils.putBoolean(Contacts.jPushSwitch,boolean)
        jPush.value = boolean
    }
    fun saveCnxhStatus(boolean: Boolean){
        MMKVUtils.putBoolean(Contacts.cnxhSwitch,boolean)
        cnxh.value = boolean
    }
    fun saveDxtsStatus(boolean: Boolean){
        MMKVUtils.putBoolean(Contacts.dxtsSwitch,boolean)
        dxts.value = boolean
    }

}