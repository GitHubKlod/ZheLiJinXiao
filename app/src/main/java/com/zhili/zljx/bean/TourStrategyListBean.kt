package com.zhili.zljx.bean


data class TourStrategyListBean(
    var DataList: List<Data> = listOf(),
    var Total: Int = 0 // 0
) {
    data class Data(
        var Id: Int = 0, // 0
        var ImageUrl: String = "", // string
        var Title: String = "" // string
    )
}
