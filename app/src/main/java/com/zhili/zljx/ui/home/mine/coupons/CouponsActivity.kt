package com.zhili.zljx.ui.home.mine.coupons

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.angcyo.tablayout.delegate.ViewPager1Delegate
import com.angcyo.tablayout.delegate2.ViewPager2Delegate
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.databinding.ActivityCouponsBinding
import com.zhili.zljx.ui.home.mine.coupons.fragment.ConsumerCouponFragment
import com.zhili.zljx.ui.home.mine.coupons.fragment.DiDiCouponFragment
import com.zhili.zljx.ui.home.mine.coupons.fragment.RmbCouponFragment
import com.zhili.zljx.ui.home.mine.coupons.fragment.fragment.ConsumerCouponTypeFragment

/**
 *@Auther KLOD
 *2023/4/3 16:37
 */
class CouponsActivity : BaseMvvmActivity<CouponsActivityVm, ActivityCouponsBinding>() {

    private val fragments = mutableListOf(
        ConsumerCouponFragment.getInstance(),
        RmbCouponFragment.getInstance(),
        DiDiCouponFragment.getInstance(),
//        ConsumerCouponFragment.getInstance(),
//        ConsumerCouponFragment.getInstance(),
    )


    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.titleBar.titleTv.text = "我的券包"
        mViewBind.titleBar.ivBack.setOnClickListener { finish() }


//        mViewBind.dslVp.adapter =
//            object : FragmentStatePagerAdapter(
//                supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//                override fun getCount()= fragments.size
//                override fun getItem( position: Int)=  fragments[position]
//
//            }
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