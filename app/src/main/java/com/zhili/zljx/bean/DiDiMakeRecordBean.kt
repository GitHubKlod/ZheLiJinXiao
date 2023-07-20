package com.zhili.zljx.bean


data class DiDiMakeRecordBean(
    var `data`: MutableList<Data> = mutableListOf(),
    var totalCount: Int = 0 // 5
) {
    data class Data(
        var ActivityNo: String = "", // 0d3o4ge8
        var ActivitySessionId: Int = 0, // 5
        var ActivitySessionTitle: String = "", // 第4轮
        var ActivityTitle: String = "", // 滴滴测试
        var CityName: String = "",
        var CountyName: String = "",
        var CreateTime: String = "", // 2023-04-13 09:52:30
        var DeleteTime: String = "",
        var ExtendJson: String = "",
        var GrabActivityCategory: Int = 0, // 10
        var IDCard: String = "", // 430523200103061550
        var Id: Int = 0, // 10
        var IsDelete: Int = 0, // 0
        var Mobile: String = "", // 17871978642
        var PrizeId: Int = 0, // 4
        var PrizeTitle: String = "", // 滴滴券包
        var ProvinceName: String = "",
        var RealName: String = "", // 易昌渠
        var Status: Int = 0, // 1
        var StatusStr: String = "", // 中签并奖品全部发放成功
        var UserNo: Long = 0 // 362048393421317
    )
}
