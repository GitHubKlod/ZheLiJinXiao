package com.zhili.zljx.ui.home.search

import android.os.Bundle
import android.view.inputmethod.EditorInfo

import androidx.fragment.app.FragmentStatePagerAdapter

import com.angcyo.tablayout.delegate.ViewPager1Delegate

import com.zhili.zljx.base.BaseMvvmActivity

import com.zhili.zljx.databinding.ActivityGroupSearchBinding
import com.zhili.zljx.ui.home.search.fragment.GroupSearchFragment

/**
 *@Auther KLOD
 *2023/4/24 19:45
 */
class GroupSearchActivity: BaseMvvmActivity<GroupSearchActivityVm, ActivityGroupSearchBinding>() {



    private val fragments = mutableListOf(
        GroupSearchFragment.getInstance(0),
        GroupSearchFragment.getInstance(1),
        GroupSearchFragment.getInstance(2),
    )

    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.apply {

            titleBar.titleTv.text = "活动圈"
            titleBar.ivBack.setOnClickListener { finish() }

//            searchContent.addTextChangedListener { text ->
//
//                mViewModel.searchContext1 = text.toString()
//
//            }
            searchContent.setOnEditorActionListener{ v, actionId, event ->

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if(searchContent.text.toString().isEmpty()){
                        showToast("请输入关键词")
                    }else{
                        mViewModel.searchContext.value = searchContent.text.toString()

                    }
                }
                false
            }
            searchBtn.setOnClickListener {
                if(searchContent.text.toString().isEmpty()){
                    showToast("请输入关键词")
                }else{
                    mViewModel.searchContext.value = searchContent.text.toString()

                }
            }

        }


        mViewBind.dslVp.adapter =
            object : FragmentStatePagerAdapter(
                supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            ) {
                override fun getCount()= fragments.size
                override fun getItem( position: Int)=  fragments[position]

            }
//        mViewBind.dslVp.adapter =
//            object : FragmentStateAdapter(
//                childFragmentManager, lifecycle) {
//                override fun getItemCount()= fragments.size
//                override fun createFragment(position: Int) = fragments[position]
//            }
        ViewPager1Delegate.install(mViewBind.dslVp, mViewBind.dslTab)
//        ViewPager2Delegate.install(mViewBind.dslVp, mViewBind.dslTab)


    }

    override fun createObserver() {



    }
}