package com.zhili.zljx.ui.home.mine.setting

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.requestNoCheck
import com.klod.base.state.ResultState
import com.zhili.zljx.bean.ErrorBean
import com.zhili.zljx.network.apiServiceMarket
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

/**
 *@Auther KLOD
 *2023/4/4 11:17
 */
class SettingActivityVm :BaseViewModel() {

    var changeNicknameResultState = MutableLiveData<ResultState<ErrorBean>>()
    var headUrlResultState = MutableLiveData<ResultState<ErrorBean>>()
    var uploadResultState = MutableLiveData<ResultState<ErrorBean>>()
    fun postChangeNickname(nickname:String){

        val map = mutableMapOf<String,Any>().apply {
            put("NickName",nickname)
        }

        requestNoCheck({ apiServiceMarket.postChangeNickname(map)},changeNicknameResultState,true)

    }
    fun postUserHeadImage(headUrl:String){

        val map = mutableMapOf<String,Any>().apply {
            put("HeadPortraitUrl",headUrl)
        }

        requestNoCheck({ apiServiceMarket.postUserHeadImage(map)},headUrlResultState,true)

    }


    fun postFile(file: File){
        //1.创建MultipartBody.Builder对象
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        //3.调用MultipartBody.Builder的addFormDataPart()方法添加表单数据
        builder.addFormDataPart("file", "avatar.jpg", requestFile)
        //4.创建List<MultipartBody.Part> 集合，
        //  调用MultipartBody.Builder的build()方法会返回一个新创建的MultipartBody
        //  再调用MultipartBody的parts()方法返回MultipartBody.Part集合
        val parts = builder.build().parts
        requestNoCheck({ apiServiceMarket.postFile(parts)},
            uploadResultState
            , true
            ,"上传中...")

    }


}