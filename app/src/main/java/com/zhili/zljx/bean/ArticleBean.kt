package com.zhili.zljx.bean

import com.zhili.zljx.ui.home.activity.adapter.ArticleAdapter.Companion.ArticleTypeSingleImg

data class ArticleBean(
    var PageIndex: Int = 0, // 1
    var PageSize: Int = 0, // 8
    var Rows: MutableList<Row> = mutableListOf(),
    var Total: Int = 0 // 27
) {
    data class Row(
        var AreaNo: String = "", // 330792
        var ArticleID: String = "", // 640fd9fac95117bc8550600a
        var CityNo: String = "", // 330700
        var ClickNum: Int = 0, // 0
        var Content: String = "", // <p>都说良好的教育源自于生活，近年来，由于政府消费券的火爆，浙里金消也逐渐以政府消费券发放平台的身份逐渐走入公众视野，掀起了一阵席卷八婺的消费券时尚风潮，这不，就在最近金华市婺城区期末调研抽测卷中我们惊喜的发现了“浙里金消”的身影。</p><p><img src="https://img.yiuxiu.com/YiuXiuMarketPayment/MarketPayment/202303/0dabb41f5dfe46c59eb40640e6ec9f02.jpg"></p><p><br></p><p><img src="https://img.yiuxiu.com/YiuXiuMarketPayment/MarketPayment/202303/cc347b02a6774fb3a2bf7d33ad63921a.jpg"></p><p>	这不单单彰显了现代教育源自生活，发现生活，理解生活的时代特性，更从侧面证明了浙里金消，以及政府所指导的消费券在市民心中的重要地位，充分的说明了，政府提倡发放消费券是明智的、具有教育意义的、具有长久发展的、是受市民爱戴的重要战略部署。</p><p>作为发放消费券的重要实施平台之一的浙里金消，今后也将不断提升自身的技术水平，不断提高服务质量，不断加强用户体验，让每次的消费券抢购更加舒心，放心。</p>
        var CreateTime: String = "", // 2023-03-14 10:20:42
        var ProvinceNo: String = "", // 330000
        var imageUrl1: String = "", // 330000
        var imageUrl2: String = "", // 330000
        var imageUrl3: String = "", // 330000
        var Sort: Int = 0, // 0
        var itemType: Int = ArticleTypeSingleImg, // 0
        var imageSize: Int = 0, // 0
        var Title: String = "" // 融入百姓融入生活！浙里金消成功“登录”小学生试卷！
    )
}