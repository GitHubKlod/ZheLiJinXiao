package com.zhili.zljx.bean

/**
 *@Auther KLOD
 *2023/4/18 10:59
 */
data class LocationDataInfo(
    var address: String = "",
    var district: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var provinceNo: Int = 0,
    var cityNo: Int = 0,
    var areaNo: Int = 0,
){

}
