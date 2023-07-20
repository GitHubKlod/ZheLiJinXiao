package com.zhili.zljx.bean

data class WriteOffOrderBean(
    var PageIndex: Int = 0, // 1
    var PageSize: Int = 0, // 20
    var Rows: MutableList<Row> = mutableListOf(),
    var Total: Int = 0 // 62
) {
    data class Row(
        var Amount: Double = 0.0, // 2100.0
        var CommissionAmount: Double = 0.0, // 0.0
        var Coupon: MutableList<CouponData> = mutableListOf(),
        var CreateTime: String = "", // 2023-03-27 16:00:08
        var DiscountAmount: Double = 0.0, // 2000.0
        var FactAmount: Double = 0.0, // 0.0
        var FinishTime: String = "", // 2023-03-27 16:00:08
        var JXWalletAmount: Double = 0.0, // 100.0
        var LogoUrl: String = "", // ["https://yiuxiu123.oss-cn-hangzhou.aliyuncs.com/YiuXiuMarketPayment/MarketPayment/202209/47dee27a801d489caf4cea63084b3680.jpg"]
        var Mobile: Any = Any(), // null
        var OrderDiscountSubsidyList: Any = Any(), // null
        var OrderNo: String = "", // COX032716002307643006
        var OrderRemark: String = "",
        var PayType: Int = 0, // 1
        var PayTypeText: String = "", // 微信公众号
        var PlatformOrderNumber: String = "", // 8032716002308164004
        var ShopBearAmount: Double = 0.0, // 2000.0
        var ShopName: String = "", // 无敌门店
        var ShopNo: String = "", // MS091718282210943003
        var Status: Int = 0, // 1
        var StatusText: String = "", // 待支付
        var SubsidyAmount: Double = 0.0, // 0.0
        var UserNo: Int = 0 // 0
    ) {
        data class CouponData(
            var ActivityLabel: String = "",
            var Amount: Double = 0.0, // 1000.0
            var CouponNo: String = "", // ZX032710372313215014
            var CouponType: Int = 0, // 1
            var CouponTypeText: String = "", // 满减
            var CreateTime: String = "", // 2023-03-27 10:37:13
            var FundTarget: Int = 0, // 1
            var FundTargetText: String = "", // 商家承担
            var LimitAmount: Double = 0.0, // 2000.0
            var Name: String = "", // 允许叠加允许组合门店满20减10
            var Status: Int = 0, // 1
            var StatusText: String = "", // 上架
            var VerifyTarget: Int = 0, // 1
            var VerifyTargetText: String = "", // 无限制
            var VerifyType: Int = 0, // 2
            var VerifyTypeText: String = "" // 线下核销
        )
    }
}