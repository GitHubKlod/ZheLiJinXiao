package com.zhili.zljx.bean


data class ActivityGroupDetailContentBean(
    var content: String = "", // 流程如下
    var media: MutableList<Media> = mutableListOf()
) {
    data class Media(
        var FileName: String = "", // efc4f70a80524451b206425a09d26306.jpg
        var FileUrl: String = "" // https://yiuxiu123.oss-cn-hangzhou.aliyuncs.com/YiuXiuMarketPayment/MarketPayment/202212/efc4f70a80524451b206425a09d26306.jpg
    )
}
