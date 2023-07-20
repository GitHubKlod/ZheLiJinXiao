package com.zhili.zljx.bean

data class ECNYMakeRecordBean(
    var `data`: MutableList<Data> = mutableListOf(),
    var totalCount: Int = 0 // 2
) {
    data class Data(
        var ActivityNo: String = "", // 572spvk0
        var ActivitySessionId: Int = 0, // 29
        var ActivitySessionTitle: String = "", // 第7轮
        var ActivityTitle: String = "", // 测试推送
        var CityName: String = "", // 金华市
        var CountyName: String = "", // 婺城区
        var CreateTime: String = "", // 2023-03-22 13:38:02
        var DeleteTime: String = "",
        var ExtendJson: String = "",
        var IDCard: String = "", // 330726199709042515
        var Id: Int = 0, // 399744
        var IsDelete: Int = 0, // 0
        var MakeActivityCategory: Int = 0, // 0
        var Mobile: String = "", // 15257925260
        var OpenTime: String = "", // 2023-03-23 00:00:00
        var ProvinceName: String = "", // 浙江省
        var RealName: String = "", // 傅楚
        var Status: Int = 0, // 0
        var StatusStr: String = "", // 未开奖
        var UserNo: Long = 0 // 387941622395077
    )
}