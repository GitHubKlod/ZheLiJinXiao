package com.zhili.zljx.bean


    data class TourLineListBean(
        var DataList: MutableList<Data> = mutableListOf(),
        var Total: Int = 0 // 0
    ) {
        data class Data(
            var AgencyID: Int = 0, // 0
            var ID: Int = 0, // 0
            var LineImage: String = "", // string
            var LinePrice: Double = 0.0, // 0
            var LineTags: MutableList<String> = mutableListOf(),
            var LineTitle: String = "", // string
            var SellNum: Int = 0 // 0
        )
    }
