package com.zhili.zljx.ui.home.mine.order.tour

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.klod.base.ext.parseState
import com.kongzue.dialogx.dialogs.MessageDialog
import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmFragment
import com.zhili.zljx.bean.TourOrderBean
import com.zhili.zljx.databinding.CommonFragmentListLayoutBinding
import com.zhili.zljx.ext.copyToClipboard
import com.zhili.zljx.ext.setAdapterChildClickListener
import com.zhili.zljx.ext.setRefreshLayoutListener
import com.zhili.zljx.ext.startAC
import com.zhili.zljx.ui.home.mine.order.detail.TourOrderDetailActivity

/**
 *@Auther KLOD
 *2023/4/14 14:07
 */
class TourOrderFragment : BaseMvvmFragment<TourOrderActivityVm, CommonFragmentListLayoutBinding>() {


    companion object {
        fun getInstance(type: Int) = TourOrderFragment().apply {
            arguments = Bundle().apply {
                putInt("type", type)
            }
        }
    }

    private val type by lazy {
        arguments?.getInt("type", 0) ?: 0
    }


    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.apply {

            commonRefreshLayout.setRefreshLayoutListener(
                onRefresh = {
                    cPage = 1
                    mViewModel.getTourOrderState(type, cPage)
                },
                onLoadMore = {
                    if (cPage <= maxPage) {
                        mViewModel.getTourOrderState(type, cPage)
                    } else {
                        showToast("无更多数据")
                        it.finishLoadMoreWithNoMoreData()
                    }
                })

            mAdapter.isEmptyViewEnable = true
            mAdapter.setEmptyViewLayout(mContext,R.layout.empty_layout1)
            commonRecyclerView.apply {

                layoutManager = LinearLayoutManager(mContext)
                adapter = mAdapter



            }

        }
        //item点击
        mAdapter.setOnItemClickListener { adapter, view, position ->
            //跳转订单详情页
            mContext.startAC<TourOrderDetailActivity> {
                putExtra("orderNo",adapter.items[position].OrderNO)
            }

        }
        mAdapter.setAdapterChildClickListener(R.id.payOrder,
            R.id.cancelOrder,
            R.id.copyOrder,
            onclick = { adapter, view, position ->
                when (view.id) {
                    R.id.payOrder -> {//支付订单点击

                    }

                    R.id.cancelOrder -> {//取消订单点击


                        MessageDialog.build()
                            .setTitle("温馨提示")
                            .setMessage("确定要取消订单吗?")
                            .setOkButton("确定")
                            .setCancelButton("取消")
                            .setOkButtonClickListener { _, _ ->
                                mViewModel.postCancelOrder(adapter.items[position].OrderNO)
                                false
                            }
                            .show()
                    }

                    R.id.copyOrder -> {//复制订单号点击
                        adapter.items[position].OrderNO.copyToClipboard(mContext)
                        showToast("复制成功")
                    }
                }
            })
    }

    override fun createObserver() {

        mViewModel.tourOrderState.observe(this) { resultState ->
            parseState(resultState, {
                setList(it)
            }, { appException ->
                mViewBind.commonRefreshLayout.finishRefresh()
                mViewBind.commonRefreshLayout.finishLoadMore()
                showToast(appException.errorMsg)
            })
        }
        mViewModel.cancelOrderState.observe(this) { resultState ->
            parseState(resultState, {
//                showToast(it.errorMsg)
                showToast("取消成功")
                mViewBind.commonRefreshLayout.autoRefresh()

            }, { appException ->
                showToast(appException.errorMsg)
            })
        }
    }


    private fun setList(it: TourOrderBean) {

        if (cPage == 1) {
            mViewBind.commonRefreshLayout.finishRefresh()
            listData = it.DataList
            mAdapter.submitList(listData)
        } else {
            mViewBind.commonRefreshLayout.finishLoadMore()
            mAdapter.addAll(it.DataList)
        }
        cPage++
    }

    private var listData = mutableListOf<TourOrderBean.Data>()
    private val mAdapter = TourOrderAdapter(listData)


    override fun lazyLoadData() {

        mViewBind.commonRefreshLayout.autoRefresh()


    }
}