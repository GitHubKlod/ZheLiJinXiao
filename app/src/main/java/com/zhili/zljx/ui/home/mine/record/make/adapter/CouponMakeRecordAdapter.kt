package com.zhili.zljx.ui.home.mine.record.make.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhili.zljx.R
import com.zhili.zljx.bean.CouponMakeRecordBean
import com.zhili.zljx.databinding.ItemCouponMakeRecordBinding
import com.zhili.zljx.ext.goneView
import com.zhili.zljx.ext.parseColor
import com.zhili.zljx.ext.safeIdCard
import com.zhili.zljx.ext.safePhone
import com.zhili.zljx.ext.visibleView

/**
 *@Auther KLOD
 *2023/4/12 17:20
 */
class CouponMakeRecordAdapter(items: List<CouponMakeRecordBean.Row>) :
    BaseQuickAdapter<CouponMakeRecordBean.Row, CouponMakeRecordAdapter.VH>(items) {
    // 自定义ViewHolder类
    class VH(parent: ViewGroup,
             val binding: ItemCouponMakeRecordBinding = ItemCouponMakeRecordBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        // 返回一个 ViewHolder
        return VH(parent)
    }


    override fun onBindViewHolder(holder: VH, position: Int, item: CouponMakeRecordBean.Row?) {
        // 设置item数据
        holder.binding.apply {
            item?.let { item ->

                //开奖时间
                runTime.text = item.DrawTime+"开奖"
                //中签状态
                couponState.text = item.StatusText
                couponState.setTextColor(
                    when(item.Status){
                        2-> "#FF58C020".parseColor()//已中签
                        3-> "#FFFF3333".parseColor() //未中签
                        else->"#FF3388ff".parseColor()
                    }
                )

                //活动名称
                keyValue1.setKeyTvText("活动名称")
                keyValue1.setValueTvText(item.MakeActivityName)
                //活动轮次
                keyValue2.setKeyTvText("活动轮次")
                keyValue2.setValueTvText(item.MakeActivitySessionName)
                //领券城市
                keyValue3.setKeyTvText("领券城市")
                keyValue3.setValueTvText(
                    if(item.Extend.cityName.isNotEmpty())
                        item.Extend.cityName[0].label
                    else
                        ""
                )
                //真实姓名
                keyValue4.setKeyTvText("真实姓名")
                keyValue4.setValueTvText(item.Extend.realName)
                //身份证号码
                keyValue5.setKeyTvText("身份证号码")
                keyValue5.setValueTvText(item.Extend.IdCard.safeIdCard())
                //手机号码
                keyValue6.setKeyTvText("手机号码")
                keyValue6.setValueTvText(item.Extend.phoneNumber.safePhone())
                //中签信息
                if(item.Status==2){
                    keyValue7.visibleView()
                    keyValue7.setKeyTvText("中签奖品")
                    keyValue7.setValueTvText(item.PrizeName)
                }else{
                    keyValue7.goneView()
                }

            }

        }

    }
}