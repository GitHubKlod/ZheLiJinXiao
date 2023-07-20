package com.zhili.zljx.bean

data class TourRegionCouponBean(
    var DataList: MutableList<Data> = mutableListOf(),
    var Total: Int = 0 // 1
) {
    data class Data(
        var ImageUrl: String = "", // https://img.yiuxiu.com/CulturalTourism/Files/202303/2f4c044c1fe2402898c24f874e556493.jpg
        var LinkUrl: String = "" // 阿斯达四大
    )
}