package com.zhili.zljx.test.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhili.zljx.bean.ErrorBean
import com.zhili.zljx.databinding.ItemTestBinding

class TestAdapter : BaseQuickAdapter<ErrorBean, TestAdapter.VH>() {

    // 自定义ViewHolder类
    class VH(
        parent: ViewGroup,
        val binding: ItemTestBinding = ItemTestBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        // 返回一个 ViewHolder
        return VH(parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int, item: ErrorBean?) {
        // 设置item数据
        holder.binding.content.text = item?.content?:""
//        holder.binding.showMore.visibleView(item?.showMore == true)
    }

}

//class DiffTest : DiffUtil.ItemCallback<TestBean>() {
//    override fun areItemsTheSame(oldItem: TestBean, newItem: TestBean): Boolean {
//        // 判断是否是同一个 item（通常使用id字段判断）
//        return oldItem.id == newItem.id
//    }
//
//    override fun areContentsTheSame(oldItem: TestBean, newItem: TestBean): Boolean {
//        // 如果是同一个item，则判断item内的数据内容是否有变化
//        return oldItem.content == newItem.content
//
//    }
//
//}
