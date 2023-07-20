package com.zhili.zljx.bean


data class TourOrderBean(
    var DataList: MutableList<Data> = mutableListOf(),
    var Total: Int = 0 // 86
) {
    data class Data(
        var CreateTime: String = "", // 2023-04-10T12:36:58.53
        var OrderAmount: Double = 0.0, // 0.10
        var OrderNO: String = "", // 183402722538553344
        var OrderType: Int = 0, // 2
        var PayStatus: Int = 0, // 1
        var PayUrl: String = "",
        var SpotImage: String = "", // https://img.yiuxiu.com/CulturalTourism/Files/202304/2e76a0886ff34bffabd5fa22930783e8.jpg?x-oss-process=image/resize,m_fill,h_73,w_104
        var SpotName: String = "" // 测试佣金路线
    )
}
