package com.zhili.zljx.ui.home.mine.record.make.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.klod.base.ext.parseState
import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmFragment
import com.zhili.zljx.bean.ECNYMakeRecordBean
import com.zhili.zljx.databinding.CommonFragmentListLayoutBinding
import com.zhili.zljx.ext.setRefreshLayoutListener
import com.zhili.zljx.ui.home.mine.record.make.MakeRecordActivityVm
import com.zhili.zljx.ui.home.mine.record.make.adapter.DiDiMakeRecordAdapter


/**
 * 预约记录 - 滴滴出行券
 *@Auther KLOD
 *2023/4/12 15:02
 */
class MakeRecordFragment3 : BaseMvvmFragment<MakeRecordActivityVm, CommonFragmentListLayoutBinding>() {

    companion object{
        fun getInstance() = MakeRecordFragment3()

    }

    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.apply {

            commonRefreshLayout.autoRefresh()
            commonRefreshLayout.setRefreshLayoutListener(
                onRefresh = {
                    cPage = 1
                    mViewModel.getDiDiMakeRecordList(cPage)
                },
                onLoadMore = {
                    if (cPage <= maxPage) {
                        mViewModel.getDiDiMakeRecordList(cPage)
                    } else {
                        it.finishLoadMoreWithNoMoreData()
                    }
                })
            mAdapter.isEmptyViewEnable = true
            mAdapter.setEmptyViewLayout(mContext, R.layout.empty_layout1)
            commonRecyclerView.apply {
                layoutManager = LinearLayoutManager(mContext)
                adapter = mAdapter
                itemAnimator?.changeDuration = 0

            }
        }

    }

    override fun createObserver() {

        mViewModel.didiMakeRecordState.observe(this) { resultState ->
            parseState(resultState, {
                setList(it)
            }, { appException ->
                mViewBind.commonRefreshLayout.finishRefresh()
                mViewBind.commonRefreshLayout.finishLoadMore()
                showToast(appException.errorMsg)
            })

        }
    }
    private var listData = mutableListOf<ECNYMakeRecordBean.Data>()
    private var mAdapter = DiDiMakeRecordAdapter(listData)
    private fun setList(it: ECNYMakeRecordBean) {
        mViewBind.apply {
            if (cPage == 1) {
                commonRefreshLayout.finishRefresh()
                listData = it.data
                mAdapter.submitList(listData)

            } else {
                commonRefreshLayout.finishLoadMore()
                mAdapter.addAll(it.data)
            }
            cPage++
            maxPage = getMaxPage(it.totalCount)
        }
    }

    override fun lazyLoadData() {


    }


}