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
 * 描述　: 网络API 数币
 */
interface ApiServiceECNY {

  /**
     * 修改用户头像
     */
//    @FormUrlEncoded
//    @POST("my/capital")
//    suspend fun getMyCapital(@FieldMap map: Map<String, @JvmSuppressWildcards Any>): MyAccountCapitalBean


    /**
     * 获取数币优惠券列表
     */
    @GET("FrontService/v1/MakeActivityGrantLog/Pager")
    suspend fun getRmbGrantLog(@QueryMap map:Map<String,@JvmSuppressWildcards Any>):ApiResponse<RmbGrantLogBean>
    /**
     * 获取滴滴优惠券列表
     */
    @GET("FrontService/v1/GrantLog/DiDiPager")
    suspend fun getDiDiGrantLog(@QueryMap map:Map<String,@JvmSuppressWildcards Any>):ApiResponse<DiDiGrantLogBean>
  /**
     * 获取数币预约记录
     */
    @GET("FrontService/v1/MakeActivityMakeLog/Pager")
    suspend fun getECNYMakeRecordList(@QueryMap map:Map<String,@JvmSuppressWildcards Any>):ApiResponse<ECNYMakeRecordBean>

  /**
     * 获取滴滴券预约记录
     */
    @GET("FrontService/v1/GrabActivityJoinLog/Pager")
    suspend fun getDiDiMakeRecordList(@QueryMap map:Map<String,@JvmSuppressWildcards Any>):ApiResponse<ECNYMakeRecordBean>


}