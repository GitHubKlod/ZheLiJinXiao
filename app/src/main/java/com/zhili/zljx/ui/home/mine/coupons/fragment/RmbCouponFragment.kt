package com.zhili.zljx.ui.home.mine.coupons.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmFragment
import com.zhili.zljx.bean.RmbGrantLogBean
import com.zhili.zljx.databinding.CommonFragmentListLayoutBinding
import com.zhili.zljx.ext.setRefreshLayoutListener
import com.zhili.zljx.ui.home.mine.coupons.CouponsActivityVm
import com.zhili.zljx.ui.home.mine.coupons.fragment.adapter.RmbCouponFragmentAdapter

/**
 * 数字人民币 券包
 *@Auther KLOD
 *2023/4/10 14:27
 */
class RmbCouponFragment : BaseMvvmFragment<CouponsActivityVm, CommonFragmentListLayoutBinding>() {
    companion object{
        fun getInstance(): RmbCouponFragment {
            return RmbCouponFragment()
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.apply {

            commonRefreshLayout.setRefreshLayoutListener(
                onRefresh = {
                    cPage = 1
                    mViewModel.getRmbGrantLog(cPage)
                },
                onLoadMore = {
                    if (cPage <= maxPage) {
                        mViewModel.getRmbGrantLog(cPage)
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
                mAdapter.addOnItemChildClickListener(R.id.rmbMore) { adapter, view, position ->
                    listData[position].showMore = !listData[position].showMore
                    adapter.notifyItemChanged(position)
                }
            }
        }

    }

    override fun createObserver() {

        mViewModel.ecnyListDataUiState.observe(this){
            if(it.isSuccess){
                setListData(it.totalCount,it.data)
            }else{
                mViewBind.commonRefreshLayout.finishRefresh()
                mViewBind.commonRefreshLayout.finishLoadMore()
                showToast(it.errorMsg)
            }
        }

    }


    private var listData = mutableListOf<RmbGrantLogBean.GrantLog.ECNY>()
    private val mAdapter by lazy {
        RmbCouponFragmentAdapter(listData)
    }

    private fun setListData(totalCount:Int,it: MutableList<RmbGrantLogBean.GrantLog.ECNY>?) {

        mViewBind.apply {

            if (cPage == 1) {
                commonRefreshLayout.finishRefresh()
                listData = it?: mutableListOf()
                mAdapter.submitList(listData)

            } else {
                commonRefreshLayout.finishLoadMore()
                mAdapter.addAll(it?: mutableListOf())
            }
            cPage++
            maxPage = getMaxPage(totalCount)

        }


    }

    override fun lazyLoadData() {

//        mViewModel.getRmbGrantLog(cPage)
        mViewBind.commonRefreshLayout.autoRefresh()

    }
}