package com.zhili.zljx.bean

import java.io.Serializable


data class UserInfo(
    var ActivityCircleNumber: Int = 0, // 0
    var BrowseNumber: Int = 0, // 0
    var CollectNumber: Int = 0, // 0
    var FollowNumber: Int = 0, // 0
    var HeadPortraitUrl: String = "",
    var IDCard: String = "", // 330726199709042515
    var IsRealName: Int = 0, // 1
    var Mobile: String = "", // 15257925260
    var NickName: String = "", // KLOD
    var RealName: String = "", // 傅楚
    var UserNo: Long = 0 // 387941622395077
) : Serializable
