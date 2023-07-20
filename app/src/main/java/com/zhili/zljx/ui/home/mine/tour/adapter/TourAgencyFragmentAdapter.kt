package com.zhili.zljx.ui.home.mine.tour.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhili.zljx.bean.TourAgencyListBean
import com.zhili.zljx.databinding.ItemTourAgency1Binding

import com.zhili.zljx.ext.loadAny

/**
 *@Auther KLOD
 *2023/5/2 10:21
 */
class TourAgencyFragmentAdapter  :
    BaseQuickAdapter<TourAgencyListBean.Data, TourAgencyFragmentAdapter.VH>() {
    // 自定义ViewHolder类
    class VH(
        parent: ViewGroup,
        val binding: ItemTourAgency1Binding = ItemTourAgency1Binding.inflate(
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
//                tourAgencyDesc.text = item.Introduce

            }

        }


    }
}