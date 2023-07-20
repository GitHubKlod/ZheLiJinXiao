package com.zhili.zljx.ui.home.mine.tour

import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.fragment.app.FragmentStatePagerAdapter

import com.angcyo.tablayout.DslTabLayout
import com.angcyo.tablayout.delegate.ViewPager1Delegate
import com.klod.base.ext.parseState
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.bean.TourAgencyClassBean
import com.zhili.zljx.databinding.ActivityTourAgencyBinding
import com.zhili.zljx.ext.toDp
import com.zhili.zljx.ui.home.mine.tour.fragment.TourAgencyFragment


/**
 *@Auther KLOD
 *2023/5/2 10:17
 */
class TourAgencyActivity : BaseMvvmActivity<TourAgencyActivityVm, ActivityTourAgencyBinding>() {


    var fragments = mutableListOf<TourAgencyFragment>()

    override fun initView(savedInstanceState: Bundle?) {


        mViewBind.apply {

            titleBar.titleTv.text = "旅行社"
            titleBar.ivBack.setOnClickListener { finish() }

        }

        mViewModel.getTourAgencyClass()

    }

    override fun createObserver() {

        //旅行社分类
        mViewModel.classDataState.observe(this) { resultState ->
            parseState(resultState, {
                setTabVp(it)
            }, {
                showToast(it.errorMsg)
            })
        }

    }

    /**
     * 设置 tab 和 vp
     */
    private fun setTabVp(it: MutableList<TourAgencyClassBean>) {

//

        fragments.clear()
//        fragments.add(TourAgencyFragment.getInstance(0))
        it.add(0, TourAgencyClassBean("全部", 0))
        it.forEach {

            fragments.add(TourAgencyFragment.getInstance(it.ID))


            mViewBind.dslTab.addView(TextView(this).apply {
                text = it.ClassName
                gravity = Gravity.CENTER
                layoutParams = DslTabLayout.LayoutParams(-2, -2).apply {
                    leftMargin = 4.toDp()
                    rightMargin = 4.toDp()
                }
                setPadding((15).toDp(), 0, (15).toDp(), 0)

            })

        }

        mViewBind.dslVp.adapter =
            object : FragmentStatePagerAdapter(
                supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            ) {
                override fun getCount() = fragments.size
                override fun getItem(position: Int) = fragments[position]

            }



        ViewPager1Delegate.install(mViewBind.dslVp, mViewBind.dslTab)


    }
}