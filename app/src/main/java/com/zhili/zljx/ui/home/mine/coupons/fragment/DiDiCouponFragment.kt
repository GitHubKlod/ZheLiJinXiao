package com.zhili.zljx.ui.home.mine.coupons.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmFragment
import com.zhili.zljx.bean.DiDiGrantLogBean
import com.zhili.zljx.databinding.CommonFragmentListLayoutBinding
import com.zhili.zljx.ext.setRefreshLayoutListener
import com.zhili.zljx.ui.home.mine.coupons.CouponsActivityVm
import com.zhili.zljx.ui.home.mine.coupons.fragment.adapter.DiDiCouponFragmentAdapter

/**
 * 滴滴券 券包
 *@Auther KLOD
 *2023/4/10 14:27
 */
class DiDiCouponFragment  : BaseMvvmFragment<CouponsActivityVm, CommonFragmentListLayoutBinding>() {

    companion object{
        fun getInstance(): DiDiCouponFragment {
            return DiDiCouponFragment()
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.apply {

            commonRefreshLayout.setRefreshLayoutListener(
                onRefresh = {
                    cPage = 1
                    mViewModel.getDiDiGrantLog(cPage)
                },
                onLoadMore = {
                    if (cPage <= maxPage) {
                        mViewModel.getDiDiGrantLog(cPage)
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
    private var listData = mutableListOf<DiDiGrantLogBean.GrantLog.ECNY>()
    private val mAdapter by lazy {
        DiDiCouponFragmentAdapter(listData)
    }

    override fun createObserver() {

        mViewModel.didiListDataUiState.observe(this){
            if(it.isSuccess){
                setListData(it.totalCount,it.data)
            }else{
                mViewBind.commonRefreshLayout.finishRefresh()
                mViewBind.commonRefreshLayout.finishLoadMore()
                showToast(it.errorMsg)
            }
        }

    }
    private fun setListData(totalCount:Int,it: MutableList<DiDiGrantLogBean.GrantLog.ECNY>?) {

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

//        mViewModel.getDiDiGrantLog(cPage)
        mViewBind.commonRefreshLayout.autoRefresh()

    }

}