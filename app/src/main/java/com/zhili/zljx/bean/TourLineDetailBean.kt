package com.zhili.zljx.bean

data class TourLineDetailBean(
    var AgencyAddress: String = "", // string
    var AgencyID: Int = 0, // 0
    var AgencyName: String = "", // string
    var AgencyTel: String = "", // string
    var ID: Int = 0, // 0
    var LineImages: MutableList<String> = mutableListOf(),
    var LineNotice: String = "", // string
    var LinePrice: Double = 0.0, // 0
    var LineTags: MutableList<String> = mutableListOf(),
    var LineTitle: String = "", // string
    var SellNum: Int = 0, // 0
    var SpotList: MutableList<Spot> = mutableListOf(),
    var TravelGroups: MutableList<TravelGroup> = mutableListOf(),
    var TripInformation: String = "" // string
) {
    data class Spot(
        var GoodsList: MutableList<Goods> = mutableListOf(),
        var SpotID: Int = 0, // 0
        var SpotName: String = "" // string
    ) {
        data class Goods(
            var GoodsID: Int = 0, // 0
            var GoodsImage: String = "", // string
            var GoodsName: String = "", // string
            var GoodsNum: Int = 0, // 0
            var GoodsPrice: Double = 0.0 // 0
        )
    }

    data class TravelGroup(
        var AgencyImage: String = "", // string
        var EndTime: String = "", // 2023-05-03T02:51:29.383Z
        var GroupName: String = "", // string
        var ID: Int = 0 // 0
    )
}