package com.zhili.zljx.ui.home.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhili.zljx.bean.ActivityHomeConfigBean
import com.zhili.zljx.bean.TourAgencyListBean
import com.zhili.zljx.databinding.ItemGridBinding
import com.zhili.zljx.databinding.ItemTourAgencyBinding
import com.zhili.zljx.ext.loadAny

/**
 *@Auther KLOD
 *2023/4/18 17:36
 */
class GridAdapter(items: List<ActivityHomeConfigBean.Data>) :
    BaseQuickAdapter<ActivityHomeConfigBean.Data, GridAdapter.VH>(items) {
        // 自定义ViewHolder类
        class VH(
            parent: ViewGroup,
            val binding: ItemGridBinding = ItemGridBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
        ) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
            // 返回一个 ViewHolder
            return VH(parent)
        }


        override fun onBindViewHolder(holder: VH, position: Int, item: ActivityHomeConfigBean.Data?) {
            // 设置item数据
            holder.binding.apply {
                item?.let { item ->

                    gridTv.text = item.name

                    gridImg.loadAny(item.img)


                }

            }


        }
}