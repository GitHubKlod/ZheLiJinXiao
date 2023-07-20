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
 * 核销订单详情
 *@Auther KLOD
 *2023/4/15 15:52
 */
class WriteOffOrderDetailActivity :
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
                //支付订单

            }
            cancelOrder.setOnClickListener {
                //取消订单
                MessageDialog.build()
                    .setTitle("温馨提示")
                    .setMessage("确定要取消订单吗?")
                    .setOkButton("确定")
                    .setCancelButton("取消")
                    .setOkButtonClickListener { _, _ ->

                        mViewModel.postCancelWriteOffOrder(orderNo)
                        false
                    }
                    .show()
            }
            orderNoTv.setOnClickListener {
                showToast("订单号复制成功")
                orderNo.copyToClipboard(this@WriteOffOrderDetailActivity)
            }
        }

        mViewModel.getWriteOffOrderDetail(orderNo)
    }

    override fun createObserver() {

        mViewModel.orderDetailState.observe(this) { resultState ->
            parseState(resultState, {
                setLayout(it)
            },
                {
                    showToast(it.errorMsg)
                })
        }
        mViewModel.cancelOrderState.observe(this) { resultState ->
            parseState(resultState, {
//                showToast(it.errorMsg)
                showToast("取消成功")

                //更新界面 重新请求
                mViewModel.getWriteOffOrderDetail(orderNo)
                //发送个消息 通知列表刷新数据
//                RxBus.get().

            }, { appException ->
                showToast(appException.errorMsg)
            })
        }
    }

    private fun setLayout(it: WriteOffOrderDetailBean) {

        mViewBind.apply {

            orderPayDesc.goneView()
            //支付状态 1 待支付 2 已取消 3 已完成
            when(it.Status){
                1->{
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
                2->{
                    orderPayState.text = "已取消"
                    orderPayState1.setValueTvText("已取消")
                    orderPayState1.setValueTvColor("#999999".parseColor())
                }
                3->{
                    orderPayState.text = "已完成"
                    orderPayState1.setValueTvText("已完成")
                    orderPayState1.setValueTvColor("#3D9C33".parseColor())
                }
            }

            //商家图片
            var stringList = mutableListOf<String>()
            try {
                stringList = it.LogoUrl.fromJsonToList(String::class.java)
            }catch (_:Exception){
            }
            if(stringList.isEmpty()){
                orderStoreImg.loadAny(R.mipmap.img_load_error)
            }else{
                orderStoreImg.loadAny(stringList[0])
            }

            //商家 名称
            orderStoreName.text = it.ShopName

            //商家 金额
            var sStr = SpannableString("￥${it.Amount.div(100.0).df("0.00")}")
            sStr.setSpan(
                AbsoluteSizeSpan(18, true),
                1,
                sStr.indexOf("."),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            orderStoreAmount.text = sStr

            //订单号
            val no = it.OrderNo+"  复制"
            val spannable = SpannableString(no)
            spannable.setSpan(
                ForegroundColorSpan("#E95929".parseColor()),
                no.indexOf("复制"),
                no.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            orderNoTv.setValueTvText(spannable)

            //支付方式
            orderPayMode.setValueTvText(it.PayTypeText)

            //下单时间
            orderCreateTime.setValueTvText(it.CreateTime)
            //支付时间没有
            orderPayTime.goneView()

            //收款门店
            orderStoreName1.setValueTvText(it.ShopName)
            //门店编号
            orderStoreNo.setValueTvText(it.ShopNo)

            //订单金额
            orderAmount.setValueTvText(it.Amount.div(100.0).df("0.00"))

            //优惠金额
            orderPreferentialAmount.setValueTvText(it.DiscountAmount.div(100.0).df("0.00"))

            //实付金额没有 隐藏  网页也没有
            orderRealAmount.goneView()

            //券信息 最后一个不要加换行
            var couponName =""
            for (i in 0 until it.OrderDiscountList.size){
                couponName += if(i==it.OrderDiscountList.size-1){
                    it.OrderDiscountList[i].Name
                }else{
                    it.OrderDiscountList[i].Name+"\n"
                }
            }

            orderCouponInfo.setValueTvText(couponName)
            //券面额
            orderCouponAmount.setValueTvText(it.DiscountAmount.div(100.0).df("0.00")+"元")
            //券数量
            orderCouponCount.setValueTvText(it.OrderDiscountList.size.toString()+"张")

        }

    }
}