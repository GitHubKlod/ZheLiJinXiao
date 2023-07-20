package com.zhili.zljx.ui.home.mine.tour

import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.angcyo.tablayout.DslTabLayout
import com.angcyo.tablayout.delegate.ViewPager1Delegate
import com.angcyo.tablayout.delegate2.ViewPager2Delegate
import com.klod.base.ext.parseState
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.bean.TourAgencyClassBean
import com.zhili.zljx.bean.TourLineListBean
import com.zhili.zljx.bean.TourRegionListBean
import com.zhili.zljx.bean.TourStrategyListBean
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.databinding.ActivityTourHomeBinding
import com.zhili.zljx.ext.setRefreshLayoutListener
import com.zhili.zljx.ext.startAC
import com.zhili.zljx.ext.toDp
import com.zhili.zljx.ui.common.WebViewActivity
import com.zhili.zljx.ui.home.mine.tour.adapter.TourLineAdapter
import com.zhili.zljx.ui.home.mine.tour.adapter.TourStrategyAdapter
import com.zhili.zljx.ui.home.mine.tour.fragment.TourHomeAgencyFragment
import com.zhili.zljx.ui.home.mine.tour.fragment.TourCouponEnterFragment
import java.util.Timer
import java.util.TimerTask


/**
 *@Auther KLOD
 *2023/4/17 16:49
 */
class TourHomeActivity : BaseMvvmActivity<TourHomeActivityVm, ActivityTourHomeBinding>() {


    private var fragments = mutableListOf<TourHomeAgencyFragment>()
    private var fragmentsTourEnter = mutableListOf<TourCouponEnterFragment>()


//    private var tourLineList = mutableListOf<TourLineListBean.Data>()
    private var tourLineAdapter = TourLineAdapter()

    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.apply {
            titleBar.titleTv.text = "文旅活动"
            titleBar.ivBack.setOnClickListener { finish() }

//            getTourCoupon.setOnClickListener {
//                showToast("领文旅券")
//            }

            moreTourAgency.setOnClickListener {
//                showToast("更多旅行社")
                startAC<TourAgencyActivity> {  }
            }
            moreTourStrategy.setOnClickListener {
                showToast("更多攻略")
            }

            refreshLayout.setEnableRefresh(false)
            refreshLayout.setRefreshLayoutListener(onRefresh = {

            }, onLoadMore = {
                if (cPage <= maxPage) {
                    mViewModel.getTourLineList(cPage)
                } else {
                    it.finishLoadMoreWithNoMoreData()
                }
            })
            //旅游攻略Rv
            tourStrategyRv.apply {
                layoutManager =
                    LinearLayoutManager(this@TourHomeActivity, RecyclerView.HORIZONTAL, false)

                adapter = strategyAdapter

            }
            //旅游路线 RV
            tourLineRv.apply {
                layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
                adapter = tourLineAdapter
            }
        }


        mViewModel.getTourAgencyClass()
        mViewModel.getTourStrategy()
        mViewModel.getTourLineList(cPage)


        //旅行攻略点击
        strategyAdapter.setOnItemClickListener { adapter, view, position ->

        }
        //旅游路线点击
        tourLineAdapter.setOnItemClickListener { adapter, view, position ->
            //跳转 旅行线路详情页
            startAC<WebViewActivity> {
                putExtra("url", Contacts.TourLineUrl+adapter.items[position].ID)
            }

        }


        //获取领券入口 地区列表
        mViewModel.getTourRegionList()

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

        //旅游路线
        mViewModel.tourLineListState.observe(this) { resultState ->
            parseState(resultState, {
                setTourLineRv(it)
            }, {
                mViewBind.refreshLayout.finishLoadMore()
                showToast(it.errorMsg)
            })
        }

        //旅游攻略
        mViewModel.tourStrategyListState.observe(this) { resultState ->
            parseState(resultState, {
                setTourStrategyRv(it)
            }, {
                showToast(it.errorMsg)
            })
        }

        //地区列表 tab
        mViewModel.regionListState.observe(this) { resultState ->
            parseState(resultState, {
                setTourEnterTabVp(it)
            }, {
                showToast(it.errorMsg)
            })
        }

