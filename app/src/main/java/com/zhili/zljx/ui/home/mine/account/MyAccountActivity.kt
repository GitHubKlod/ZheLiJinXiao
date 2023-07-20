package com.zhili.zljx.ui.home.mine.account

import android.os.Bundle
import com.klod.base.BaseViewModel
import com.klod.base.ext.parseState
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.bean.MyAccountCapitalBean
import com.zhili.zljx.bean.MyAccountRedDotBean
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.databinding.ActivityMyAccountBinding
import com.zhili.zljx.ext.df
import com.zhili.zljx.ext.startAC
import com.zhili.zljx.ui.common.HtmlTvActivity
import com.zhili.zljx.ui.common.WebViewActivity
import com.zhili.zljx.ui.home.mine.account.address.MyAddressActivity
import com.zhili.zljx.ui.home.mine.account.bank.MyBankCardActivity

/**
 *@Auther KLOD
 *2023/4/11 10:11
 */
class MyAccountActivity : BaseMvvmActivity<MyAccountActivityVm, ActivityMyAccountBinding>() {


    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.apply {

            titleBar.ivBack.setOnClickListener { finish() }
            titleBar.titleTv.text = "我的账户"

            redPackageDesc.setOnClickListener {
                startAC<HtmlTvActivity> {
//                    putExtra("htmlCode",)
                }
            }

            //红包余额
            redPackageItem1.setOnClickListener {
                startAC<WebViewActivity> {
                    putExtra("url",Contacts.JxHbWalletUrl)
                }
            }
            //购利点
             redPackageItem2.setOnClickListener {
                startAC<WebViewActivity> {
                    putExtra("url",Contacts.JxHbRightsValueUrl)
                }
            }
            //能量
             redPackageItem3.setOnClickListener {
                startAC<WebViewActivity> {
                    putExtra("url",Contacts.EnergyUrl)
                }
            }
            //银行卡
            redPackageItem5.setOnClickListener {
                startAC<MyBankCardActivity> {

                }
            }
            //收货地址
            redPackageItem6.setOnClickListener {
                startAC<MyAddressActivity> {
//                    putExtra("url",Contacts.EnergyUrl)
                }
            }
            //
            redPackageItem4.setOnClickListener {
                showToast("正在建设中")
            }
            redPackageDetail.setOnClickListener {
                startAC<WebViewActivity> {
                    putExtra("url",Contacts.JxHbRecordUrl)
                }
            }
            redPackageDrawing.setOnClickListener {
                startAC<WebViewActivity> {
                    putExtra("url",Contacts.JxHbExtractUrl)
                }
            }

        }



        mViewModel.getMyRedDot()
        mViewModel.getMyCapital()



    }



    override fun createObserver() {


        //我的红包
        mViewModel.redDotResultState.observe(this) {

            parseState(it, { myAccountRedDotBean ->
                setRedPackageView(myAccountRedDotBean)
            }, { appException ->
                showToast(appException.errorMsg)
            })

        }
        //我的资产
        mViewModel.capitalResultState.observe(this) {
            parseState(it, { myAccountCapitalBean ->

                setCapitalView(myAccountCapitalBean)

            }, { appException ->
                showToast(appException.errorMsg)
            })

        }


    }

    /**
     * 设置我的资产 视图数据
     */
    private fun setCapitalView(myAccountCapitalBean: MyAccountCapitalBean) {

        mViewBind.apply {

            item1Value .text = myAccountCapitalBean.WalletBalance.toString()
            item2Value .text = myAccountCapitalBean.CurrentHiPoint.toString()
            item3Value .text = myAccountCapitalBean.EnergyPoint.toString()
            item4Value .text = myAccountCapitalBean.EquityCard.toString()


        }

    }
    /**
     * 设置我的红包 视图数据
     */
    private fun setRedPackageView(myAccountRedDotBean: MyAccountRedDotBean) {

        mViewBind.apply {

            cRedPackage.text  = myAccountRedDotBean.CurrentRedDot.toString()
            todayRedPackage.text  = myAccountRedDotBean.TodayRedDot.toString()
            totalRedPackage.text  = myAccountRedDotBean.TotalRedDot.toString()

        }

    }
}