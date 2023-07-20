package com.zhili.zljx.ui.home.mine.order.writeoff

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhili.zljx.R
import com.zhili.zljx.bean.WriteOffOrderBean
import com.zhili.zljx.databinding.ItemWriteOffOrderBinding
import com.zhili.zljx.ext.df
import com.zhili.zljx.ext.fromJsonToList
import com.zhili.zljx.ext.loadAny
import com.zhili.zljx.ext.parseColor
import com.zhili.zljx.ext.visibleView


/**
 *@Auther KLOD
 *2023/4/13 15:04
 */
class WriteOffOrderAdapter(items: List<WriteOffOrderBean.Row>) :
    BaseQuickAdapter<WriteOffOrderBean.Row, WriteOffOrderAdapter.VH>(items) {

    // 自定义ViewHolder类
    class VH(
        parent: ViewGroup,
        val binding: ItemWriteOffOrderBinding = ItemWriteOffOrderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        // 返回一个 ViewHolder
        return VH(parent)
    }


    override fun onBindViewHolder(holder: VH, position: Int, item: WriteOffOrderBean.Row?) {
        // 设置item数据
        holder.binding.apply {
            item?.let { item ->


                //订单号
                orderNo.text  = "订单号: "+item.OrderNo
                //订单状态
                orderState.text = item.StatusText
                //订单状态颜色
                orderState.setTextColor(
                    when(item.Status){
                        1->"#E95929".parseColor()//待支付
                        2->"#999999".parseColor()//已取消
                        3->"#3D9C33".parseColor()//已完成
                        else->"#999999".parseColor()//已取消
                    }
                )

                //门店图片 需要从数组里取出来
                var stringList = mutableListOf<String>()
                try {
                    stringList = item.LogoUrl.fromJsonToList(String::class.java)
                }catch (_:Exception){ }
                if(stringList.isEmpty()){
                    storeHead.loadAny(R.mipmap.img_load_error)
                }else{
                    storeHead.loadAny(stringList[0])
                }
                //商家名称
                storeName.text = item.ShopName
                //消费了多少钱
                var sStr  =SpannableString("￥${item.DiscountAmount.div(100.0).df("0.00")}")
                    sStr.setSpan(
                    AbsoluteSizeSpan(18, true),
                    1,
                        sStr.indexOf("."),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                amount.text = sStr
                //什么时间消费的
                orderTime.text = item.CreateTime
                //是否显示取消订单按钮
                cancelOrder.visibleView(item.Status==1)





            }

        }


    }
}