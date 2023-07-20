package com.zhili.zljx.ui.login

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.request
import com.klod.base.ext.requestNoCheck
import com.klod.base.state.ResultState
import com.zhili.zljx.bean.ErrorBean
import com.zhili.zljx.bean.PhoneCodeBean
import com.zhili.zljx.bean.TokenBean
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.network.api.ApiService
import com.zhili.zljx.network.apiServiceSSO

/**
 *@Auther KLOD
 *2023/3/29 19:22
 */
class LoginActivityVm :BaseViewModel() {

    var codeData = MutableLiveData<ResultState<ErrorBean>>()
    var tokenData = MutableLiveData<ResultState<TokenBean>>()
//    var userData = MutableLiveData<ResultState<UserInfo>>()


    fun getCode(phone:String,sessionId :String,sig :String,token:String){

        var map = mutableMapOf<String,Any>()
        map["loginName"] = phone
        map["sessionId"] = sessionId
        map["sig"] = sig
        map["token"] = token
        requestNoCheck({ apiServiceSSO.getPhoneCode(map)},codeData,true)

    }

    fun login(phone:String , code:String){

        var map = mutableMapOf<String,Any>()
        map["client_id"] = ApiService.SSO_CLIENT_ID
        map["client_secret"] = ApiService.SSO_CLIENT_SECRET
        map["grant_type"] = "shortmessage_code"
        map["scope"] = "Payment openid profile CarShow offline_access"
        map["loginname"] = phone
        map["code"] = code
        requestNoCheck({ apiServiceSSO.login(map)},tokenData,true)

    }

}