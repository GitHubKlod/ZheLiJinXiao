package com.zhili.zljx.bean

data class RmbGrantLogBean(
    var grantLogs: MutableList<GrantLog> = mutableListOf(),
    var totalCount: Int = 0 // 1
) {
    data class GrantLog(
        var ActivityNo: String = "", // wmygulfk
        var ActivitySessionId: Int = 0, // 3
        var ActivitySessionTitle: String = "", // 第3轮
        var ActivityTitle: String = "", // 武义数字人民币消费红包发放
        var CityName: String = "",
        var CountyName: String = "",
        var CreateTime: String = "", // 2023-04-11 16:55:53
        var ECNYs: MutableList<ECNY> = mutableListOf(),
        var IDCard: String = "", // 420620197609252514
        var Id: Int = 0, // 1008
        var Mobile: String = "", // 17871978642
        var PrizeId: Int = 0, // 3
        var ProvinceName: String = "",
        var RealName: String = "", // 易昌渠
        var UserNo: Long = 0 // 362048393421317
    ) {
        data class ECNY(
            var Amount: Double = 0.0, // 30
            var BankName: String = "", // 工商银行
            var CouponNo: String = "", // 4146583342411677696
            var CoverImage: String = "", // https://img.yiuxiu.com/ECNY/20230216092144151185.jpg
            var DetailId: Int = 0, // 2009
            var ECNYId: Int = 0, // 1
            var ExpiryBeginTime: String = "", // 2023-02-21 10:00:00
            var ExpiryEndTime: String = "", // 2023-10-02 10:00:00
            var Explain: String = "", // <p>aa</p>
            var LimitAmount: Double = 0.0, // 6000
            var PrizeTitle: String = "", // 111
            var titleName: String = "", // 111
            var showMore: Boolean = false // 111
        )
    }
}