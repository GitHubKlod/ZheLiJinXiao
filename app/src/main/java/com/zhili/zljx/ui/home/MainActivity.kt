package com.zhili.zljx.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.BarUtils
import com.google.android.material.badge.BadgeDrawable
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import com.klod.base.ext.parseState
import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.bean.AppVersionInfoBean
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.databinding.ActivityMainBinding
import com.zhili.zljx.ext.checkConstraintSet
import com.zhili.zljx.ext.parseColor
import com.zhili.zljx.ext.parseColorStateList
import com.zhili.zljx.ext.startAC
import com.zhili.zljx.ui.common.WebViewActivity
import com.zhili.zljx.ui.home.activity.ActivityFragment
import com.zhili.zljx.ui.home.consume.ConsumeFragment
import com.zhili.zljx.ui.home.exhibition.ExhibitionFragment
import com.zhili.zljx.ui.home.message.MessageFragment
import com.zhili.zljx.ui.home.mine.MineFragment
import com.zhili.zljx.ui.login.LoginActivity
import com.zhili.zljx.utils.MMKVUtils


class MainActivity : BaseMvvmActivity<MainActivityVm, ActivityMainBinding>() {

    private var fragments: MutableList<Fragment> = mutableListOf(

    )

    private var consumeFragment = ConsumeFragment()
    private var exhibitionFragment = ExhibitionFragment()
    private var activityFragment = ActivityFragment()
    private var messageFragment = MessageFragment()
    private var mineFragment = MineFragment()

    private val fragmentTag = mutableListOf(
        "ConsumeFragment",
        "ExhibitionFragment",
        "ActivityFragment",
        "MessageFragment",
        "MineFragment"
    )

    private var lastFragment = 0
    override fun initView(savedInstanceState: Bundle?) {

        //判断是否有保存数据
        if (savedInstanceState != null) {
            fragments.clear()
            consumeFragment =
                (supportFragmentManager.getFragment(savedInstanceState, fragmentTag[0])
                    ?: ConsumeFragment()) as ConsumeFragment
            exhibitionFragment =
                (supportFragmentManager.getFragment(savedInstanceState, fragmentTag[1])
                    ?: ExhibitionFragment()) as ExhibitionFragment
            activityFragment =
                (supportFragmentManager.getFragment(savedInstanceState, fragmentTag[2])
                    ?: ActivityFragment()) as ActivityFragment
            messageFragment =
                (supportFragmentManager.getFragment(savedInstanceState, fragmentTag[3])
                    ?: MessageFragment()) as MessageFragment
            mineFragment = (supportFragmentManager.getFragment(savedInstanceState, fragmentTag[4])
                ?: MineFragment()) as MineFragment
            loadData()
        } else {
            loadData()
        }


        mViewBind.navView.itemIconTintList = null
        //获取底部导航图标颜色，根据图标颜色设置文字颜色
        mViewBind.navView.itemTextColor = R.drawable.home_bnv_tv.parseColorStateList(this)

//        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        //悬浮按钮点击
        mViewBind.floatBtn.setOnClickListener {
            mViewBind.navView.selectedItemId = R.id.homeFragment3
        }

//        mViewBind.navView.setupWithNavController(navController)

        //监听BNV点击事件
        mViewBind.navView.setOnItemSelectedListener {

//                    NavigationUI.onNavDestinationSelected(it, navController)
            when (it.itemId) {
                R.id.homeFragment1 -> {
                    if (lastFragment == 0) {
                        //点击了当前页面的item 是否刷新

                    } else {
                        switchFragment(lastFragment, 0)
                        lastFragment = 0
//                        setWebViewToBottom()
                        checkFragment()
                    }
                }

                R.id.homeFragment2 -> {

                    if (lastFragment == 1) {
                        //点击了当前页面的item 是否刷新

                    } else {
                        switchFragment(lastFragment, 1)
                        lastFragment = 1
//                        setWebViewToBottom()

                        checkFragment()
                    }
                }

                R.id.homeFragment3 -> {
                    if (!MMKVUtils.getBoolean(Contacts.IsLogin)) {
                        startAC<LoginActivity> {}
                        return@setOnItemSelectedListener false
                    } else {

                        if (lastFragment == 2) {
                            //点击了当前页面的item 是否刷新

                        } else {
                            switchFragment(lastFragment, 2)
                            lastFragment = 2
                            setWebViewToNav()

//                            checkFragment()
                        }
                    }
                }

                R.id.homeFragment4 -> {
                    if (!MMKVUtils.getBoolean(Contacts.IsLogin)) {
                        startAC<LoginActivity> {}
                        return@setOnItemSelectedListener false
                    } else {

                        if (lastFragment == 3) {
                            //点击了当前页面的item 是否刷新

                        } else {
//                            switchFragment(lastFragment, 3)
//                            lastFragment = 3
////                            setWebViewToBottom()
//                            checkFragment()
                            startAC<WebViewActivity> {
                                putExtra("url", Contacts.HomeMessageUrl)
                            }
                            return@setOnItemSelectedListener false

                        }
                    }
                }

                R.id.homeFragment5 -> {
                    if (!MMKVUtils.getBoolean(Contacts.IsLogin)) {
                        startAC<LoginActivity> {}
                        return@setOnItemSelectedListener false
                    } else {

                        if (lastFragment == 4) {
                            //点击了当前页面的item 是否刷新

                        } else {
                            switchFragment(lastFragment, 4)
                            lastFragment = 4
                            setWebViewToNav()
//                            checkFragment()
                        }
                    }
                }
            }
            true
        }

        //默认 第一个fragment显示
        switchFragment(lastFragment, 0)
        lastFragment = 0

        setWebViewToBottom()
//        checkFragment()

//        showUpdateAppDialog("",false,"","")

        //检查更新
        mViewModel.getVersionInfo()
        //获取消息未读数量
        mViewModel.getTotalUnReadCount()

    }


