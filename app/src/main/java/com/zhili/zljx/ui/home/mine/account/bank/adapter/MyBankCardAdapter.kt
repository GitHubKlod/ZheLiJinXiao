package com.zhili.zljx.ui.home.mine.account.bank.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.chad.library.adapter.base.BaseQuickAdapter
import com.klod.base.network.NetworkUtil.url
import com.zhili.zljx.R
import com.zhili.zljx.bean.request.MyBankCardBean
import com.zhili.zljx.databinding.ItemBankCardBinding
import com.zhili.zljx.ext.loadAny
import com.zhili.zljx.ext.parseColor


/**
 *@Auther KLOD
 *2023/5/3 20:49
 */
class MyBankCardAdapter : BaseQuickAdapter<MyBankCardBean, MyBankCardAdapter.VH>() {

    // 自定义ViewHolder类
    class VH(
        parent: ViewGroup,
        val binding: ItemBankCardBinding = ItemBankCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        // 返回一个 ViewHolder
        return VH(parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int, item: MyBankCardBean?) {
        // 设置item数据
        holder.binding.apply {
            item?.let {

                bankName.text = item.bank
                cardType.text = "储蓄卡"
                cardNumber.text = if (item.cardNo.length > 4)
                    item.cardNo.substring(item.cardNo.length - 4)
                else
                    item.cardNo

                bankIcon.loadAny(item.icon)

//
                Glide.with(context)
                    .load(item.icon)
                    .into(object : CustomTarget<Drawable>() {
                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                            // do something with resource
//                            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.image)
                            Palette.from(ConvertUtils.drawable2Bitmap(resource)).generate { palette ->
                                val dominantColor = palette?.getDominantColor(ContextCompat.getColor(context, R.color.black))
                                bankImg.setBackgroundColor(dominantColor?: "#FF000000".parseColor())
                                // do something with dominant color
                            }
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                            // placeholder is cleared
                        }
                    })
            }

        }
//        holder.binding.showMore.visibleView(item?.showMore == true)
    }
}