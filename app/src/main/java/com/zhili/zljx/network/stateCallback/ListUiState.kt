package com.zhili.zljx.network.stateCallback

/**
 * 作者　: KLOD
 * 时间　: 2020/3/11
 * 描述　:操作数据的状态类
 */
data class ListUiState<T>(
    //请求是否成功
    var isSuccess: Boolean = true,
    //操作的对象
    var data: T? = null,
    //请求失败的错误信息
    var errorMsg: String = "",
    //总条数
    var totalCount: Int = 0,
    //下标
    var index: Int = -1
)