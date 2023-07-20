package com.zhili.zljx.bean

data class WriteOffOrderDetailBean(
    var Amount: Double = 0.0, // 20000.0
    var CommissionAmount: Double = 0.0, // 120.000000
    var Coupon: MutableList<CouponData> = mutableListOf(),
    var CreateTime: String = "", // 2023-02-21 13:37:32
    var DiscountAmount: Double = 0.0, // 4.0
    var FactAmount: Double = 0.0, // 19996.0
    var FinishTime: String = "",
    var JXWalletAmount: Double = 0.0, // 0.0
    var LogoUrl: String = "", // ["https://img.yiuxiu.com/YiuXiuPayment/Payment/202301/f3a5275a61d6404c933bca879d62597b.jpg"]
    var OrderDiscountList: MutableList<OrderDiscount> = mutableListOf(),
    var OrderDiscountSubsidyList: MutableList<Any> = mutableListOf(),
    var OrderNo: String = "", // COL022113372332564232
    var OrderRemark: Any = Any(), // null
    var PayType: Int = 0, // 1
    var PayTypeText: String = "", // 微信公众号
    var PlatformOrderNumber: String = "", // 8022113372332810198
    var ShopBearAmount: Double = 0.0, // 4.0
    var ShopName: String = "", // 浙江之鑫文化旅游有限公司
    var ShopNo: String = "", // MS013110082338335240
    var Status: Int = 0, // 2
    var StatusText: String = "", // 已取消
    var SubsidyAmount: Double = 0.0, // 0.0
    var UserNo: Long = 0 // 362140191735897
) {
    data class CouponData(
        var ActivityLabel: Any = Any(), // null
        var Amount: Double = 0.0, // 4.0
        var CouponNo: String = "", // ZX102412572209114009
        var CouponType: Int = 0, // 1
        var CouponTypeText: String = "", // 满减
        var CreateTime: String = "", // 2022-10-24 12:57:09
        var FundTarget: Int = 0, // 1
        var FundTargetText: String = "", // 商家承担
        var LimitAmount: Double = 0.0, // 5.0
        var Name: String = "", // 满0.05减0.04
        var Status: Int = 0, // 1
        var StatusText: String = "", // 上架
        var VerifyTarget: Int = 0, // 1
        var VerifyTargetText: String = "", // 无限制
        var VerifyType: Int = 0, // 1
        var VerifyTypeText: String = "" // 无限制
    )

    data class OrderDiscount(
        var Amount: Double = 0.0, // 4.0
        var CouponNo: String = "", // ZX102412572209114009
        var Name: String = "", // 满0.05减0.04
        var ShopBearAmount: Double = 0.0 // 4.0
    )
}