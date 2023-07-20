package com.zhili.zljx.ui.home.mine

import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.klod.base.ext.parseState
import com.klod.base.ext.util.logE
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.zhili.zljx.R
import com.zhili.zljx.app.appViewModel
import com.zhili.zljx.base.BaseMvvmFragment
import com.zhili.zljx.bean.EnergyPointBean
import com.zhili.zljx.bean.MyBottomGameBean
import com.zhili.zljx.bean.UserInfo
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.databinding.FragmentMineBinding
import com.zhili.zljx.ext.callPhone
import com.zhili.zljx.ext.df
import com.zhili.zljx.ext.loadAny
import com.zhili.zljx.ext.loadAnyHead
import com.zhili.zljx.ext.parseColor
import com.zhili.zljx.ext.safePhone
import com.zhili.zljx.ext.startAC
import com.zhili.zljx.ui.common.WebViewActivity
import com.zhili.zljx.ui.home.mine.account.MyAccountActivity
import com.zhili.zljx.ui.home.mine.coupons.CouponsActivity
import com.zhili.zljx.ui.home.mine.order.tour.TourOrderActivity
import com.zhili.zljx.ui.home.mine.order.writeoff.WriteOffOrderActivity
import com.zhili.zljx.ui.home.mine.record.make.MakeRecordActivity
import com.zhili.zljx.ui.home.mine.setting.SettingActivity
import com.zhili.zljx.ui.home.mine.tour.TourHomeActivity
import com.zhili.zljx.utils.MMKVUtils

/**
 *@Auther KLOD
 *2023/3/29 16:37
 */
