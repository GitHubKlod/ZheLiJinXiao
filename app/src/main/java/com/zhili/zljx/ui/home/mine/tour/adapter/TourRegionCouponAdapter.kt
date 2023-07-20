package com.zhili.zljx.ui.home.mine.tour.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhili.zljx.bean.TourRegionCouponBean

import com.zhili.zljx.databinding.FragmentTourCouponEnterBinding

import com.zhili.zljx.ext.loadAny

/**
 *@Auther KLOD
 *2023/4/27 14:12
 */
class TourRegionCouponAdapter : BaseQuickAdapter<TourRegionCouponBean.Data, TourRegionCouponAdapter.VH>() {
    // 自定义ViewHolder类
    class VH(
        parent: ViewGroup,
        val binding: FragmentTourCouponEnterBinding = FragmentTourCouponEnterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        // 返回一个 ViewHolder
        return VH(parent)
    }


    override fun onBindViewHolder(holder: VH, position: Int, item: TourRegionCouponBean.Data?) {
        // 设置item数据
        holder.binding.apply {
            item?.let { item ->

                imageView.loadAny(item.ImageUrl)


            }

        }


    }
}