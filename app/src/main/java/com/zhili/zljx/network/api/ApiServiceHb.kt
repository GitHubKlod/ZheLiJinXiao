package com.zhili.zljx.network.api

import com.zhili.zljx.bean.*
import com.zhili.zljx.bean.base.ApiResponse
import com.zhili.zljx.bean.request.BankCardJsonBean
import com.zhili.zljx.bean.request.CommonRequestBean
import com.zhili.zljx.bean.request.MyBankCardBean
import com.zhili.zljx.bean.request.UnBindCardBean
import retrofit2.http.Body

import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap


/**
 * 作者　: KLOD
 * 时间　: 2023/4/23
 * 描述　: 网络API 数币
 */
interface ApiServiceHb {
    /**
     * 获取能量点
     */
    @FormUrlEncoded
    @POST("energypoint/getenergypointinfo")
    suspend fun getEnergyPointInfo(@FieldMap map: Map<String, @JvmSuppressWildcards Any>): EnergyPointBean

    /**
     * 获取红包数据
     */
    @FormUrlEncoded
    @POST("my/reddot")
    suspend fun getMyRedDot(@FieldMap map: Map<String, @JvmSuppressWildcards Any>): MyAccountRedDotBean

    /**
     * 获取资产数据
     */
    @FormUrlEncoded
    @POST("my/capital")
    suspend fun getMyCapital(@FieldMap map: Map<String, @JvmSuppressWildcards Any>): MyAccountCapitalBean

    /**
     * 获取网页代码
     */
    @GET("explain/get")
    suspend fun getHtmlCode(@QueryMap map: Map<String, @JvmSuppressWildcards Any>): ErrorBean

    /**
     * 获取用户收货地址
     */
    @GET("useraddress/detail")
    suspend fun getUserAddress(@QueryMap map: Map<String, @JvmSuppressWildcards Any>): MyAddressBean

    /**
     * 编辑用户收货地址
     */
//    @Headers("Content-Type: application/json;charset=UTF-8")
//    @POST("useraddress/edit")
//    @FormUrlEncoded
//    suspend fun postUserAddress(@FieldMap map: Map<String, @JvmSuppressWildcards Any>): ErrorBean
    @POST("useraddress/edit")
    suspend fun postUserAddress(@Body addressBean: MyAddressBean): ErrorBean


    /**
     * 获取用户银行卡列表
     */
    @GET("userCard/list")
    suspend fun getUserBankCardList(@QueryMap map: Map<String, @JvmSuppressWildcards Any>): ApiResponse<MutableList<MyBankCardBean>>
   /**
     * 获取我的基本信息
     */
    @GET("usercard/getmyinfo")
    suspend fun getMyInfo(@QueryMap map: Map<String, @JvmSuppressWildcards Any>): BankMyInfoBean


    /**
     * 删除银行卡
     */
    @POST("userCard/delete")
    suspend fun postDeleteBankCard(@Body unBindCardBean: UnBindCardBean): ErrorBean

   /**
     * 删除银行卡   发送短信
     */
    @POST("usercard/sendsms")
    suspend fun sendSms(@Body commonRequestBean: CommonRequestBean): ErrorBean


    /**
     * 添加银行卡
     */
//    @Headers("Content-Type: application/json;charset=UTF-8")
//    @POST("useraddress/edit")
//    @FormUrlEncoded
//    suspend fun postUserAddress(@FieldMap map: Map<String, @JvmSuppressWildcards Any>): ErrorBean
    @POST("usercard/add")
    suspend fun postAddBankCard(@Body cardJsonBean: BankCardJsonBean): ErrorBean


}