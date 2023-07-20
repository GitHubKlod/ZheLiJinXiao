package com.zhili.zljx.bean.request

import com.google.gson.annotations.SerializedName

data class MyBankCardBean(
    @SerializedName("Bank") var bank: String = "", // string
    @SerializedName("CardNo") var cardNo: String = "", // string
    @SerializedName("City") var city: String = "", // string
    @SerializedName("CityCode") var cityCode: String = "", // string
    @SerializedName("CreateTime") var createTime: String = "", // 2023-05-03T11:18:46.175Z
    @SerializedName("Id") var id: Int = 0, // 0
    @SerializedName("Province") var province: String = "", // string
    @SerializedName("ProvinceCode") var provinceCode: String = "", // string
     var backgroundImg: String = "", // string
     var icon: String = "", // string

    @SerializedName("UserNo") var userNo: String = ""// 0
)