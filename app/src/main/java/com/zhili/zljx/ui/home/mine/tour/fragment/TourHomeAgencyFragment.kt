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
import com.zhili.zljx.ext.startAC
import com.zhili.zljx.ui.home.mine.tour.AgencyDetailActivity
import com.zhili.zljx.ui.home.mine.tour.TourHomeActivityVm

/**
 *@Auther KLOD
 *2023/4/17 18:22
 */
class TourHomeAgencyFragment: BaseMvvmFragment<TourHomeActivityVm, CommonFragmentListLayoutBinding>() {


    companion object{
        fun getInstance(type:Int)=TourHomeAgencyFragment().apply {
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

            commonRefreshLayout.setEnableRefresh(false)
            commonRefreshLayout.setEnableLoadMore(false)

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
                showToast(it.errorMsg)
            })

        }

    }


//    private var listData  = mutableListOf<TourAgencyListBean.Data>()
    private val mAdapter = HomeTourAgencyFragmentAdapter(mutableListOf())


    /**
     * 设置3条 数据 部分旅行社
     */
    private fun setTourAgencyRv(it: TourAgencyListBean) {


        mAdapter.submitList(it.DataList)

    }

    override fun lazyLoadData() {

        mViewModel.getTourAgencyList(type,1)

    }


    private class  HomeTourAgencyFragmentAdapter(items: List<TourAgencyListBean.Data>) :
        BaseQuickAdapter<TourAgencyListBean.Data, HomeTourAgencyFragmentAdapter.VH>(items) {
        // 自定义ViewHolder类
        class VH(
            parent: ViewGroup,
            val binding: ItemTourAgencyBinding = ItemTourAgencyBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
        ) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
            // 返回一个 ViewHolder
            return VH(parent)
        }


        override fun onBindViewHolder(holder: VH, position: Int, item: TourAgencyListBean.Data?) {
            // 设置item数据
            holder.binding.apply {
                item?.let { item ->

                    tourAgencyImage.loadAny(item.BackImage)
                    tourAgencyName.text = item.AgencyName
                    tourAgencyDesc.text = item.Introduce

                }

            }


        }
    }

}