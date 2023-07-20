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
interface ApiServiceMarketPayment {

    /**
     * 获取优惠券列表
     */
    @GET("OpenService/v1/User/GetCouponList")
    suspend fun getCouponList(@QueryMap map:Map<String,@JvmSuppressWildcards Any>):MyCouponListBean


    /**
     * 获取核销订单列表
     */
    @GET("OpenService/v1/Order/GetList")
    suspend fun getWriteOffOrderList(@QueryMap map:Map<String,@JvmSuppressWildcards Any>):WriteOffOrderBean


    /**
     * 取消核销订单
     */
    @POST("OpenService/v1/Order/CancelOrder")
    @FormUrlEncoded
    suspend fun postCancelOrder(@FieldMap map: Map<String,@JvmSuppressWildcards Any>):ErrorBean

    /**
     * 获取核销订单详情
     */
    @GET("OpenService/v1/Order/GetOrder")
    suspend fun getWriteOffOrderDetail(@QueryMap map: Map<String,@JvmSuppressWildcards Any>):WriteOffOrderDetailBean



}