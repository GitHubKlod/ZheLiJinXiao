package com.zhili.zljx.ui.home.mine.order.writeoff

import android.os.Bundle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.angcyo.tablayout.delegate2.ViewPager2Delegate
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.databinding.ActivityWriteOffOrderBinding

/**
 *@Auther KLOD
 *2023/4/13 14:24
 */
class WriteOffOrderActivity :
    BaseMvvmActivity<WriteOffOrderActivityVm, ActivityWriteOffOrderBinding>() {

    private val fragments = mutableListOf(
        WriteOffOrderFragment.getInstance(0),
        WriteOffOrderFragment.getInstance(1),
        WriteOffOrderFragment.getInstance(2),
        WriteOffOrderFragment.getInstance(3)
    )



    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.apply {
            titleBar.titleTv.text = "核销订单"
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