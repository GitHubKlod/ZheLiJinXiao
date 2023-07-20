package com.zhili.zljx.ui.home.mine.tour.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.klod.base.ext.parseState
import com.klod.base.ext.util.logE
import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmFragment
import com.zhili.zljx.bean.MyCouponListBean
import com.zhili.zljx.bean.TourAgencyListBean
import com.zhili.zljx.databinding.CommonFragmentListLayoutBinding
import com.zhili.zljx.databinding.FragmentTourCouponEnterBinding
import com.zhili.zljx.databinding.FragmentTourRegionBinding
import com.zhili.zljx.databinding.ItemCouponsInventRmbBinding
import com.zhili.zljx.databinding.ItemTourAgencyBinding
import com.zhili.zljx.ext.df
import com.zhili.zljx.ext.fromHtml
import com.zhili.zljx.ext.loadAny
import com.zhili.zljx.ext.startAC
import com.zhili.zljx.ext.toDp
import com.zhili.zljx.ext.visibleView
import com.zhili.zljx.ui.common.WebViewActivity
import com.zhili.zljx.ui.home.mine.tour.TourHomeActivityVm
import com.zhili.zljx.ui.home.mine.tour.adapter.TourRegionCouponAdapter

/**
 *@Auther KLOD
 *2023/4/17 18:22
 */
class TourCouponEnterFragment :
    BaseMvvmFragment<TourHomeActivityVm, FragmentTourRegionBinding>() {


    companion object {
        fun getInstance(regionId: Int) = TourCouponEnterFragment().apply {
            arguments = Bundle().apply {
                putInt("regionId", regionId)
            }
        }
    }

    private val regionId by lazy {
        arguments?.getInt("regionId", 0) ?: 0
    }

    override fun initView(savedInstanceState: Bundle?) {


        mViewBind.apply {

//            imageView.loadAny("")
            commonRefreshLayout.setEnableLoadMore(false)
            commonRefreshLayout.setEnableRefresh(false)

            commonRecyclerView.apply {
                isNestedScrollingEnabled = false
                layoutManager = LinearLayoutManager(mContext)
                adapter = mAdapter
            }

        }
        mAdapter.isEmptyViewEnable = false
        mAdapter.setEmptyViewLayout(mContext,  R.layout.empty_layout1)

        mAdapter.setOnItemClickListener{adapter, view, position ->

            mContext.startAC<WebViewActivity> {
                putExtra("url",adapter.items[position].LinkUrl)
            }

        }

    }

    override fun createObserver() {

        mViewModel.regionCouponListState.observe(this) { resultState ->
            parseState(resultState, {
                mAdapter.submitList(it.DataList)

                setVpHeight()

            }, {
                showToast(it.errorMsg)
            })
        }

    }

    private fun setVpHeight() {

//        mViewBind.commonRecyclerView.post{
//            val a =   mViewBind.commonRecyclerView.computeVerticalScrollRange()
//            "查兰 rv高度 $a".logE()
//            mViewModel.vpHeight.value = a + 13.toDp()
//        }


    }

    private val mAdapter = TourRegionCouponAdapter()


    override fun lazyLoadData() {
        mViewModel.getTourRegionCouponList(regionId)

    }


}