class MineFragment : BaseMvvmFragment<MineFragmentVm, FragmentMineBinding>() {
    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.apply {
            //设置点击事件
            mySetting.setOnClickListener {
                mContext.startAC<SettingActivity> {}
            }
            //查看能量 点击事件
            myLevelBg.setOnClickListener {
                mContext.startAC<WebViewActivity> {
                    putExtra("url",Contacts.EnergyUrl)
                }
            }
            //我的券包点击事件
            myCoupons.setOnClickListener {
                mContext.startAC<CouponsActivity> { }
//                startActivity()
            }
            //我的账户点击事件
            myAccount.setOnClickListener {
                mContext.startAC<MyAccountActivity> { }
            }
            //预约记录点击事件
            myFunction1.setOnClickListener {
                mContext.startAC<MakeRecordActivity> { }
            }
            //在线订单
            myOnlineOrder.setOnClickListener {
                mContext.startAC<WebViewActivity> {
                    putExtra("url",Contacts.SuperMarketOrderUrl)
                }
            }

            //核销订单点击事件
            myWriteOffOrder.setOnClickListener {
                mContext.startAC<WriteOffOrderActivity> { }
            }
            //文旅订单点击事件
            myTourOrder.setOnClickListener {
                mContext.startAC<TourOrderActivity> { }
            }
            //企业券
            myEnterpriseOrder.setOnClickListener {

                mContext.startAC<WebViewActivity> {
                    putExtra("url",Contacts.CompanyCouponUrl)
                }

            }

            //广告Banner点击
            myCenterBanner.setOnClickListener {
                //宝箱活动
                mContext.startAC<WebViewActivity> {
                    putExtra("url",Contacts.JxHbUrl)
                }
            }
            //政策申请
            myFunction2.setOnClickListener {
                mContext.startAC<WebViewActivity> {
                    putExtra("url",Contacts.CarShowUrl)
                }
            }
            //商家入住
            myFunction3.setOnClickListener {
                mContext.startAC<WebViewActivity> {
                    putExtra("url",Contacts.ShopEnterUrl)
                }
            }
            //联系客服
            myFunction4.setOnClickListener {
                Contacts.CallService.callPhone(mContext)
            }
            //便民服务
            myFunction5.setOnClickListener {
                mContext.startAC<WebViewActivity> {
                    putExtra("url",Contacts.PublishServiceUrl)
                }
            }
            //意见反馈
            myFunction6.setOnClickListener {
                mContext.startAC<WebViewActivity> {
                    putExtra("url",Contacts.FeedBackUrl)
                }
            }
            //报名记录
            myFunction7.setOnClickListener {

            }
            myRefreshLayout.setEnableLoadMore(false)
            myRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
                override fun onRefresh(refreshLayout: RefreshLayout) {
                    getMultiData()

                }

                override fun onLoadMore(refreshLayout: RefreshLayout) {


                }
            })

            //安卓6.0不支持drawableTint需要手动设置
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
                // 在 Android 6.0 及以上版本中运行的代码
                val drawable = mViewBind.tv21.compoundDrawables[0].mutate()
                drawable.setColorFilter(R.color.white.parseColor(mContext), PorterDuff.Mode.SRC_IN)
                mViewBind.tv21.setCompoundDrawables(null, null, drawable, null)
                mViewBind.tv31.setCompoundDrawables(null, null, drawable, null)
            }


        }


        appViewModel.userInfo.observe(this){
            it?.let {
                setUserInfoView(it)
            }
        }

        getMultiData()

    }

    private fun getMultiData(){
        mViewModel.getMyInfo()

        mViewModel.getEnergyPointInfo()
    }

    override fun createObserver() {

        mViewModel.userInfo.observe(this) { resultState ->
            parseState(
                resultState, {
                    //保存个人信息
//                    "aa ${ it}".logE()
                    appViewModel.userInfo.value = it
                    MMKVUtils.setUser(it)

                }, { appException ->
                    showToast(appException.errorMsg)
                }
            )
        }
        mViewModel.energyPointState.observe(this) { resultState ->
            parseState(
                resultState, {
                    setEnergyPointView(it)
                }, { appException ->
                    showToast(appException.errorMsg)
                }
            )
        }

        appViewModel.userInfo.observe(this) {
            it?.apply {
                setUserInfoView(it)
            }
        }

        mViewModel.gameData.observe(this) {

            it?.let {
                setGameRv(it)
            }
        }

    }

    private var gameAdapter = MineGameAdapter()

    private fun setGameRv(it: MutableList<MyBottomGameBean>) {


        mViewBind.myBottomRv.apply {

            layoutManager = GridLayoutManager(mContext, 2)

            adapter = gameAdapter


        }


        gameAdapter.submitList(it)
        gameAdapter.setOnItemClickListener { adapter, view, position ->
            when (position) {
                0 -> {
                    //金华市2023年文旅券发放
                    mContext.startAC<TourHomeActivity> {  }
                }
                1 -> {
                    //宝箱活动
                    mContext.startAC<WebViewActivity> {
                        putExtra("url",Contacts.JxHbUrl)
                    }
                }
                2 -> {
                    //竞猜赢红包
                    mContext.startAC<WebViewActivity> {
                        putExtra("url",Contacts.JcHbUrl)
                    }
                }
                3 -> {
                    //能量PK
                    mContext.startAC<WebViewActivity> {
                        putExtra("url",Contacts.PkUrl)
                    }
                }
            }
        }

    }

    /**
     * 设置能量点VIew
     */
    private fun setEnergyPointView(energyPointBean: EnergyPointBean) {

        mViewBind.apply {

//            myLevelIcon.loadAny(energyPointBean.SmIconUrl)
            myLevelBg.loadAny(energyPointBean.BkSmImageUrl, R.mipmap.bg_my_level_def)
            myLevelValue.text = energyPointBean.CurrentEnergyPoint.df("#.##")
//            myLevelName.text = energyPointBean.EnergyPointLevelName
            myLevelMax.text = "/" + energyPointBean.EnergyPointLevelMaxVal.df("#.##")
            myLevelProgress.progress = energyPointBean.CurrentEnergyPoint.toInt()
            myLevelProgress.max = energyPointBean.EnergyPointLevelMaxVal.toInt()

        }

    }


    /**
     * 设置用户信息界面
     */
    private fun setUserInfoView(userInfo: UserInfo) {
        mViewBind.myRefreshLayout.finishRefresh()
        userInfo.run {
            mViewBind.myHeadImg.loadAnyHead(HeadPortraitUrl)

            mViewBind.myName.text = NickName.ifEmpty { RealName }

            mViewBind.MyPhone.text = Mobile.safePhone()

        }
    }

    override fun lazyLoadData() {


    }
}