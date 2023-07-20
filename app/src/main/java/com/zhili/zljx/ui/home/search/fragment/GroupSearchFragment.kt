package com.zhili.zljx.ui.home.search.fragment

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.klod.base.ext.parseState
import com.klod.base.ext.util.logE
import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmFragment
import com.zhili.zljx.bean.ActivityGroupBean
import com.zhili.zljx.databinding.CommonFragmentListLayoutBinding
import com.zhili.zljx.ext.setRefreshLayoutListener
import com.zhili.zljx.ext.startAC
import com.zhili.zljx.ui.home.activity.adapter.GroupAdapter
import com.zhili.zljx.ui.home.article.GroupDetailActivity
import com.zhili.zljx.ui.home.search.GroupSearchActivityVm

/**
 *@Auther KLOD
 *2023/4/24 19:59
 */
class GroupSearchFragment :
    BaseMvvmFragment<GroupSearchActivityVm, CommonFragmentListLayoutBinding>() {

    private val  mShareViewModel :GroupSearchActivityVm by activityViewModels()

    companion object {
        fun getInstance(type: Int) = GroupSearchFragment().apply {
            arguments = Bundle().apply {
                putInt("type", type)
            }
        }
    }

    private val type by lazy {
        arguments?.getInt("type") ?: 0
    }

    private val mAdapter = GroupAdapter()

    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.commonRefreshLayout.setRefreshLayoutListener(
            onRefresh = {
                cPage = 1
                mShareViewModel.getSearchResult(cPage,type)
            },
            onLoadMore = {
                if(cPage<=maxPage){
                    mShareViewModel.getSearchResult(cPage,type)

                }else{
                    it.finishLoadMoreWithNoMoreData()
                }
            }
        )

        mViewBind.commonRecyclerView.apply {

            layoutManager = LinearLayoutManager(mContext)

            adapter = mAdapter

        }
        mAdapter.isEmptyViewEnable = true

        mAdapter.setEmptyViewLayout(mContext, R.layout.empty_layout1)

        mAdapter.setOnItemClickListener{adapter, view, position ->

            mContext.startAC<GroupDetailActivity> {
                putExtra("subjectId", adapter.items[position].SubjectID)
            }

        }

        mShareViewModel.getSearchResult(cPage,type)

    }

    override fun createObserver() {

        mShareViewModel.searchContext.observe(this){
            "活动圈搜索关键词 = $it".logE()
            cPage = 1
            mShareViewModel.getSearchResult(cPage,type)
        }

        mShareViewModel.groupDataState.observe(this) { resultState ->

            parseState(resultState, {
                   setGroupResultRv(it)
            }, {
                showToast(it.errorMsg)
                mViewBind.commonRefreshLayout.finishRefresh()
                mViewBind.commonRefreshLayout.finishLoadMore()
            })

        }

    }

    private fun setGroupResultRv(it: ActivityGroupBean) {

//        "$type 数据来了 ${it.Rows.size}".logE()
        if(cPage==1){
            mAdapter.submitList(it.Rows)
            mViewBind.commonRefreshLayout.finishRefresh()
        }else{
            mAdapter.addAll(it.Rows)
            mViewBind.commonRefreshLayout.finishLoadMore()
        }

        cPage++
        maxPage = getMaxPage(it.Total)

    }

    override fun lazyLoadData() {


    }
}