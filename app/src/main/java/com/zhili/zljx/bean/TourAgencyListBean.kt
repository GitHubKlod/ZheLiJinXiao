package com.zhili.zljx.bean


data class TourAgencyListBean(
    var DataList: List<Data> = listOf(),
    var Total: Int = 0 // 0
) {
    data class Data(
        var AgencyAddress: String = "", // string
        var AgencyName: String = "", // string
        var AgencyTel: String = "", // string
        var BackImage: String = "", // string
        var ID: Int = 0, // 0
        var Introduce: String = "" // string
    )
}
