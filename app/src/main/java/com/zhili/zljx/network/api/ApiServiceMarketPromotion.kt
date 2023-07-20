package com.zhili.zljx.network.api

import com.zhili.zljx.BuildConfig
import com.zhili.zljx.bean.*
import com.zhili.zljx.bean.base.ApiResponse
import okhttp3.MultipartBody

import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.QueryMap


/**
 * 作者　: KLOD
 * 时间　: 2023/4/23
 * 描述　: 网络API MarketPromotion
 */
interface ApiServiceMarketPromotion {


    /**
     * 上传文件
     */
    @Multipart
    @POST("OpenService/v1/Common/UploadFile")
    suspend fun postFile(@Part partLis: List<MultipartBody.Part>): ErrorBean
    /**
     * 获取个人信息
     */
    @GET("OpenService/v1/User/GetHome")
    suspend fun getUserInfo(@QueryMap map: Map<String, @JvmSuppressWildcards Any>): UserInfo


    /**
     * 修改昵称
     */
    @FormUrlEncoded
    @POST("OpenService/v1/User/SetUserNickName")
    suspend fun postChangeNickname(@FieldMap map: Map<String, @JvmSuppressWildcards Any>): ErrorBean


    /**
     * 实名认证
     */
    @FormUrlEncoded
    @POST("OpenService/v1/User/RealNameAuth")
    suspend fun postRealName(@FieldMap map: Map<String, @JvmSuppressWildcards Any>): ErrorBean
    /**
     * 修改用户头像
     */
    @FormUrlEncoded
    @POST("OpenService/v1/User/SetUserHeadPortraitUrl")
    suspend fun postUserHeadImage(@FieldMap map: Map<String, @JvmSuppressWildcards Any>): ErrorBean

    /**
     * 获取普惠券预约纪录
     */
    @GET("OpenService/v1/User/GetMakeRecordsList")
    suspend fun getMakeRecordList(@QueryMap map: Map<String, @JvmSuppressWildcards Any>): CouponMakeRecordBean

}