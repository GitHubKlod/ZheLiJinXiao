package com.zhili.zljx.ui.home.mine.order.tour

import android.os.Bundle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.angcyo.tablayout.delegate2.ViewPager2Delegate
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.databinding.ActivityTourOrderBinding


/**
 *@Auther KLOD
 *2023/4/13 14:24
 */
class TourOrderActivity :
    BaseMvvmActivity<TourOrderActivityVm, ActivityTourOrderBinding>() {

    private val fragments = mutableListOf(
        TourOrderFragment.getInstance(-2),
        TourOrderFragment.getInstance(0),
        TourOrderFragment.getInstance(-1),
        TourOrderFragment.getInstance(1)
    )

    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.apply {
            titleBar.titleTv.text = "文旅订单"
            titleBar.ivBack.setOnClickListener { finish() }
        }

        mViewBind.dslVp.adapter =
            object : FragmentStateAdapter(
                supportFragmentManager, lifecycle) {
                override fun getItemCount()= fragments.size
                override fun createFragment(position: Int) = fragments[position]
            }
        ViewPager2Delegate.install(mViewBind.dslVp, mViewBind.dslTab)

    }

    override fun createObserver() {


    }
}