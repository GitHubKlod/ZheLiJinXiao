package com.zhili.zljx.ui.home.mine.coupons.fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhili.zljx.R
import com.zhili.zljx.bean.MyCouponListBean
import com.zhili.zljx.databinding.ItemCouponsInventRmbBinding
import com.zhili.zljx.ext.df
import com.zhili.zljx.ext.fromHtml
import com.zhili.zljx.ext.loadAny
import com.zhili.zljx.ext.visibleView

/**
 *@Auther KLOD
 *2023/4/10 11:17
 */
class ConsumerCouponTypeFragmentAdapter(var type: Int, items: List<MyCouponListBean.Row>) :
    BaseQuickAdapter<MyCouponListBean.Row, ConsumerCouponTypeFragmentAdapter.VH>(items) {


    // 自定义ViewHolder类
    class VH(
        parent: ViewGroup,
        val binding: ItemCouponsInventRmbBinding = ItemCouponsInventRmbBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        // 返回一个 ViewHolder
        return VH(parent)
    }


    override fun onBindViewHolder(holder: VH, position: Int, item: MyCouponListBean.Row?) {
        // 设置item数据
        holder.binding.apply {
            item?.let { item ->



                rmbName.text = item.Name
                rmbAmount.text = item.Amount.div(100.00).df("#.##")
                rmbSill.text =
                    if (item.CouponType == 1) "满${item.LimitAmount.div(100.00).df("#.##")}使用" else "无门槛"
//                rmbTime.text = if (type == 1) item.ExpiryBeginTime else "${item.ExpiryEndTime}到期"
                rmbTime.text = "${item.ExpiryEndTime}到期"
                rmbDesc.text = item.Explain.fromHtml()
                rmbMoreIcon.rotation = if (item.showMore) 180f else 0f
                rmbDesc.visibleView(item.showMore)

                rmbHead.loadAny(item.CoverImage)


                //券状态 1 待生效 2 已过期 3 已使用 4 已失效 5 已冻结
                stateImg.setImageResource(
                    when (item.CouponStatus) {
                        1 -> { //待生效
                            R.mipmap.img_coupons_type2
                        }

                        2 -> { //已过期
                            R.mipmap.img_coupons_type4
                        }

                        3 -> { //已使用
                            R.mipmap.img_coupons_type3
                        }

                        4 -> { //已失效
                            R.mipmap.img_coupons_type4
                        }
                        5 -> { //已失效
                            R.mipmap.img_coupons_type5
                        }
                        else -> 0
                    }
                )
            }

        }


    }

}