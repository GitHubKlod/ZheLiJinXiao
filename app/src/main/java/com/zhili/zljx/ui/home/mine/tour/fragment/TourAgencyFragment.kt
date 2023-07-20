package com.zhili.zljx.ui.home.mine.tour.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.klod.base.ext.parseState
import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmFragment
import com.zhili.zljx.bean.TourAgencyListBean
import com.zhili.zljx.databinding.CommonFragmentListLayoutBinding
import com.zhili.zljx.databinding.ItemTourAgencyBinding
import com.zhili.zljx.ext.loadAny
import com.zhili.zljx.ext.setRefreshLayoutListener
import com.zhili.zljx.ext.startAC
import com.zhili.zljx.ui.home.mine.tour.AgencyDetailActivity
import com.zhili.zljx.ui.home.mine.tour.TourAgencyActivityVm
import com.zhili.zljx.ui.home.mine.tour.TourHomeActivityVm
import com.zhili.zljx.ui.home.mine.tour.adapter.TourAgencyFragmentAdapter

/**
 *@Auther KLOD
 *2023/4/17 18:22
 */
class TourAgencyFragment: BaseMvvmFragment<TourAgencyActivityVm, CommonFragmentListLayoutBinding>() {


    companion object{
        fun getInstance(type:Int)=TourAgencyFragment().apply {
            arguments = Bundle().apply {
                putInt("type",type)
            }
        }
    }

    private val type by lazy {
        arguments?.getInt("type",0)?:0
    }

    override fun initView(savedInstanceState: Bundle?) {


        mViewBind.apply {


            commonRefreshLayout.setRefreshLayoutListener(onRefresh = {
                cPage = 1
                mViewModel.getTourAgencyList(type,cPage)
            }, onLoadMore ={
                if (cPage <= maxPage) {
                    mViewModel.getTourAgencyList(type,cPage)
                } else {
                    it.finishLoadMoreWithNoMoreData()
                }
            })

            commonRecyclerView.apply {
                layoutManager = LinearLayoutManager(mContext)

                adapter =mAdapter


            }

        }

        mAdapter.isEmptyViewEnable = true
        mAdapter.setEmptyViewLayout(mContext,R.layout.empty_layout1)

        mAdapter.setOnItemClickListener{adapter, view, position ->

            //跳转 旅行社详情页
            mContext.startAC<AgencyDetailActivity> {
                putExtra("agencyId",adapter.items[position].ID)
                putExtra("agencyName",adapter.items[position].AgencyName)
            }
        }


    }

    override fun createObserver() {

        mViewModel.tourAgencyListState.observe(this){ resultState ->

            parseState(resultState,{

                setTourAgencyRv(it)

            },{
                mViewBind.commonRefreshLayout.finishRefresh()
                mViewBind.commonRefreshLayout.finishLoadMore()

                showToast(it.errorMsg)
            })

        }

    }


//    private var listData  = mutableListOf<TourAgencyListBean.Data>()
    private val mAdapter = TourAgencyFragmentAdapter()


    /**
     * 设置3条 数据 部分旅行社
     */
    private fun setTourAgencyRv(it: TourAgencyListBean) {

        if(cPage ==1){
            mAdapter.submitList(it.DataList)
            mViewBind.commonRefreshLayout.finishRefresh()
        }else{
            mViewBind.commonRefreshLayout.finishLoadMore()
            mAdapter.addAll(it.DataList)
        }
        cPage++
        maxPage = getMaxPage(it.Total)

    }

    override fun lazyLoadData() {

        mViewModel.getTourAgencyList(type,cPage)

    }


}