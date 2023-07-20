package com.zhili.zljx.bean

import com.google.gson.annotations.SerializedName

data class CouponMakeRecordBean(
    var PageIndex: Int = 0, // 1
    var PageSize: Int = 0, // 20
    var Rows: MutableList<Row> = mutableListOf(),
    var Total: Int = 0 // 5
) {
    data class Row(
        var AuditStatus: Int = 0, // 0
        var AuditStatusText: String = "", // 0
        var DrawStatus: Int = 0, // 2
        var DrawStatusText: String = "", // 已开奖
        var DrawTime: String = "", // 2023-03-24 10:00:00
        var Extend: ExtendData = ExtendData(),
        var GrantMode: Int = 0, // 0
        var GrantModeText: String = "", // 0
        var GrantNo: String = "",
        var GrantStatus: Int = 0, // 0
        var GrantStatusText: String = "", // 0
        var MakeActivityMakeNo: String = "", // MAM032111162317350650
        var MakeActivityName: String = "", // 婺城区普惠券预约
        var MakeActivityNo: String = "", // MA012110532318420859
        var MakeActivitySessionID: Int = 0, // 78
        var MakeActivitySessionName: String = "", // 第九轮
        var MakeActivitySessionNumber: Int = 0, // 9
        var PrizeName: String = "",
        var Status: Int = 0, // 3
        var StatusText: String = "" // 未中签
    ) {
        data class ExtendData(
            @SerializedName("手机号码") var phoneNumber: String = "", //
            @SerializedName("真实姓名") var realName: String = "", //
            @SerializedName("身份证号码", alternate = ["证件号码"]) var IdCard: String = "", // 330726199709042515
            @SerializedName("领券城市") var cityName: MutableList<CityData> = mutableListOf()
        ) {
            data class CityData(
                var label: String = "", // 婺城区
                var value: Int = 0 // 330702
            )
        }
    }
}