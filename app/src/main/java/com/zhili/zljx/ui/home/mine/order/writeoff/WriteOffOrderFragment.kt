package com.zhili.zljx.ui.home.mine.order.writeoff

import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import com.klod.base.ext.parseState

import com.kongzue.dialogx.dialogs.MessageDialog

import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmFragment
import com.zhili.zljx.bean.WriteOffOrderBean
import com.zhili.zljx.databinding.CommonFragmentListLayoutBinding
import com.zhili.zljx.ext.copyToClipboard
import com.zhili.zljx.ext.setRefreshLayoutListener
import com.zhili.zljx.ext.setAdapterChildClickListener
import com.zhili.zljx.ext.startAC
import com.zhili.zljx.ui.home.mine.order.detail.WriteOffOrderDetailActivity


/**
 *@Auther KLOD
 *2023/4/13 14:34
 */
class WriteOffOrderFragment :
    BaseMvvmFragment<WriteOffOrderActivityVm, CommonFragmentListLayoutBinding>() {
    companion object {
        fun getInstance(type: Int) = WriteOffOrderFragment().apply {
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
                    mViewModel.getWriteOffOrderList(type, cPage)
                },
                onLoadMore = {
                    if (cPage <= maxPage) {
                        mViewModel.getWriteOffOrderList(type, cPage)
                    } else {
                        it.finishLoadMoreWithNoMoreData()
                    }
                })

            mAdapter.isEmptyViewEnable = true
            mAdapter.setEmptyViewLayout(mContext,R.layout.empty_layout1)
            commonRecyclerView.apply {
                layoutManager = LinearLayoutManager(mContext)
                adapter = mAdapter
                itemAnimator?.changeDuration = 0



            }
        }
        mAdapter.setOnItemClickListener{ adapter, _, position ->
                mContext.startAC<WriteOffOrderDetailActivity> {
                    putExtra("orderNo",adapter.items[position].OrderNo)
                }
        }
        mAdapter.setAdapterChildClickListener(R.id.cancelOrder, R.id.copyOrder,
            onclick = { adapter, view, position ->

                when (view.id) {
                    R.id.cancelOrder -> {
                        MessageDialog.build()
                            .setTitle("温馨提示")
                            .setMessage("确定要取消订单吗?")
                            .setOkButton("确定")
                            .setCancelButton("取消")
                            .setOkButtonClickListener { _, _ ->

                                mViewModel.postCancelOrder(adapter.items[position].OrderNo)
                                false
                            }
                            .show()
                    }

                    R.id.copyOrder -> {
                        adapter.items[position].OrderNo.copyToClipboard(mContext)
                        showToast("已复制订单号到粘贴板")
                    }
                }

            })
    }

    override fun createObserver() {
        mViewModel.woOrderState.observe(this) { resultState ->
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
//                mAdapter.removeAt(deleteIndex)
                mViewBind.commonRefreshLayout.autoRefresh()
            }, { appException ->
                showToast(appException.errorMsg)
            })
        }

    }

    private var listData = mutableListOf<WriteOffOrderBean.Row>()

    private val mAdapter by lazy {
        WriteOffOrderAdapter(listData)
    }

    private fun setList(it: WriteOffOrderBean) {
        mViewBind.apply {
            if (cPage == 1) {
                commonRefreshLayout.finishRefresh()
                listData = it.Rows
                mAdapter.submitList(listData)

            } else {
                commonRefreshLayout.finishLoadMore()
                mAdapter.addAll(it.Rows)
            }
            cPage++
            maxPage = getMaxPage(it.Total)
        }
    }

    override fun lazyLoadData() {

        mViewBind.commonRefreshLayout.autoRefresh()

    }


}