        //动态设置 入口vp的高度
//        mViewModel.vpHeight.observe(this){
//            if(it!=0){
//
//                val newHeight = it
//                //解决办法就是先得到原来的params然后，再设置回去。
//                val layoutParams =
//                    ConstraintLayout.LayoutParams(mViewBind.dslVpEnter.layoutParams as ConstraintLayout.LayoutParams)
//                layoutParams.height = newHeight
//                mViewBind.dslVpEnter.layoutParams = layoutParams
//            }
//        }


    }


    private val strategyAdapter = TourStrategyAdapter(mutableListOf())

    /**
     * 设置旅游攻略  //暂时隐藏
     */
    private fun setTourStrategyRv(it: TourStrategyListBean) {


//        if (it.Total == 0) {
//            mViewBind.tourStrategyCl.goneView()
//        } else {
//            mViewBind.tourStrategyCl.visibleView()
//            strategyAdapter.submitList(it.DataList)
//        }


    }


    /**
     * 设置底部Rv 旅游路线
     */
    private fun setTourLineRv(it: TourLineListBean) {

        if (cPage == 1) {
//            commonRefreshLayout.finishRefresh()
//            tourLineList = it.DataList
            tourLineAdapter.submitList(it.DataList)

        } else {
            mViewBind.refreshLayout.finishLoadMore()
            tourLineAdapter.addAll(it.DataList)
        }
        cPage++
        maxPage = getMaxPage(it.Total)

    }

    /**
     * 设置 领券入口
     */
    private fun setTourEnterTabVp(it: MutableList<TourRegionListBean>) {

//

        fragmentsTourEnter.clear()
//        fragments.add(TourAgencyFragment.getInstance(0))
//        it.add(0, TourAgencyClassBean("全部", 0))
        it.forEach {

            fragmentsTourEnter.add(TourCouponEnterFragment.getInstance(it.ID))

            mViewBind.dslTabEnter.addView(TextView(this).apply {
                text = it.RegionName
                gravity = Gravity.CENTER
                layoutParams = DslTabLayout.LayoutParams(-2, -2).apply {
                    leftMargin = 4.toDp()
                    rightMargin = 4.toDp()
                }
                setPadding((15).toDp(), 0, (15).toDp(), 0)
            })
        }
//        mViewBind.dslVpEnter.adapter =
//            object : FragmentStateAdapter(
//                supportFragmentManager, lifecycle) {
//                override fun getItemCount()= fragmentsTourEnter.size
//                override fun createFragment(position: Int) = fragmentsTourEnter[position]
//            }
        mViewBind.dslVpEnter.adapter =
            object : FragmentStatePagerAdapter(
                supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            ) {
                override fun getCount()= fragmentsTourEnter.size
                override fun getItem( position: Int)=  fragmentsTourEnter[position]

            }
        ViewPager1Delegate.install(mViewBind.dslVpEnter, mViewBind.dslTabEnter)

    }

    /**
     * 设置 tab 和 vp   3个旅行社
     */
    private fun setTabVp(it: MutableList<TourAgencyClassBean>) {

//

        fragments.clear()
//        fragments.add(TourAgencyFragment.getInstance(0))
        it.add(0, TourAgencyClassBean("全部", 0))
        it.forEach {

            fragments.add(TourHomeAgencyFragment.getInstance(it.ID))


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
            object : FragmentStateAdapter(
                supportFragmentManager, lifecycle) {
                override fun getItemCount()= fragments.size
                override fun createFragment(position: Int) = fragments[position]
            }


        mViewBind.dslTab.configTabLayoutConfig {
            onSelectIndexChange = { fromIndex, selectIndexList, reselect, fromUser ->
                if (fromUser) {
                    isChange = true
                    vpIndex = selectIndexList.first()
                    mViewBind.dslVp.setCurrentItem(selectIndexList.first(), true)

                }
            }
        }

        ViewPager2Delegate.install(mViewBind.dslVp, mViewBind.dslTab)

        startTimer()
//        countDown.start()
    }

    private fun startTimer() {

        timer.schedule(timerTask, 3000,3000)

    }

    override fun onDestroy() {
        timer.cancel()
        timerTask.cancel()

        super.onDestroy()

    }

    private var isChange = false

    private var vpIndex = 0

    //定时器
    private var timer: Timer = Timer()

    //定时任务
    private var timerTask: TimerTask = object : TimerTask() {
        override fun run() {
            runOnUiThread {
                if (isChange) {
                    isChange = false
                }else{
                    vpIndex++
                    if (vpIndex >= fragments.size) {
                        vpIndex = 0
                    }
                    mViewBind.dslVp.setCurrentItem(vpIndex, true)
                }
            }
        }
    }

}