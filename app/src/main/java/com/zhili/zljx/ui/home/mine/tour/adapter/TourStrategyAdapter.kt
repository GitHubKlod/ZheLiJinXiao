package com.zhili.zljx.ui.home.mine.tour.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhili.zljx.bean.TourAgencyListBean
import com.zhili.zljx.bean.TourStrategyListBean
import com.zhili.zljx.databinding.ItemTourAgencyBinding
import com.zhili.zljx.databinding.ItemTourStrategyBinding
import com.zhili.zljx.ext.loadAny

/**
 *@Auther KLOD
 *2023/4/17 19:13
 */
class TourStrategyAdapter(items: List<TourStrategyListBean.Data>) :
    BaseQuickAdapter<TourStrategyListBean.Data, TourStrategyAdapter.VH>(items) {
    // 自定义ViewHolder类
    class VH(
        parent: ViewGroup,
        val binding: ItemTourStrategyBinding = ItemTourStrategyBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        // 返回一个 ViewHolder
        return VH(parent)
    }


    override fun onBindViewHolder(holder: VH, position: Int, item: TourStrategyListBean.Data?) {
        // 设置item数据
        holder.binding.apply {
            item?.let { item ->

                image.loadAny(item.ImageUrl)
                desc.text = item.Title

            }

        }


    }
}