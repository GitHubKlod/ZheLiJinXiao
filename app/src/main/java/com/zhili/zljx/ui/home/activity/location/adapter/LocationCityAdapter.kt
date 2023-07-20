package com.zhili.zljx.ui.home.activity.location.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhili.zljx.bean.LocationDataInfo
import com.zhili.zljx.databinding.ItemCityPickerBinding


/**
 *@Auther KLOD
 *2023/4/22 10:31
 */
class LocationCityAdapter : BaseQuickAdapter<LocationDataInfo, LocationCityAdapter.VH>() {

    // 自定义ViewHolder类
    class VH(
        parent: ViewGroup,
        val binding: ItemCityPickerBinding = ItemCityPickerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        // 返回一个 ViewHolder
        return VH(parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int, item: LocationDataInfo?) {
        // 设置item数据
        holder.binding.apply {
            item?.let {

                text.text = item.district

            }

        }
//        holder.binding.showMore.visibleView(item?.showMore == true)
    }

}
