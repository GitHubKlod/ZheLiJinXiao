package com.zhili.zljx.ui.home.mine.order.detail

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import com.klod.base.ext.parseState
import com.kongzue.dialogx.dialogs.MessageDialog
import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.bean.TourOrderDetailBean
import com.zhili.zljx.bean.WriteOffOrderDetailBean
import com.zhili.zljx.databinding.ActivityOrderDetailBinding
import com.zhili.zljx.ext.copyToClipboard
import com.zhili.zljx.ext.df
import com.zhili.zljx.ext.fromJsonToList
import com.zhili.zljx.ext.goneView
import com.zhili.zljx.ext.loadAny
import com.zhili.zljx.ext.parseColor
import com.zhili.zljx.ext.visibleView

/**
 * 文旅订单详情
 *@Auther KLOD
 *2023/4/15 15:52
 */
class TourOrderDetailActivity :
    BaseMvvmActivity<OrderDetailActivityVm, ActivityOrderDetailBinding>() {

    private val orderNo by lazy {
        intent?.getStringExtra("orderNo")?:""
    }

    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.apply {

            titleBar.titleTv.text = "订单详情"
            titleBar.ivBack.setOnClickListener { finish() }
            backOrderList.setOnClickListener { finish() }
            payOrder.setOnClickListener {
                //支付文旅订单

            }
            cancelOrder.setOnClickListener {
                //取消文旅订单
                MessageDialog.build()
                    .setTitle("温馨提示")
                    .setMessage("确定要取消订单吗?")
                    .setOkButton("确定")
                    .setCancelButton("取消")
                    .setOkButtonClickListener { _, _ ->

                        mViewModel.postTourCancelOrder(orderNo)
                        false
                    }
                    .show()
            }
            orderNoTv.setOnClickListener {
                showToast("订单号复制成功")
                orderNo.copyToClipboard(this@TourOrderDetailActivity)
            }
        }

        mViewModel.getTourOrderDetail(orderNo)
    }

    override fun createObserver() {

        mViewModel.tourOrderDetailState.observe(this) { resultState ->
            parseState(resultState, {
                setLayout(it)
            },
                {
                    showToast(it.errorMsg)
                })
        }
        mViewModel.cancelTourOrderState.observe(this) { resultState ->
            parseState(resultState, {
//                showToast(it.errorMsg)
                showToast("取消成功")
                //更新界面 重新请求
                mViewModel.getTourOrderDetail(orderNo)
                //发送个消息 通知列表刷新数据
//                RxBus.get().
            }, { appException ->
                showToast(appException.errorMsg)
            })
        }
    }

    private fun setLayout(it: TourOrderDetailBean) {

        mViewBind.apply {

            tourLl.visibleView()
            ll2.goneView()

            orderPayDesc.goneView()
            //支付状态 1 已支付 -1 已取消 0 待支付
            when(it.PayStatus){
                0->{
//                    1->"#E95929".parseColor()//待支付
//                    2->"#999999".parseColor()//已取消
//                    3->"#3D9C33".parseColor()//已完成
//                    else->"#999999".parseColor()//已取消
                    cancelOrder.visibleView()
                    payOrder.visibleView()
                    orderPayState.text = "待支付"
                    orderPayState1.setValueTvText("待支付")
                    orderPayState1.setValueTvColor("#E95929".parseColor())
                }
                -1->{
                    orderPayState.text = "已取消"
                    orderPayState1.setValueTvText("已取消")
                    orderPayState1.setValueTvColor("#999999".parseColor())
                }
                1->{
                    orderPayState.text = "已支付"
                    orderPayState1.setValueTvText("已支付")
                    orderPayState1.setValueTvColor("#3D9C33".parseColor())
                }
            }

            //所属团组
            orderTourGroup.setValueTvText(it.TravelGroups)
            //商家图片
//            var stringList = mutableListOf<String>()
//            try {
//                stringList = it.LogoUrl.fromJsonToList(String::class.java)
//            }catch (_:Exception){
//            }
//            if(stringList.isEmpty()){
//                orderStoreImg.loadAny(R.mipmap.img_load_error)
//            }else{
                orderStoreImg.loadAny(it.SpotImage)
//            }

            //商家 名称
            orderStoreName.text = it.SpotName

            //商家 金额
            var sStr = SpannableString("￥${it.OrderAmount.df("0.00")}")
            sStr.setSpan(
                AbsoluteSizeSpan(18, true),
                1,
                sStr.indexOf("."),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            orderStoreAmount.text = sStr

            //订单号
            val no = it.OrderNO+"  复制"
            val spannable = SpannableString(no)
            spannable.setSpan(
                ForegroundColorSpan("#E95929".parseColor()),
                no.indexOf("复制"),
                no.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            orderNoTv.setValueTvText(spannable)

            //支付方式
            orderPayMode.setValueTvText(it.PayName)

            //下单时间
            orderCreateTime.setValueTvText(it.CreateTime)
            //支付时间
            orderPayTime.setValueTvText(it.PayTime?:"")

            //收款门店
//            orderStoreName1.setValueTvText(it.ShopName)
            //门店编号
//            orderStoreNo.setValueTvText(it.ShopNo)

            //订单金额
            orderAmount.setValueTvText(it.OrderAmount.df("0.00"))
            //优惠金额
            orderPreferentialAmount.setValueTvText(it.ReduceAmount.df("0.00"))

            //实付金额
            orderRealAmount.setValueTvText(it.OrderAmount.minus(it.ReduceAmount).df("0.00"))

            //券信息 最后一个不要加换行
            var couponName =""
            for (i in 0 until it.CouponList.size){
                couponName += if(i==it.CouponList.size-1){
                    it.CouponList[i]
                }else{
                    it.CouponList[i]+"\n"
                }
            }
            orderCouponInfo.setValueTvText(couponName)

            //券面额
            orderCouponAmount.setValueTvText(it.ReduceAmount.df("0.00")+"元")
            //券数量
            orderCouponCount.setValueTvText(it.CouponList.size.toString()+"张")

        }

    }
}