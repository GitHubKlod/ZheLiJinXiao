package com.zhili.zljx.bean

data class ActivityGroupBean(
    var PageIndex: Int = 0, // 1
    var PageSize: Int = 0, // 20
    var Rows: MutableList<Row> = mutableListOf(),
    var Total: Int = 0 // 9
) {
    data class Row(
        var AreaNo: String = "", // 330792
        var BrowseNumber: Int = 0, // 2438
        var CityNo: String = "", // 330700
        var CollectNumber: Int = 0, // 3
        var CommentNumber: Int = 0, // 10
        var CreateTime: String = "", // 2022-12-14 15:43:00
        var HeadPortraitUrl: String = "",
        var LikeNumber: Int = 0, // 8
        var Media: MutableList<MediaData> = mutableListOf(),
        var NickName: String = "",
        var ProvinceNo: String = "", // 330000
        var SubjectID: String = "", // 107
        var Title: String = "", // 商家如何入驻进件浙里金消？
        var UserNo: Long = 0 // 569029958670853
    ) {
        data class MediaData(
            var FileName: String = "", // 864910c4c1d24966930439b1f5679d67.jpg
            var FileUrl: String = "" // https://yiuxiu123.oss-cn-hangzhou.aliyuncs.com/YiuXiuMarketPayment/MarketPayment/202212/864910c4c1d24966930439b1f5679d67.jpg
        )
    }
}