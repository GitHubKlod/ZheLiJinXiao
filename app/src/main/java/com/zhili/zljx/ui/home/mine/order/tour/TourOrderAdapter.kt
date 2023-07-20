package com.zhili.zljx.ui.home.mine.order.tour

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter

import com.zhili.zljx.bean.TourOrderBean
import com.zhili.zljx.databinding.ItemTourOrderBinding

import com.zhili.zljx.ext.df

import com.zhili.zljx.ext.goneView
import com.zhili.zljx.ext.loadAny
import com.zhili.zljx.ext.parseColor


/**
 *@Auther KLOD
 *2023/4/13 15:04
 */
class TourOrderAdapter(items: List<TourOrderBean.Data>) :
    BaseQuickAdapter<TourOrderBean.Data, TourOrderAdapter.VH>(items) {

    // 自定义ViewHolder类
    class VH(
        parent: ViewGroup,
        val binding: ItemTourOrderBinding = ItemTourOrderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        // 返回一个 ViewHolder
        return VH(parent)
    }


    override fun onBindViewHolder(holder: VH, position: Int, item: TourOrderBean.Data?) {
        // 设置item数据
        holder.binding.apply {
            item?.let { item ->


                //订单号
                orderNo.text = "订单号: " + item.OrderNO
                //订单状态

                when (item.PayStatus) {
                    1 -> {
                        orderState.text = "已支付"
                        orderState.setTextColor("#3D9C33".parseColor())
                    }

                    0 -> {
                        orderState.text = "待支付"
                        orderState.setTextColor("#E95929".parseColor())
                    }

                    -1 -> {
                        orderState.text = "已取消"
                        orderState.setTextColor("#999999".parseColor())
                    }

                    else -> {
                        orderState.text = ""
                    }
                }
                //门店图片
                storeHead.loadAny(item.SpotImage)
                //商家名称
                storeName.text = item.SpotName
                //消费了多少钱
                var sStr = SpannableString("￥${item.OrderAmount.df("0.00")}")
                sStr.setSpan(
                    AbsoluteSizeSpan(18, true),
                    1,
                    sStr.indexOf("."),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                amount.text = sStr
                //什么时间消费的
                orderTime.text = item.CreateTime

                //是否显示取消订单按钮 //已支付和 已取消就隐藏掉按钮
                cancelOrder.goneView(item.PayStatus == -1 || item.PayStatus == 1)
                payOrder.goneView(item.PayStatus == -1 || item.PayStatus == 1)


            }

        }


    }
}