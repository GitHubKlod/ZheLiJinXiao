package com.zhili.zljx.bean

data class BanksDataBean(
    var banks: MutableList<Bank> = mutableListOf()
) {
    data class Bank(
        var BankName: String = "", // 中国农业银行
        var ID: Int = 0, // 1
        var ImgUrl: String = "" // https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301175933884007.png
    )
}