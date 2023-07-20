package com.zhili.zljx.bean


data class ActivityHomeConfigBean(
    var adHeight: String = "",
//    var adInterval: Int = 0, // 10
    var adList: MutableList<Any> = mutableListOf(),
//    var adNextMargin: Int = 0, // 0
    var adNum: Int = 0, // 1
//    var adPreviousMargin: Int = 0, // 0
    var adWidth: String = "",
    var anchorHtml: String = "",
    var anchorList: MutableList<Anchor> = mutableListOf(),
    var backgroundColor: String = "",
    var backgroundImg: String = "",
    var borderRadius: MutableList<Int> = mutableListOf(),
    var columnNum: Int = 0, // 5
    var dataList: MutableList<Data> = mutableListOf(),
    var html: String = "",
    var id: String = "", // h-devise-0
    var img: String = "", // https://img.yiuxiu.com/YiuXiuMarketPayment/MarketPayment/202302/e5418828550b487cbaf551029ee99fac.jpg
//    var imgHeight: Int = 0, // 360
//    var imgWidth: Int = 0, // 690
    var isShow: String = "",
    var isStickyBottom: Boolean = false, // false
    var isStickyTop: Boolean = false, // true
    var itemWidth: String = "",
    var layout: String = "",
    var link: String = "",
    var margin: MutableList<Any> = mutableListOf(),
    var moreText: String = "", // 更多
//    var nextMargin: Int = 0, // 30
//    var offsetTop: Int = 0, // 0
    var padding: MutableList<Int> = mutableListOf(),
//    var previousMargin: Int = 0, // 30
    var scrolling: Boolean = false, // false
    var style: String = "", //                         background-size: 100% auto;                        background-position: top center;                        background-repeat: repeat-y;                     margin:0rpx 0rpx 0rpx 0rpx; padding:0rpx 0rpx 0rpx 0rpx;
    var textList: MutableList<Text> = mutableListOf(),
    var totalSize: String = "", // 3
    var type: String = "" // t-userLocation
) {
    data class Anchor(
        var height: String = "", // 64
        var left: String = "", // 11
        var link: String = "", // https://zljx3.yiuxiu.com/#/pagesActivityApply/apply?MakeActivityNo=MA012110532318420859&ProvinceNo=330000&CityNo=330700&AreaNo=330702
        var top: String = "", // 7
        var width: String = "" // 170
    )

    data class Data(
        var backgroundColor: String = "", // #ffffff
        var borderRadius: MutableList<Int> = mutableListOf(),
        var img: String = "", // https://img.yiuxiu.com/YiuXiuMarketPayment/MarketPayment/202304/cc716896536943beabaeaba021821e11.png
        var imgBorderRadius: MutableList<Int> = mutableListOf(),
//        var imgHeight: Int = 0, // 190
        var imgStyle: String = "", // width:150rpx; height:190rpx; border-radius:10rpx 10rpx 10rpx 10rpx;
//        var imgWidth: Int = 0, // 150
        var isEdit: Boolean = false, // false
        var isShowLinkBtn: Boolean = false, // true
        var isShowName: Boolean = false, // false
        var isShowPrice: Boolean = false, // false
        var isShowText: Boolean = false, // false
        var link: String = "", // https://culturaltourism.yiuxiu.com/#/routeDetail?travelLineId=48
        var linkBtn: String = "", // 马上抢！
//        var linkBtnMarginLeft: Int = 0, // 10
//        var linkBtnMarginTop: Int = 0, // -60
        var margin: MutableList<Int> = mutableListOf(),
        var name: String = "", // 政府券
        var nameAlignment: String = "", // left
//        var nameFontSize: Int = 0, // 28
        var nameMargin: MutableList<Any> = mutableListOf(),
        var nameWeight: Boolean = false, // true
        var padding: MutableList<Int> = mutableListOf(),
        var price: String = "",
        var priceAlignment: String = "", // left
//        var priceFontSize: Int = 0, // 24
        var priceMargin: MutableList<Int> = mutableListOf(),
        var priceStyle: String = "", // font-size:24rpx; text-align:left; margin:0rpx 0rpx 0rpx 0rpx;
        var style: String = "", // width:345rpx; background-color:#ffffff; border-radius:10rpx 10rpx 10rpx 10rpx; margin:15rpx 0rpx 0rpx 0rpx; padding:10rpx 10rpx 10rpx 10rpx;
        var text: String = "", // 文旅券正在预约中
        var textAlignment: String = "", // left
        var textList: MutableList<Text> = mutableListOf(),
        var textMargin: MutableList<Any> = mutableListOf(),
        var itemType :Int = 0
//        var width: Int = 0 // 345
    ) {
        data class Text(
            var alignment: String = "", // left
            var color: String = "", // #333333
//            var fontSize: Int = 0, // 24
            var margin: MutableList<Any> = mutableListOf(),
            var style: String = "", // font-size:24rpx; color:#333333; font-weight:bold; text-align:left; margin:5rpx 0rpx 0rpx 10rpx;
            var text: String = "", // 磐安普惠券开抢
            var weight: Boolean = false // true
        )
    }

    data class Text(
        var color: String = "", // #333333
        var italic: Boolean = false, // false
//        var marginLeft: Int = 0, // 0
//        var size: Int = 0, // 32
        var text: String = "", // 政府券
        var itemType :Int = 0,
        var weight: Boolean = false // true
    )
}
