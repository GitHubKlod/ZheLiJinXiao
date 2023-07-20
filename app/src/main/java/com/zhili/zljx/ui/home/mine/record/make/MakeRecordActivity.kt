package com.zhili.zljx.ui.home.mine.record.make

import android.os.Bundle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.angcyo.tablayout.delegate2.ViewPager2Delegate
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.databinding.ActivityMakeRecordBinding
import com.zhili.zljx.ui.home.mine.record.make.fragment.MakeRecordFragment1
import com.zhili.zljx.ui.home.mine.record.make.fragment.MakeRecordFragment2
import com.zhili.zljx.ui.home.mine.record.make.fragment.MakeRecordFragment3


/**
 * 预约记录
 *@Auther KLOD
 *2023/4/12 15:02
 */
class MakeRecordActivity : BaseMvvmActivity<MakeRecordActivityVm, ActivityMakeRecordBinding>() {
    private val fragments = mutableListOf(
        MakeRecordFragment1.getInstance(),
        MakeRecordFragment2.getInstance(),
        MakeRecordFragment3.getInstance(),
    )


    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.apply {
            titleBar.titleTv.text = "预约记录"
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