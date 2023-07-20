package com.zhili.zljx.network.api

import com.zhili.zljx.bean.*
import com.zhili.zljx.bean.base.ApiResponse

import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * 作者　: KLOD
 * 时间　: 2023/4/23
 * 描述　: 网络API 数币
 */
interface ApiServiceTour {


    /**
     * 获取文旅订单列表
     */
    @POST("OrderInfo/GetOrderList")
    @FormUrlEncoded
    suspend fun getTourOrderList(@FieldMap map: Map<String,@JvmSuppressWildcards Any>):ApiResponse<TourOrderBean>

    /**
     * 获取文旅订单详情
     */
    @POST("OrderInfo/GetOrderDetail")
    @FormUrlEncoded
    suspend fun getTourOrderDetailInfo(@FieldMap map: Map<String,@JvmSuppressWildcards Any>):ApiResponse<TourOrderDetailBean>


    /**
     * 取消文旅订单
     */
    @POST("OrderInfo/CancelOrder")
    @FormUrlEncoded
    suspend fun postCancelOrder(@FieldMap map: Map<String,@JvmSuppressWildcards Any>):ErrorBean

    /**
     * 获取旅行社分类列表
     */
    @POST("ShopInfo/GetTravelAgencyClassList")
    @FormUrlEncoded
    suspend fun getTourAgencyClassList(@FieldMap map: Map<String,@JvmSuppressWildcards Any>):ApiResponse<MutableList<TourAgencyClassBean>>

   /**
     * 获取旅行社列表
     */
    @POST("ShopInfo/GetTravelAgencyList")
    @FormUrlEncoded
    suspend fun getTourAgencyList(@FieldMap map: Map<String,@JvmSuppressWildcards Any>):ApiResponse<TourAgencyListBean>
   /**
     * 获取旅行线路列表
     */
    @POST("TravelLine/GetTravelLineList")
    @FormUrlEncoded
    suspend fun getTourLineList(@FieldMap map: Map<String,@JvmSuppressWildcards Any>):ApiResponse<TourLineListBean>

    /**
     * 获取旅行攻略列表
     */
    @POST("TourismStrategy/GetTourismStrategyList")
    @FormUrlEncoded
    suspend fun getTourStrategyList(@FieldMap map: Map<String,@JvmSuppressWildcards Any>):ApiResponse<TourStrategyListBean>


    /**
     * 获取文旅券地区列表
     */
    @POST("Advertise/GetRegionList")
    @FormUrlEncoded
    suspend fun getTourRegionList(@FieldMap map: Map<String,@JvmSuppressWildcards Any>):ApiResponse<MutableList<TourRegionListBean>>

    /**
     * 获取地区券入口列表
     */
    @POST("Advertise/GetAdvPageList")
    @FormUrlEncoded
    suspend fun getTourRegionCouponList(@FieldMap map: Map<String,@JvmSuppressWildcards Any>):ApiResponse<TourRegionCouponBean>


    /**
     * 获取旅行社信息
     */
    @POST("ShopInfo/GetTravelAgencyInfo")
    @FormUrlEncoded
    suspend fun getTourAgencyInfo(@FieldMap map: Map<String,@JvmSuppressWildcards Any>):ApiResponse<AgencyDetailBean>



    /**
     * 获取旅行线路详情
     */
    @POST("TravelLine/GetTravelLineDetail")
    @FormUrlEncoded
    suspend fun getTourLineDetail(@FieldMap map: Map<String,@JvmSuppressWildcards Any>):ApiResponse<TourLineDetailBean>

}