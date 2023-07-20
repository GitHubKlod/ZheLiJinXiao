package com.zhili.zljx.ui.home.mine.tour

import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.fragment.app.FragmentStatePagerAdapter
import com.angcyo.tablayout.delegate.ViewPager1Delegate
import com.klod.base.ext.parseState
import com.klod.base.ext.util.logE
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.bean.AgencyDetailBean
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.databinding.ActivityAgencyDetailBinding
import com.zhili.zljx.ext.callPhone
import com.zhili.zljx.ext.colorChange
import com.zhili.zljx.ext.goneView
import com.zhili.zljx.ext.loadAny
import com.zhili.zljx.ext.parseColor
import com.zhili.zljx.ext.visibleView

import com.zhili.zljx.ui.home.mine.tour.fragment.TourAgencyLineFragment

/**
 *@Auther KLOD
 *2023/5/2 13:19
 */
class AgencyDetailActivity :
    BaseMvvmActivity<AgencyDetailActivityVm, ActivityAgencyDetailBinding>() {


    private val fragments = mutableListOf<TourAgencyLineFragment>()

    private val agencyId by lazy {
        intent?.getIntExtra("agencyId", 0) ?: 0
    }
    private val agencyName by lazy {
        intent?.getStringExtra("agencyName") ?: ""
    }

    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.apply {

            titleBar.titleTv.text = this@AgencyDetailActivity.agencyName
            titleBar.ivBack.setOnClickListener { finish() }

            callPhone.setOnClickListener {
                Contacts.CallService.callPhone(this@AgencyDetailActivity)
            }
            showMore.setOnClickListener {

                if(agencyDesc.maxLines==2){
                    agencyDesc.maxLines = 999
                    showMore.text = "收缩"
                }else{
                    agencyDesc.maxLines = 2
                    showMore.text = "展开"
                }
            }

        }

        //初始化fragment
        fragments.clear()
        fragments.add(TourAgencyLineFragment.getInstance(agencyId))
        fragments.add(TourAgencyLineFragment.getInstance(agencyId))

        mViewBind.dslVp.adapter =
            object : FragmentStatePagerAdapter(
                supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            ) {
                override fun getCount() = fragments.size
                override fun getItem(position: Int) = fragments[position]

            }

        ViewPager1Delegate.install(mViewBind.dslVp, mViewBind.dslTab)


        mViewModel.getAgencyDetailInfo(agencyId)


    }

    override fun createObserver() {


        mViewModel.detailState.observe(this) { resultState ->

            parseState(resultState, {
                setAgencyInfo(it)
            }, {
                showToast(it.errorMsg)
            })

        }

    }

    private fun setAgencyInfo(it: AgencyDetailBean) {

            mViewBind.apply {

                agencyName.text = it.AgencyName
                agencyPhone.text = it.AgencyTel
                agencyAddress.text = it.AgencyAddress
                agencyBackground.loadAny(it.BackImage)

                agencyDesc.text = "简介： ${it.Introduce}".colorChange("#FF666666".parseColor(),0,3)
//                "${agencyDesc.lineCount}行".logE()


                showMore.goneView(agencyDesc.lineCount<=2)
                agencyDesc.maxLines = 2

            }

    }
}