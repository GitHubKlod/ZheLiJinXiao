package com.zhili.zljx.ui.home.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhili.zljx.bean.ActivityHomeConfigBean
import com.zhili.zljx.bean.TourAgencyListBean
import com.zhili.zljx.databinding.ItemActivitySubsidyBinding
import com.zhili.zljx.databinding.ItemGridBinding
import com.zhili.zljx.databinding.ItemTourAgencyBinding
import com.zhili.zljx.ext.loadAny

/**
 *@Auther KLOD
 *2023/4/18 17:36
 */
class SubsidyAdapter :
    BaseQuickAdapter<ActivityHomeConfigBean.Data, SubsidyAdapter.VH>() {
        // 自定义ViewHolder类
        class VH(
            parent: ViewGroup,
            val binding: ItemActivitySubsidyBinding = ItemActivitySubsidyBinding.inflate(
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

                    subsidyName.text = item.name.ifEmpty { if(item.textList.isEmpty()) "" else item.textList[0].text }
                    subsidyDesc.text = item.text.ifEmpty { if(item.textList.isNotEmpty()&&item.textList.size>=2) item.textList[1].text else  ""}
                    subsidyImg.loadAny(item.img)
                    tv1.text = item.linkBtn

                }

            }


        }
}