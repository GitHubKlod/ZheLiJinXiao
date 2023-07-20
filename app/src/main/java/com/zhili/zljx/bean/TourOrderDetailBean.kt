package com.zhili.zljx.bean


data class TourOrderDetailBean(
    var AgencyID: Int = 0, // 25
    var CancelTime: Any = Any(), // null
    var CouponList: List<String> = listOf(),
    var CreateTime: String = "", // 2023-02-24T11:28:43.13
    var GoodsList: List<Goods> = listOf(),
    var MSCode: String = "", // MS012810232351695109
    var OrderAmount: Double = 0.0, // 400.00
    var OrderNO: String = "", // 167078091225563136
    var OrderType: Int = 0, // 2
    var PayName: String = "", // 微信支付
    var PayStatus: Int = 0, // 1
    var PayTime: String = "", // 2023-02-24T11:28:51.477
    var PayUrl: String = "",
    var ReduceAmount: Double = 0.0, // 200.00
    var Remark: String = "",
    var SpotID: Int = 0, // 67
    var SpotImage: String = "", // https://img.yiuxiu.com/SmartJinKai/Files/202302/4497da7d0a634297907040034ba491f4.png
    var SpotName: String = "", // 大美浦江2日游
    var TravelGroups: String = "" // 轻奢游
) {
    data class Goods(
        var GoodsID: Int = 0, // 3437
        var GoodsImage: String = "", // https://img.yiuxiu.com/SmartJinKai/Files/202302/cc3d9e436ea841d08844dcf3dc3b8743.png
        var GoodsName: String = "", // 车辆费用
        var GoodsNum: Int = 0, // 1
        var GoodsPrice: Double = 0.0 // 50.00
    )
}
