package com.zhili.zljx.bean.request

/**
 * 发送数据json格式 用
 */
data class BankCardJsonBean(
    var bank: String = "", // string
    var cardID: String = "", // string
    var cardNo: String = "", // string
    var city: String = "", // string
    var cityCode: String = "", // string
    var code: String = "", // string
    var phone: String = "", // string
    var province: String = "", // string
    var provinceCode: String = "", // string
    var realName: String = "" // string
)