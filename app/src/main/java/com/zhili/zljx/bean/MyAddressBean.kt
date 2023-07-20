package com.zhili.zljx.bean

import com.google.gson.annotations.SerializedName

data class MyAddressBean(
    @SerializedName("Address") var address: String = "", // string
    @SerializedName("City") var city: String = "", // string
    @SerializedName("CityCode") var cityCode: String = "", // string
    @SerializedName("County") var county: String = "", // string
    @SerializedName("CountyCode") var countyCode: String = "", // string
    @SerializedName("CreateTime") var createTime: String = "", // 2023-05-03T05:56:22.707Z
    @SerializedName("DeliveryMode") var deliveryMode: String = "", // string
    @SerializedName("DeliveryTime") var deliveryTime: String = "", // string
    @SerializedName("Id") var id: String = "",
    @SerializedName("Province") var province: String = "", // string
    @SerializedName("ProvinceCode") var provinceCode: String = "", // string
    @SerializedName("Recipient") var recipient: String = "", // string
    @SerializedName("SubAddr") var subAddr: String = "", // string
    @SerializedName("Telephone") var telephone: String = "", // string
    @SerializedName("UserNo") var userNo: String = ""
)