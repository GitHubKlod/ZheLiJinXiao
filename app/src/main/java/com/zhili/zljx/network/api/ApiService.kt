package com.zhili.zljx.network.api

import com.zhili.zljx.BuildConfig
import com.zhili.zljx.bean.*

import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

import retrofit2.http.POST


/**
 * 作者　: KLOD
 * 时间　: 2023/4/23
 * 描述　: 网络API
 */
interface ApiService {

    companion object {
        const val SERVER_URL_SSO = BuildConfig.BASE_SSO_URL
        const val SERVER_URL_MARKET_PROMOTION = BuildConfig.BASE_MARKET_PROMOTION_URL
        const val SERVER_URL_MARKET_PAYMENT = BuildConfig.BASE_MARKET_PAYMENT_URL
        const val SERVER_URL_HB = BuildConfig.BASE_MARKET_HB_URL
        const val SERVER_URL_ECNY = BuildConfig.BASE_ECNY_URL
        const val SERVER_URL_TOUR = BuildConfig.BASE_TOUR_URL
        const val SERVER_URL_CIRCLE = BuildConfig.BASE_CIRCLE_URL
        const val SERVER_URL_CONFIG = BuildConfig.BASE_CONFIG_URL
        const val SERVER_URL_IM = BuildConfig.BASE_IM_URL

        const val SSO_CLIENT_ID = BuildConfig.BASE_SSO_CLIENT_ID
        const val SSO_CLIENT_SECRET = BuildConfig.BASE_SSO_CLIENT_SECRET
    }

    /**
     * 获取活动页
     */
    @GET("YiuXiuMarketPayment/MarketPayment/zljx3_ActivityIndex_330702.js")
    suspend fun getActivityConfig(): String

    /**
     * 获取验证码
     */
    @POST("Auth/SendCode")
    @FormUrlEncoded
    suspend fun getPhoneCode(@FieldMap map: Map<String, @JvmSuppressWildcards Any>):ErrorBean

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("connect/token")
    suspend fun login(@FieldMap map: Map<String, @JvmSuppressWildcards Any>): TokenBean






}