    /**
     * 加载数据
     */
    private fun loadData() {

        //加载fragment
        fragments.apply {
            add(consumeFragment)
            add(exhibitionFragment)
            add(activityFragment)
            add(messageFragment)
            add(mineFragment)
        }

    }

    /**
     * 切换fragment
     */
    private fun switchFragment(lastFragment: Int, index: Int) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        //隐藏上个Fragment
        transaction.hide(fragments[lastFragment])
        if (!fragments[index].isAdded) {
            transaction.add(R.id.nav_host_fragment_activity_main, fragments[index])
        }
        transaction.show(fragments[index]).commitAllowingStateLoss()


    }

    /**
     * 检查fragment是否需要改变约束
     */
    private fun checkFragment() {


        if (lastFragment == 0) {
            consumeFragment.getCurrentUrl()?.checkConstraintSet()
            return
        }
        if (lastFragment == 1) {
            exhibitionFragment.getCurrentUrl()?.checkConstraintSet()
            return
        }
        if (lastFragment == 3) {
            messageFragment.getCurrentUrl()?.checkConstraintSet()
            return
        }

    }

    override fun createObserver() {
        mViewModel.versionState.observe(this) { resultState ->

            parseState(resultState, {
                setVersionInfo(it)
            }, {
                showToast(it.errorMsg)
            })

        }

        //添加角标
        mViewModel.unReadCountState.observe(this) { resultState ->

            parseState(resultState, {
                val badge: BadgeDrawable =
                    mViewBind.navView.getOrCreateBadge(R.id.homeFragment4)
                badge.isVisible = it != 0
                badge.backgroundColor = R.color.FFE95929.parseColor(this)
                badge.number = it
            }, {

            })

        }

    }


    /**
     * 设置版本信息
     */
    private fun setVersionInfo(it: AppVersionInfoBean) {

        //弹窗提示  当前code > = 发布的版本 那就是说明是更新的版本无需更新
        if (AppUtils.getAppVersionCode() >= it.MinVersion) {
//            showToast("当前已是最新版")
        } else {

            showUpdateAppDialog(
                it.VersionNo,
                it.IsForceUpdate == 1,
                it.ChangeLog
            )
        }
    }

    /**
     * 改变约束到底部
     */
    private fun setWebViewToBottom() {
        var set = ConstraintSet()
        mViewBind.navHostFragmentActivityMain.setPadding(0, BarUtils.getStatusBarHeight(), 0, 0)

        set.clone(mViewBind.container)
        set.connect(
            mViewBind.navHostFragmentActivityMain.id,
            ConstraintSet.BOTTOM,
            mViewBind.navView.id,
            ConstraintSet.BOTTOM
        )
        set.applyTo(mViewBind.container)
    }

    /**
     * 改变约束到导航栏
     */
    private fun setWebViewToNav(topPadding: Int = 0) {
        var set = ConstraintSet()
        mViewBind.navHostFragmentActivityMain.setPadding(0, topPadding, 0, 0)

        set.clone(mViewBind.container)
        set.connect(
            mViewBind.navHostFragmentActivityMain.id,
            ConstraintSet.BOTTOM,
            mViewBind.navView.id,
            ConstraintSet.TOP
        )
        set.applyTo(mViewBind.container)
    }


    /**
     * 保存数据
     *
     * @param outState
     */

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        for (i in fragments.indices) {
            if (fragments[i].isAdded) {
                supportFragmentManager.putFragment(
                    outState,
                    fragmentTag[i],
                    fragments[i]
                )
            }
        }
        super.onSaveInstanceState(outState)
    }


    private var exitTime: Long = 0//双击返回键退出程序
    override fun onBackPressed() {
        if (lastFragment == 0 && consumeFragment.getCanBack()) {
            return
        }
        if (lastFragment == 1 && exhibitionFragment.getCanBack()) {
            return
        }
        if (lastFragment == 3 && messageFragment.getCanBack()) {
            return
        }
        if (System.currentTimeMillis() - exitTime > 2000) {
            showToast("再按一次退出程序")
            exitTime = System.currentTimeMillis()

        } else {
            moveTaskToBack(true)
        }

    }

    /**
     * 改变约束
     */
    @Subscribe(tags = [Tag("setConstraintSet")])
    fun setConstraintSet(param: String) {  // 这里也可以写成(String tag, Object param)

        when (param) {
            "toBottom" -> {
                if (lastFragment == 0 || lastFragment == 1 || lastFragment == 3)
                    setWebViewToBottom()
            }

            "toNav" -> {
                setWebViewToNav(BarUtils.getStatusBarHeight())
            }
        }
    }

    /**
     * 切换Fragment
     */
    @Subscribe(tags = [Tag("setMainFragmentSelect")])
    fun setMainFragmentSelect(param: String) {  // 这里也可以写成(String tag, Object param)

        when (param) {
            "0" -> {

            }

            "1" -> {

            }

            "2" -> {

            }

            "3" -> {

            }

            "4" -> {

//                mViewBind.navView.selectedItemId  = R.id.homeFragment5
//                mViewBind.navHostFragmentActivityMain.setPadding(0,0,0,0)

            }
        }
    }


}