package com.zhili.zljx.ui.home.mine.setting

import android.os.Bundle
import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.databinding.ActivityPushSwitchBinding
import com.zhili.zljx.utils.MMKVUtils


class PushSwitchActivity: BaseMvvmActivity<PushSwitchVm, ActivityPushSwitchBinding>() {


    override fun layoutId()= R.layout.activity_push_switch

    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.titleBar.titleTv.text = "推送设置"
        mViewBind.titleBar.ivBack.setOnClickListener { finish() }
        //读取数据 默认true

        mViewBind.articleSwitch.isChecked = MMKVUtils.getBoolean(Contacts.articlePushSwitch,true)
        mViewBind.goodsSwitch.isChecked = MMKVUtils.getBoolean(Contacts.goodsPushSwitch,true)
        mViewBind.jPushSwitch.isChecked = MMKVUtils.getBoolean(Contacts.jPushSwitch,true)
        mViewBind.cnxhSwitch.isChecked = MMKVUtils.getBoolean(Contacts.cnxhSwitch,true)
        mViewBind.dxtsSwitch.isChecked = MMKVUtils.getBoolean(Contacts.dxtsSwitch,true)

        //文章推送开关
        mViewBind.articleSwitch.setOnClickListener {

            mViewModel.saveArticlePushStatus( !mViewBind.articleSwitch.isChecked)

        }
        //商品推送开关
        mViewBind.goodsSwitch.setOnClickListener {

            mViewModel.saveGoodsPushStatus( !mViewBind.goodsSwitch.isChecked)

        }
        //极光推送开关
        mViewBind.jPushSwitch.setOnClickListener {

            mViewModel.saveJgPushStatus( !mViewBind.jPushSwitch.isChecked)

        }
        //猜你喜欢开关
        mViewBind.cnxhSwitch.setOnClickListener {

            mViewModel.saveCnxhStatus( !mViewBind.cnxhSwitch.isChecked)

        }
        //定向推送开关
        mViewBind.dxtsSwitch.setOnClickListener {

            mViewModel.saveDxtsStatus( !mViewBind.dxtsSwitch.isChecked)

        }


    }

    override fun createObserver() {

        //极光推送
        mViewModel.jPush.observe(this) {
            mViewBind.jPushSwitch.isChecked = it
            showToast(if(it) "已开启通知栏推送" else "已关闭通知栏推送")

//            if(it){
//                JPushUPSManager.turnOnPush(this) { tr->
//                    mViewBind.jPushSwitch.isChecked = tr.returnCode==0
//                    showShortToast(if(tr.returnCode==0)"开启通知栏推送成功" else "开启通知栏推送失败")
//                }
//            }else{
//                JPushUPSManager.turnOffPush(this) { tr->
//                    mViewBind.jPushSwitch.isChecked = tr.returnCode!=0
//                    showShortToast(if(tr.returnCode==0)"关闭通知栏推送成功" else "关闭通知栏推送失败")
//                }
//            }
        }
        //商品推送
        mViewModel.goods.observe(this) {
            mViewBind.goodsSwitch.isChecked = it
            showToast(if(it) "已开启商品推送" else "已关闭商品推送")

        }
        //文章推送
        mViewModel.article.observe(this) {
            mViewBind.articleSwitch.isChecked = it
            showToast(if(it) "已开启文章推送" else "已关闭文章推送")

        }
        //已开启猜你喜欢推送
        mViewModel.cnxh.observe(this) {
            mViewBind.cnxhSwitch.isChecked = it
            showToast(if(it) "已开启猜你喜欢推送" else "已关闭猜你喜欢推送")
        }
        //定向推送功能
        mViewModel.dxts.observe(this) {
            mViewBind.dxtsSwitch.isChecked = it
            showToast(if(it) "已开启定向推送功能" else "已关闭定向推送功能")

        }

    }

}