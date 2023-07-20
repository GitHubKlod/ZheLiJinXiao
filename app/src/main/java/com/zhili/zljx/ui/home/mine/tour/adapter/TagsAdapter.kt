package com.zhili.zljx.ui.home.mine.tour.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhili.zljx.databinding.ItemTourLineTagsBinding

/**
 *@Auther KLOD
 *2023/5/3 11:13
 */
class TagsAdapter(items: List<String>) :
    BaseQuickAdapter<String, TagsAdapter.VH>(items) {    // 自定义ViewHolder类
    class VH(
        parent: ViewGroup,
        val binding: ItemTourLineTagsBinding = ItemTourLineTagsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        // 返回一个 ViewHolder
        return VH(parent)
    }


    override fun onBindViewHolder(holder: VH, position: Int, item: String?) {
        // 设置item数据
        holder.binding.apply {
            item?.let { item ->
                tag.text = item
            }
        }


    }
}