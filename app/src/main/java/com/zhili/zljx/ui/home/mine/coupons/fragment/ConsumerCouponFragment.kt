package com.zhili.zljx.ui.home.mine.coupons.fragment

import android.os.Bundle
import androidx.fragment.app.FragmentStatePagerAdapter

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.angcyo.tablayout.delegate.ViewPager1Delegate
import com.angcyo.tablayout.delegate2.ViewPager2Delegate
import com.zhili.zljx.base.BaseMvvmFragment
import com.zhili.zljx.databinding.FragmentConsumerCouponsBinding
import com.zhili.zljx.ui.home.mine.coupons.CouponsActivityVm
import com.zhili.zljx.ui.home.mine.coupons.fragment.fragment.ConsumerCouponTypeFragment

/**
 * 消费券 券包
 *@Auther KLOD
 *2023/4/10 11:15
 */
class ConsumerCouponFragment : BaseMvvmFragment<CouponsActivityVm, FragmentConsumerCouponsBinding>() {
    companion object{
        fun getInstance(): ConsumerCouponFragment {
            return ConsumerCouponFragment()
        }
    }
    private val fragments = mutableListOf(
        ConsumerCouponTypeFragment.getInstance(2),
        ConsumerCouponTypeFragment.getInstance(1),
        ConsumerCouponTypeFragment.getInstance(3)
    )


    override fun initView(savedInstanceState: Bundle?) {

//        mViewBind.dslVp.adapter =
//            object : FragmentStatePagerAdapter(
//                childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
//            ) {
//                override fun getCount()= fragments.size
//                override fun getItem( position: Int)=  fragments[position]
//
//            }
        mViewBind.dslVp.adapter =
            object : FragmentStateAdapter(
                childFragmentManager, lifecycle) {
                override fun getItemCount()= fragments.size
                override fun createFragment(position: Int) = fragments[position]
            }
//        ViewPager1Delegate.install(mViewBind.dslVp, mViewBind.dslTab)
        ViewPager2Delegate.install(mViewBind.dslVp, mViewBind.dslTab)


    }

    override fun createObserver() {

    }



    override fun lazyLoadData() {

    }
}