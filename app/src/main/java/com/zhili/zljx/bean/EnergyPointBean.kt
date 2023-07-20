package com.zhili.zljx.bean

data class EnergyPointBean(
    var AdvertEnergyPoint: Double = 0.0, // 10.0
    var BkImageUrl: String = "", // https://img.yiuxiu.com/jinxiaohongbao/20221125072310805471.png
    var BkSmImageUrl: String = "", // https://img.yiuxiu.com/jinxiaohongbao/20230302021719192388.png
    var CurrentEnergyPoint: Double = 0.0, // 0.0
    var DisplayName: String = "", // 15257925260
    var EnergyPointLevelMaxVal: Double = 0.0, // 500
    var EnergyPointLevelMinVal: Double = 0.0, // 0
    var EnergyPointLevelName: String = "", // 细胞
    var FontColor: String = "", // #3F434D
    var FriendCircleEnergyPoint: Double = 0.0, // 10.0
    var FriendEnergyPoint: Double = 0.0, // 10.0
    var Headimgurl: String = "",
    var MdIconUrl: String = "", // https://img.yiuxiu.com/jinxiaohongbao/20221125072330459322.png
    var SmIconUrl: String = "" // https://img.yiuxiu.com/jinxiaohongbao/20221208125302236235.png
)