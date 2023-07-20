package com.zhili.zljx.bean

data class ActivityGroupDetailBean(
    var AreaNo: String = "", // 330792
    var BrowseNumber: Int = 0, // 1106
    var CityNo: String = "", // 330700
    var CollectNumber: Int = 0, // 2
    var CommentNumber: Int = 0, // 2
    var Content: String = "", // [{content:"如下图",media:[{"FileUrl":"https://yiuxiu123.oss-cn-hangzhou.aliyuncs.com/YiuXiuMarketPayment/MarketPayment/202212/dfaa4ca25b314c8981cfbc13a305277e.jpg","FileName":"dfaa4ca25b314c8981cfbc13a305277e.jpg"}]}]
    var CreateTime: String = "", // 2022-12-14 15:31:36
    var HeadPortraitUrl: String = "",
    var IsCollect: Int = 0, // 1
    var IsLike: Int = 0, // 0
    var LikeNumber: Int = 0, // 3
    var Media: MutableList  <MediaData> = mutableListOf(),
    var NickName: String = "",
    var ProvinceNo: String = "", // 330000
    var SubjectID: Int = 0, // 90
    var Title: String = "", // 浙里金消抢红包游戏中钱包规则介绍
    var UserNo: Long = 0 // 569029958670853
) {
    data class MediaData(
        var FileName: String = "", // dfaa4ca25b314c8981cfbc13a305277e.jpg
        var FileUrl: String = "" // https://yiuxiu123.oss-cn-hangzhou.aliyuncs.com/YiuXiuMarketPayment/MarketPayment/202212/dfaa4ca25b314c8981cfbc13a305277e.jpg
    )
}