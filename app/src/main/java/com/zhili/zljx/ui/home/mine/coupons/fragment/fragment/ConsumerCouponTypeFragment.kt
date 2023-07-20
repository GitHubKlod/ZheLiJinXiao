package com.zhili.zljx.ui.home.mine.coupons.fragment.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.klod.base.ext.parseState
import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmFragment
import com.zhili.zljx.bean.MyCouponListBean
import com.zhili.zljx.databinding.CommonFragmentListLayoutBinding
import com.zhili.zljx.ext.setRefreshLayoutListener
import com.zhili.zljx.ui.home.mine.coupons.CouponsActivityVm
import com.zhili.zljx.ui.home.mine.coupons.fragment.adapter.ConsumerCouponTypeFragmentAdapter


/**
 *@Auther KLOD
 *2023/4/10 11:17
 */
class ConsumerCouponTypeFragment :
    BaseMvvmFragment<CouponsActivityVm, CommonFragmentListLayoutBinding>() {


    private val type by lazy {
        arguments?.getInt("type", 0) ?: 0
    }

    companion object {

        //        0全部，1待生效，2待使用，3已失效
        fun getInstance(type: Int): ConsumerCouponTypeFragment =
            ConsumerCouponTypeFragment().apply {
                arguments = Bundle().apply {
                    putInt("type", type)
                }
            }

    }

    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.apply {

            commonRefreshLayout.setRefreshLayoutListener(
                onRefresh = {
                    cPage = 1
                    mViewModel.getConsumerCouponList(cPage, type)
                },
                onLoadMore = {
                    if (cPage <= maxPage) {
                        mViewModel.getConsumerCouponList(cPage, type)
                    } else {
                        it.finishLoadMoreWithNoMoreData()
                    }
                })
            mAdapter.isEmptyViewEnable = true

            mAdapter.setEmptyViewLayout(mContext,R.layout.empty_layout1)
            commonRecyclerView.apply {
                layoutManager = LinearLayoutManager(mContext)
                adapter = mAdapter
                (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
                mAdapter.addOnItemChildClickListener(R.id.rmbMore) { adapter, view, position ->
                    listData[position].showMore = !listData[position].showMore
                    adapter.notifyItemChanged(position)
                }
            }
        }

    }

    /**
     * 监听
     */
    override fun createObserver() {

        mViewModel.couponListData.observe(this) {

            parseState(it, { listBean ->
                setListData(listBean)
            }, { appException ->
                mViewBind.commonRefreshLayout.finishRefresh()
                mViewBind.commonRefreshLayout.finishLoadMore()
                showToast(appException.errorMsg)
            })

        }

    }

    private var listData = mutableListOf<MyCouponListBean.Row>()
    private val mAdapter by lazy {
        ConsumerCouponTypeFragmentAdapter(type, listData)
    }

    private fun setListData(it: MyCouponListBean) {

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

//        mViewModel.getConsumerCouponList(cPage, type)
        mViewBind.commonRefreshLayout.autoRefresh()

    }
}