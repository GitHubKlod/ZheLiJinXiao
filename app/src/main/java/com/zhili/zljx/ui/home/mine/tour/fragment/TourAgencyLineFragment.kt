package com.zhili.zljx.ui.home.mine.tour.fragment

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.klod.base.ext.parseState
import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmFragment
import com.zhili.zljx.bean.TourLineListBean
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.databinding.CommonFragmentListLayoutBinding
import com.zhili.zljx.ext.setRefreshLayoutListener
import com.zhili.zljx.ext.startAC
import com.zhili.zljx.ui.common.WebViewActivity
import com.zhili.zljx.ui.home.mine.tour.AgencyDetailActivityVm
import com.zhili.zljx.ui.home.mine.tour.adapter.TourLineAdapter

/**
 *@Auther KLOD
 *2023/5/2 17:02
 */
class TourAgencyLineFragment :
    BaseMvvmFragment<AgencyDetailActivityVm, CommonFragmentListLayoutBinding>() {


    companion object{
        fun getInstance(agencyId:Int)=TourAgencyLineFragment().apply {
            arguments = Bundle().apply {
                putInt("agencyId",agencyId)
            }
        }
    }

    private val agencyId by lazy {
        arguments?.getInt("agencyId",0)?:0
    }

    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.apply {
            commonRefreshLayout.setRefreshLayoutListener(onRefresh = {
                cPage = 1
                mViewModel.getTourLineList(agencyId,cPage)
            }, onLoadMore ={
                if (cPage <= maxPage) {
                    mViewModel.getTourLineList(agencyId,cPage)
                } else {
                    it.finishLoadMoreWithNoMoreData()
                }
            })

            commonRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)

                adapter =tourLineAdapter


            }

        }

        tourLineAdapter.isEmptyViewEnable = true
        tourLineAdapter.setEmptyViewLayout(mContext, R.layout.empty_layout1)

        tourLineAdapter.setOnItemClickListener{adapter, view, position ->

            //跳转 旅行线路详情页
            mContext.startAC<WebViewActivity> {
                putExtra("url", Contacts.TourLineUrl+adapter.items[position].ID)
            }

        }


    }

    override fun createObserver() {
        //旅游路线
        mViewModel.tourLineListState.observe(this) { resultState ->
            parseState(resultState, {
                setTourLineRv(it)
            }, {
                mViewBind.commonRefreshLayout.finishRefresh()
                mViewBind.commonRefreshLayout.finishLoadMore()

                showToast(it.errorMsg)
            })
        }


    }

    override fun lazyLoadData() {

        mViewModel.getTourLineList(agencyId, cPage)

    }


    private var tourLineAdapter = TourLineAdapter()

    /**
     * 设置底部Rv 旅游路线
     */
    private fun setTourLineRv(it: TourLineListBean) {

        if (cPage == 1) {
            mViewBind.commonRefreshLayout.finishRefresh()
            tourLineAdapter.submitList(it.DataList)

        } else {
            mViewBind.commonRefreshLayout.finishLoadMore()
            tourLineAdapter.addAll(it.DataList)
        }
        cPage++
        maxPage = getMaxPage(it.Total)

    }

}