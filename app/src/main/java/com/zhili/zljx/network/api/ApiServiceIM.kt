package com.zhili.zljx.network.api

import com.zhili.zljx.bean.*
import com.zhili.zljx.bean.base.ApiResponse
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
interface ApiServiceIM {


    /**
     * 获取总未读数量
     */
    @GET("FrontService/v1/MessageCount/GetTotalUnReadCount")
    suspend fun  getTotalUnReadCount():ApiResponse<Int>


}

