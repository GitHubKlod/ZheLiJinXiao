package com.zhili.zljx.ui.home.mine.tour.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.zhili.zljx.bean.TourLineListBean
import com.zhili.zljx.databinding.ItemTourLineBinding
import com.zhili.zljx.databinding.ItemTourLineTagsBinding
import com.zhili.zljx.ext.loadAny
import com.zhili.zljx.ext.priceStyle

/**
 *@Auther KLOD
 *2023/4/17 19:19
 */
class TourLineAdapter:
    BaseQuickAdapter<TourLineListBean.Data, TourLineAdapter.VH>() {    // 自定义ViewHolder类
    class VH(
        parent: ViewGroup,
        val binding: ItemTourLineBinding = ItemTourLineBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        // 返回一个 ViewHolder
        return VH(parent)
    }


    override fun onBindViewHolder(holder: VH, position: Int, item: TourLineListBean.Data?) {
        // 设置item数据
        holder.binding.apply {
            item?.let { item ->

                image.loadAny(item.LineImage)
                title.text = item.LineTitle

                price.text = item.LinePrice.priceStyle(size = 15)

                count.text = "${item.SellNum}人下单"

                tagsRv.apply {
                    //设置布局管理器  流式布局 盒子布局
                    val flexBoxLayoutManager = FlexboxLayoutManager(context).apply {
                        //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical和horizontal。
                        flexDirection = FlexDirection.ROW //主轴为水平方向，起点在左端。
                        //flexWrap 默认情况下 Flex 跟 LinearLayout 一样，都是不带换行排列的，但是flexWrap属性可以支持换行排列。
                        flexWrap = FlexWrap.WRAP //按正常方向换行
                        //justifyContent 属性定义了项目在主轴上的对齐方式。
                        justifyContent = JustifyContent.FLEX_START //交叉轴的起点对齐。
                    }
                    layoutManager = flexBoxLayoutManager

                    adapter = TagsAdapter(item.LineTags)
                }

            }

        }


    }



}