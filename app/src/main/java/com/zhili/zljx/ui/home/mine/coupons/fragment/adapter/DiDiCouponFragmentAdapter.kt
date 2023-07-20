package com.zhili.zljx.ui.home.mine.coupons.fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhili.zljx.bean.DiDiGrantLogBean
import com.zhili.zljx.bean.RmbGrantLogBean
import com.zhili.zljx.databinding.ItemCouponsInventRmbBinding
import com.zhili.zljx.ext.df
import com.zhili.zljx.ext.fromHtml
import com.zhili.zljx.ext.goneView
import com.zhili.zljx.ext.loadAny
import com.zhili.zljx.ext.visibleView

/**
 *@Auther KLOD
 *2023/4/12 13:35
 */
class DiDiCouponFragmentAdapter(items: List<DiDiGrantLogBean.GrantLog.ECNY>) :
    BaseQuickAdapter<DiDiGrantLogBean.GrantLog.ECNY, DiDiCouponFragmentAdapter.VH>(items) {


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


    override fun onBindViewHolder(holder: VH, position: Int, item: DiDiGrantLogBean.GrantLog.ECNY?) {
        // 设置item数据
        holder.binding.apply {
            item?.let { item ->

                stateImg.goneView()

                rmbName.text = item.DiDiCouponName

//
                if(item.Discount==0.0){
                    //满减券
                    rmbAmount.text = item.Amount.df()
                    rmbSill.text = "满${item.LimitAmount.div(100.00).df()}减${ item.Amount.df()}"
                    tv1.visibleView()
                }else{
                    //打折券
                    rmbSill.text = "最高优惠${item.Amount.df()}元"
                    rmbAmount.text = "${item.Discount.div(10.0).df()}折"
                    tv1.goneView()
                }

                rmbTime.text ="${item.ExpiryEndTime}到期"

                rmbDesc.text = item.Explain.fromHtml()
                rmbMoreIcon.rotation = if (item.showMore) 180f else 0f
                rmbDesc.visibleView(item.showMore)
//
                rmbHead.loadAny(item.CoverImage)



            }

        }


    }
}