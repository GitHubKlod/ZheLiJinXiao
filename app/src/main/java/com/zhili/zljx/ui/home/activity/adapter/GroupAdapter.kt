package com.zhili.zljx.ui.home.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhili.zljx.bean.ActivityGroupBean
import com.zhili.zljx.bean.ActivityHomeConfigBean
import com.zhili.zljx.bean.TourAgencyListBean
import com.zhili.zljx.databinding.ItemActivityGroupBinding
import com.zhili.zljx.databinding.ItemGridBinding
import com.zhili.zljx.databinding.ItemTourAgencyBinding
import com.zhili.zljx.ext.loadAny
import com.zhili.zljx.ext.loadAnyHead

/**
 *@Auther KLOD
 *2023/4/18 17:36
 */
class GroupAdapter :
    BaseQuickAdapter<ActivityGroupBean.Row, GroupAdapter.VH>() {
        // 自定义ViewHolder类
        class VH(
            parent: ViewGroup,
            val binding: ItemActivityGroupBinding = ItemActivityGroupBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
        ) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
            // 返回一个 ViewHolder
            return VH(parent)
        }


        override fun onBindViewHolder(holder: VH, position: Int, item: ActivityGroupBean.Row?) {
            // 设置item数据
            holder.binding.apply {
                item?.let { item ->


                    var head = ""
                    if(item.Media.isNotEmpty()){
                        head = item.Media[0].FileUrl
                    }
                    groupImg.loadAny(head)


                    groupTitle.text = item.Title

                    groupNumber.text  = "${item.CollectNumber}人关注了话题"

                    groupUpName.text = item.NickName.ifEmpty { "浙里金消用户" }
                    groupUpHeader.loadAnyHead(item.HeadPortraitUrl)

                    upTime.text = "创建于: ${item.CreateTime}"
                }

            }


        }
}