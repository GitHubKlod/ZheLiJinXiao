package com.zhili.zljx.bean


data class DiDiGrantLogBean(
    var grantLogs: List<GrantLog> = listOf(),
    var totalCount: Int = 0 // 1
) {
    data class GrantLog(
        var ActivityCategory: Int = 0, // 2
        var ActivityNo: String = "", // 0d3o4ge8
        var ActivitySessionId: Int = 0, // 5
        var ActivitySessionTitle: String = "", // 第4轮
        var ActivityTitle: String = "", // 滴滴测试
        var BankActivityNo: String = "", // lADzDs4y82jhuHNQ8FfFQA==
        var BatchMark: String = "", // 20230411173027
        var CityName: String = "", // null
        var CountyName: String = "", // null
        var CreateTime: String = "", // 2023-04-11 17:30:27
        var DeleteTime: String = "", // null
        var Display: Boolean = false, // true
        var ECNYs: MutableList<ECNY> = mutableListOf(),
        var ExecuteCallApiErrCode: String = "", // 0
        var ExecuteCallApiErrMessage: String = "", // success
        var ExecuteCompleteMark: String = "", // vnitbi0tb2tc
        var ExecuteNumber: Int = 0, // 1
        var FailNumber: Int = 0, // 0
        var GrantMark: Int = 0, // 1
        var GrantResult: String = "", // 发放成功
        var HisBatchMark: String = "", // 20230411173027
        var IDCard: String = "", // 430523200103061550
        var Id: Int = 0, // 9
        var IsDelete: Int = 0, // 0
        var LastCallApiTime: String = "", // 2023-04-11 17:30:27
        var Mobile: String = "", // 17871978642
        var PrimaryKey: String = "", // cea449a5-7c4a-4a44-95f2-20e2c6b51ba2
        var PrizeId: Int = 0, // 4
        var PrizeTitle: String = "", // 滴滴券包
        var ProvinceName: String = "", // null
        var PushWeChat: Boolean = false, // false
        var RealName: String = "", // 易昌渠
        var SourceId: Int = 0, // 9
        var UserNo: Long = 0 // 362048393421317
    ) {
        data class ECNY(
            var Amount: Double = 0.0, // 20
            var BankName: String = "", // 滴滴
            var CouponNo: String = "", // e95a544430e04882a12b4ac3381847d1
            var CouponType: Int = 0, // 100
            var CouponTypeStr: String = "", // 打折券
            var DiDiCouponName: String = "", // 券名称
            var CoverImage: String = "", // https://img.yiuxiu.com/ECNY/20230327060714739710.jpg
            var DetailId: Int = 0, // 37
            var DiDiCouponId: String = "", // 2724c3e65ca5ab0013
            var DiDiExpireTime: String = "", // 2023-04-12 23:59:59
            var DiDiProductId: String = "", // 210
            var DiDiStatus: Int = 0, // 2
            var DiDiStatusStr: String = "", // 绑定
            var Discount: Double = 0.0, // 90
            var ECNYId: Int = 0, // 22
            var ExpiryBeginTime: String = "", // null
            var ExpiryEndTime: String = "", // 2023-04-12 23:59:59
            var Explain: String = "", // <p>限快车8-10点使用</p>
            var LimitAmount: Double = 0.0, // 0
            var showMore: Boolean = false, // 0
            var PrizeTitle: String = "" // 滴滴券包
        )
    }
}

