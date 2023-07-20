package com.zhili.zljx.ext

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener


/**
 * 简化一下 刷新加载 监听代码
 *@Auther KLOD
 *2023/4/10 18:59
 */

fun SmartRefreshLayout.setRefreshLayoutListener(onRefresh :(refreshLayout: RefreshLayout)->Unit, onLoadMore:(refreshLayout: RefreshLayout)->Unit){

    this.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
        override fun onRefresh(refreshLayout: RefreshLayout) {
            onRefresh(refreshLayout)
        }
        override fun onLoadMore(refreshLayout: RefreshLayout) {
            onLoadMore(refreshLayout)
        }
    })


}

/**
 * 简化一下 adapter 子view监听事件注册
 */
fun <T> BaseQuickAdapter<T,*>.setAdapterChildClickListener(vararg idRange: Int, onclick:(adapter: BaseQuickAdapter< T , *>, view: View, position: Int)->Unit){
    idRange.forEach {
        this.addOnItemChildClickListener(it,onclick)
    }
}


