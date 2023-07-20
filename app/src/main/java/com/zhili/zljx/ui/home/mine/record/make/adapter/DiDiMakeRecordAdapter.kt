package com.zhili.zljx.ui.home.mine.record.make.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhili.zljx.bean.DiDiMakeRecordBean
import com.zhili.zljx.bean.ECNYMakeRecordBean
import com.zhili.zljx.databinding.ItemCouponMakeRecordBinding
import com.zhili.zljx.ext.goneView
import com.zhili.zljx.ext.invisibleView
import com.zhili.zljx.ext.parseColor
import com.zhili.zljx.ext.safeIdCard
import com.zhili.zljx.ext.safePhone


/**
 *@Auther KLOD
 *2023/4/13 9:57
 */
class DiDiMakeRecordAdapter(items: List<ECNYMakeRecordBean.Data>) :
    BaseQuickAdapter<ECNYMakeRecordBean.Data, DiDiMakeRecordAdapter.VH>(items) {
    // 自定义ViewHolder类
    class VH(
        parent: ViewGroup,
        val binding: ItemCouponMakeRecordBinding = ItemCouponMakeRecordBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        // 返回一个 ViewHolder
        return VH(parent)
    }


    override fun onBindViewHolder(holder: VH, position: Int, item: ECNYMakeRecordBean.Data?) {
        // 设置item数据
        holder.binding.apply {
            item?.let { item ->

                //开奖时间
                runTime.invisibleView()
                //中签状态
                couponState.text = item.StatusStr
                couponState.setTextColor(
                    when (item.Status) {
                        1 -> "#FF58C020".parseColor()//已中签
                        2 -> "#FFFF3333".parseColor() //未中签
                        3 -> "#FFFF3333".parseColor() //中签但奖品全部发送失败
                        else -> "#FF3388ff".parseColor()
                    }
                )

                //活动名称
                keyValue1.setKeyTvText("活动名称")
                keyValue1.setValueTvText(item.ActivityTitle)
                //活动轮次
                keyValue2.setKeyTvText("活动轮次")
                keyValue2.setValueTvText(item.ActivitySessionTitle)
                //真实姓名
                keyValue3.setKeyTvText("真实姓名")
                keyValue3.setValueTvText(item.RealName)
                //身份证号码
                keyValue4.setKeyTvText("身份证号码")
                keyValue4.setValueTvText(item.IDCard.safeIdCard())
                //手机号码
                keyValue5.setKeyTvText("手机号码")
                keyValue5.setValueTvText(item.Mobile.safePhone())
                //预约时间
                keyValue6.setKeyTvText("预约时间")
                keyValue6.setValueTvText(item.CreateTime)
                //中签信息
//                if (item.Status == 2) {
//                    keyValue7.visibleView()
//                    keyValue7.setKeyTvText("中签奖品")
//                    keyValue7.setValueTvText(item.PrizeName)
//                } else {
                    keyValue7.goneView()
//                }

            }

        }

    }
}