package com.zhili.zljx.bean


data class AppVersionInfoBean(
    var ChangeLog: String = "", // 浙里金消安卓版发布啦
    var ForceVersion: String = "",
    var IsForceUpdate: Int = 0, // 0
    var IsPrompt: Int = 0, // 0
    var LogId: Int = 0, // 1
    var MinVersion: Int = 0, // 1
    var VersionNo: String = "" // 1.0.0
)
