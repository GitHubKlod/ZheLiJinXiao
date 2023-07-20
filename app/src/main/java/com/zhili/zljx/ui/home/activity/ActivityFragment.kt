package com.zhili.zljx.ui.home.activity

import android.app.ActionBar.LayoutParams
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.blankj.utilcode.util.ScreenUtils
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.klod.base.ext.parseState
import com.klod.base.ext.util.logE
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.indicator.CircleIndicator
import com.zhili.zljx.BuildConfig
import com.zhili.zljx.app.appViewModel
import com.zhili.zljx.base.BaseMvvmFragment
import com.zhili.zljx.bean.ActivityGroupBean
import com.zhili.zljx.bean.ActivityHomeConfigBean
import com.zhili.zljx.bean.ArticleBean
import com.zhili.zljx.databinding.FragmentActivityBinding
import com.zhili.zljx.ext.goneView
import com.zhili.zljx.ext.loadAny
import com.zhili.zljx.ext.parseColor
import com.zhili.zljx.ext.setRefreshLayoutListener
import com.zhili.zljx.ext.startAC
import com.zhili.zljx.ui.common.WebViewActivity
import com.zhili.zljx.ui.home.activity.adapter.ArticleAdapter
import com.zhili.zljx.ui.home.activity.adapter.GovCouponAdapter
import com.zhili.zljx.ui.home.activity.adapter.GridAdapter
import com.zhili.zljx.ui.home.activity.adapter.GroupAdapter
import com.zhili.zljx.ui.home.activity.adapter.SubsidyAdapter
import com.zhili.zljx.ui.home.activity.location.LocationSelectActivity
import com.zhili.zljx.ui.home.article.ArticleDetailActivity
import com.zhili.zljx.ui.home.article.GroupDetailActivity
import com.zhili.zljx.ui.home.search.GroupSearchActivity
import com.zhili.zljx.utils.MMKVUtils
import com.zhili.zljx.widget.adapter.MyBannerImageAdapter


/**
 *@Auther KLOD
 *2023/3/29 16:34
 * 首页 - 活动页面
 */
class ActivityFragment : BaseMvvmFragment<ActivityFragmentVm, FragmentActivityBinding>() {

//    private var mAgentWeb: AgentWeb? = null
//    private var mWebView: WebView? = null


    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.apply {

            //点击获取定位 , 先判断权限
            locationBar.getLocationTv.setOnClickListener {

                XXPermissions.with(mContext)
                    .permission(Permission.ACCESS_COARSE_LOCATION)
                    .permission(Permission.ACCESS_FINE_LOCATION)
                    .request(object : OnPermissionCallback {
                        override fun onGranted(
                            permissions: MutableList<String>,
                            allGranted: Boolean
                        ) {
                            if (allGranted) {
                                mViewModel.getLocationInfo()
                            } else {
                                showToast("未获取到定位权限 , 无法获取当前位置")
                            }
                        }

                        override fun onDenied(
                            permissions: MutableList<String>,
                            doNotAskAgain: Boolean
                        ) {
                            showToast("未获取到定位权限 , 无法获取当前位置")
                        }
                    })

            }

            //刷新
            activityRefresh.setRefreshLayoutListener(
                onRefresh = {
                    mViewModel.getActivityPageConfig()
                    cPage = 1
                    mViewModel.getArticleList(cPage)
                },
                onLoadMore = {
                    if (cPage <= maxPage) {
                        mViewModel.getArticleList(cPage)
                    } else {
                        it.finishLoadMoreWithNoMoreData()
                    }
                })
            //底部资讯文章
            bottomRecyclerView.apply {
                layoutManager = LinearLayoutManager(mContext)
                adapter = articleAdapter
            }

            groupLayout.groupMore.setOnClickListener {
                mContext.startAC<GroupSearchActivity> {  }
            }

        }

        if (XXPermissions.isGranted(
                mContext,
                Permission.ACCESS_COARSE_LOCATION,
                Permission.ACCESS_FINE_LOCATION
            )
        ) {
            mViewModel.getLocationInfo()
        } else {

        }
        //获取页面配置
        mViewModel.getActivityPageConfig()
        //选择地区
        mViewBind.locationBar.areaTv.setOnClickListener {

            mContext.startAC<LocationSelectActivity> { }

        }

        //适配器 点击
        articleAdapter.setOnItemClickListener { adapter, view, position ->

            mContext.startAC<ArticleDetailActivity> {
                putExtra("articleId", adapter.items[position].ArticleID)
            }

        }
        //活动圈
        groupAdapter.setOnItemClickListener { adapter, view, position ->

            mContext.startAC<GroupDetailActivity> {
                putExtra("subjectId", adapter.items[position].SubjectID)
            }

        }


        //享补贴
//        mSubsidyAdapter.setOnItemClickListener { adapter, view, position ->
//        }
        //政府券
//        mGovAdapter.setOnItemClickListener { adapter, view, position ->
//
//        }

    }


    override fun createObserver() {

        //页面配置数据
        mViewModel.pageConfig.observe(this) { resultState ->
            parseState(resultState, {
                configPage(it)
            }, {
                showToast(it.errorMsg)
            })
        }

        //监听定位信息
        appViewModel.locationInfo.observe(this) {
            it?.let {
                mViewBind.locationBar.apply {
                    getLocationTv.text = it.address
                    areaTv.text = it.district
                }
                //获取底部资讯文章 要在拿到定位后
                mViewModel.getArticleList(cPage)
                //获取活动圈
                mViewModel.getActivityGroupList()
                //获取页面配置
                mViewModel.getActivityPageConfig()
            }
        }
        //监听定位状态
        mViewModel.locationUiState.observe(this){
            if(it.isSuccess){
                //保存位置缓存
                MMKVUtils.setLocationInfo(it.data!!)
                //保存 位置信息
                appViewModel.locationInfo.value = it.data
            }else{
                showToast(it.errorMsg)
            }
        }

        //活动圈
        mViewModel.groupDataState.observe(this) { resultState ->

            parseState(resultState, {
                setGroupRv(it)
            }, {
                showToast(it.errorMsg)
            })

        }

        //文章
        mViewModel.articleDataState.observe(this) { resultState ->
            parseState(resultState, {
                setArticleRv(it)
            }, {
                mViewBind.activityRefresh.finishRefresh()
                mViewBind.activityRefresh.finishLoadMore()
                showToast(it.errorMsg)
            })
        }

    }


    private var articleAdapter = ArticleAdapter()

    /**
     *  设置底部文章
     */
    private fun setArticleRv(it: ArticleBean) {


        if (cPage == 1) {
            mViewBind.activityRefresh.finishRefresh()
            articleAdapter.submitList(it.Rows)
        } else {
            mViewBind.activityRefresh.finishLoadMore()
            articleAdapter.addAll(it.Rows)
        }
        cPage++
        maxPage = getMaxPage(it.Total)
    }

    private var groupAdapter = GroupAdapter()

    /**
     * 设置活动圈界面
     */
    private fun setGroupRv(it: ActivityGroupBean) {

        mViewBind.groupLayout.groupRv.apply {

            layoutManager = LinearLayoutManager(mContext)

            adapter = groupAdapter

        }

        groupAdapter.submitList(it.Rows)

    }

    override fun lazyLoadData() {


    }


    //Banner的 数据区分
    private var banner1 = ActivityHomeConfigBean()
    private var banner2 = ActivityHomeConfigBean()
    private var banner3 = ActivityHomeConfigBean()
    private var banner4 = ActivityHomeConfigBean()
    private var banner5 = ActivityHomeConfigBean()


    //纯图片 添加锚点的
    private var img1 = ActivityHomeConfigBean()
    private var img2 = ActivityHomeConfigBean()

    //政府券 多Item类型
    private var imgText2_1 = ActivityHomeConfigBean()

    //享补贴
    private var imgText1_1 = ActivityHomeConfigBean()

    //标题
    private var title1 = ActivityHomeConfigBean()
    private var title2 = ActivityHomeConfigBean()
    private var title3 = ActivityHomeConfigBean()


    private var mGovAdapter = GovCouponAdapter()
    private var mSubsidyAdapter = SubsidyAdapter()

    /**
     * 清空数据
     */
    private fun clearData() {

        banner1 = ActivityHomeConfigBean()
        banner2 = ActivityHomeConfigBean()
        banner3 = ActivityHomeConfigBean()
        banner4 = ActivityHomeConfigBean()
        banner5 = ActivityHomeConfigBean()
        img1 = ActivityHomeConfigBean()
        img2 = ActivityHomeConfigBean()
        imgText2_1 = ActivityHomeConfigBean()
    }

    /**
     * 配置页面  我的很大 你要忍一下
     *
     * 放最下面吧 好找
     */
    private fun configPage(it: MutableList<ActivityHomeConfigBean>) {

        clearData()


        it.forEach {

            when (it.type) {
                "t-userLocation" -> {
                    //顶部定位栏
                }

                "h-noticeBar" -> {
                    //顶部消息滚动栏
                }

                "h-swiper" -> {
                    //顶部Banner
                    if(it.dataList.isEmpty()){
                            return@forEach
                    }
                    if (banner1.type.isEmpty()) {
//                        "banner1".logE()

                        banner1 = it
                        mViewBind.topLayout.bannerTop
                            .setBannerGalleryEffect(8, 8)
                        setBanner(mViewBind.topLayout.bannerTop, it)

                    } else if (banner2.type.isEmpty()) {
//                        如果banner1已经有数据了 那就给2赋值  以此类推 实现同type不同数据
//                        "banner2".logE()

                        banner2 = it
                        setBanner(mViewBind.topLayout.bannerCenter, it)
                    } else if (banner3.type.isEmpty()) {
//                        "banner3".logE()
                        banner3 = it
                        setBanner(mViewBind.businessBanner, it)
                    } else if (banner4.type.isEmpty()) {
//                        "banner4".logE()

                        banner4 = it
                        setBanner(mViewBind.groupLayout.groupBanner, it)

                    }

                }


                "h-grid" -> {
                    //导航栏 xml里那banner下面的N个
                    setGridRv(it)

                }

                "img" -> {
                    //图片类型 需要根据定位参数 代码添加View的点击事件
                    if (img1.type.isEmpty()) {
                        img1 = it
//                        mViewBind.topLayout.image1.loadAny(it.img)
                        addCouponsAnchor(it, mViewBind.topLayout.img1Fl)
                    } else if (img2.type.isEmpty()) {
                        img2 = it
//                        mViewBind.businessCouponLayout.bg1.loadAny(it.img)
                        addCouponsAnchor(it, mViewBind.businessCouponLayout.img2Fl)
                    }

                }

                "t-imageText-2" -> {
                    //政府券 多Item类型Rv
                    if (imgText2_1.type.isEmpty()) {
                        imgText2_1 = it
                        setGovMultiItemAdapter(it)

                    }


                }

                "t-imageText-1" -> {
                    //享补贴
                    if (imgText1_1.type.isEmpty()) {
                        imgText1_1 = it
                        setSubsidyRv(it)
                    }
                }

                "t-article-list" -> {
                    //底部资讯 的 加载数量
                }

                "t-circle-list" -> {
                    //圈子 加载
                    mViewModel.getActivityGroupList(it.totalSize)
                }

                "t-plate-title" -> {
                    //标题 更多
                    if (title1.type.isEmpty()) {
                        title1 = it
                    } else if (title2.type.isEmpty()) {
                        title2 = it
                    } else if (title3.type.isEmpty()) {
                        title3 = it
                    }
                }

            }

        }


    }

    /**
     * 享补贴
     */
    private fun setSubsidyRv(it: ActivityHomeConfigBean) {

        mViewBind.subsidyLayout.subsidyRv.apply {

            layoutManager = LinearLayoutManager(mContext)

            adapter = mSubsidyAdapter

        }

        mSubsidyAdapter.submitList(it.dataList)

        mSubsidyAdapter.setOnItemClickListener { adapter, view, position ->
            if (adapter.items[position].link.isNotEmpty()) {
                mContext.startAC<WebViewActivity> {
                    putExtra("url", adapter.items[position].link)
                }
            }
        }
        mViewBind.subsidyLayout.subsidyMore.setOnClickListener { _ ->
            if (title2.link.isNotEmpty()) {
                mContext.startAC<WebViewActivity> {
                    putExtra("url", title2.link)
                }
            }
        }

    }


    /**
     * 设置政府券(也许不是) 多类型Item
     */
    private fun setGovMultiItemAdapter(it: ActivityHomeConfigBean) {

//        mGovAdapter.remove()
        if (it.dataList.isEmpty()) {
            mViewBind.govCouponLayout.rootCl.goneView()
        } else {

            mViewBind.govCouponLayout.govCouponRv.apply {
                layoutManager = StaggeredGridLayoutManager(
                    if (it.dataList.size == 1) 1 else 2,
                    RecyclerView.VERTICAL
                )
                adapter = mGovAdapter
            }
            for (i in 0 until it.dataList.size) {
                when (it.dataList.size) {
                    1 -> {
                        it.dataList[i].itemType = GovCouponAdapter.ArticleType2
                    }

                    3, 5 -> {
                        it.dataList[i].itemType =
                            if (i == 0)
                                GovCouponAdapter.ArticleType1
                            else
                                GovCouponAdapter.CouponTypeSmall
                    }

                    else -> {
                        it.dataList[i].itemType = GovCouponAdapter.CouponTypeSmall
                    }
                }
            }
            mGovAdapter.submitList(it.dataList)

        }

//        mGovAdapter.notifyDataSetChanged()

        mGovAdapter.setOnItemClickListener { adapter, view, position ->

            if (adapter.items[position].link.isNotEmpty()) {
                mContext.startAC<WebViewActivity> {
                    putExtra("url", adapter.items[position].link)
                }
            }
        }
        mViewBind.govCouponLayout.govCouponMore.setOnClickListener { _ ->
            if (title1.link.isNotEmpty()) {
                mContext.startAC<WebViewActivity> {
                    putExtra("url", title1.link)
                }
            }
        }

    }


    //添加锚点
    private fun addCouponsAnchor(it: ActivityHomeConfigBean, pView: ViewGroup) {

        "当前屏幕宽度 = $screenWidth".logE()

        pView.removeAllViews()

        val imageView = ImageView(mContext)
        val flp = FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
        if(it.margin.isNotEmpty()&&it.margin.size==4){
            flp.leftMargin = it.margin[3].toString().toDouble().toInt()
//            flp.topMargin
            flp.rightMargin = it.margin[1].toString().toDouble().toInt()
//            flp.bottomMargin
        }
        imageView.layoutParams = flp
        imageView.adjustViewBounds = true
        pView.addView(imageView)
        imageView.loadAny(it.img)


        it.anchorList.forEach {
            val lp = FrameLayout.LayoutParams(
                getRealWidthHeight(it.width),
                getRealWidthHeight(it.height)
//            it.width.toDouble().toInt(),
//                it.height.toDouble().toInt()
            )
            lp.leftMargin = getRealWidthHeight(it.left)+flp.leftMargin
            lp.topMargin = getRealWidthHeight(it.top)
            val iv = View(mContext)

            if(BuildConfig.DEBUG){
                iv.setBackgroundColor("#88FFFFFF".parseColor())
            }

            iv.layoutParams = lp
            iv.setOnClickListener { _ ->
                mContext.startAC<WebViewActivity> {
                    putExtra("url", it.link)
                }
            }
            pView.addView(iv)
        }


    }

    /**
     * 设置 顶部导航栏
     */
    private fun setGridRv(it: ActivityHomeConfigBean) {


        var gridAdapter = GridAdapter(it.dataList)


        mViewBind.topLayout.gridRv.apply {

            layoutManager = GridLayoutManager(mContext, it.columnNum)

            adapter = gridAdapter
        }
        gridAdapter.setOnItemClickListener { adapter, view, position ->

//            showToast(adapter.items[position].name)
            if (adapter.items[position].link.isNotEmpty()) {
                mContext.startAC<WebViewActivity> {
                    putExtra("url", adapter.items[position].link)
                }
            }

        }

    }


    /**
     * 设置界面中的所有Banner
     */
    private fun setBanner(
        banner: Banner<Any, BannerAdapter<Any, *>>,
        bannerData: ActivityHomeConfigBean
    ) {



        val clp = if( banner.layoutParams is LinearLayout.LayoutParams){
            LinearLayout.LayoutParams(banner.layoutParams)
        }else{

            ConstraintLayout.LayoutParams(banner.layoutParams as ConstraintLayout.LayoutParams)
        }

        if(bannerData.margin.isNotEmpty()&&bannerData.margin.size==4){
            //上右下左
            clp.leftMargin = bannerData.margin[3].toString().toDouble().toInt()
//            flp.topMargin
            clp.rightMargin =  bannerData.margin[1].toString().toDouble().toInt()
//            flp.bottomMargin
        }
        //--------------------------简单使用-------------------------------
        banner.addBannerLifecycleObserver(this)//添加生命周期观察者
            .setAdapter(MyBannerImageAdapter(bannerData.dataList))
            .setOnBannerListener { data, position ->

                if ((data as ActivityHomeConfigBean.Data).link.isNotEmpty()) {
                    mContext.startAC<WebViewActivity> {
                        putExtra("url", data.link)
                    }
                }
            }
            .indicator = CircleIndicator(mContext)

        banner.layoutParams  = clp

    }

    private val screenWidth by lazy {
        ScreenUtils.getScreenWidth()
    }

    //前端的 宽高是根据375px宽度来算的
    private val referenceWidth = 750

    private fun getRealWidthHeight(old: String): Int {

        var old1 = old.ifEmpty { "0" }.toDouble()
        var real = old1.times(screenWidth).div(referenceWidth)
        return real.toInt()

    }


//    //注入Cookie
//    AgentWebConfig.syncCookie(".yiuxiu.com","ZLJX_LOGINKEY=${MMKVUtils.getToken()?.access_token?:""}");
//
//    mAgentWeb =  AgentWeb.with(this@ActivityFragment)
//    .setAgentWebParent(activityAgentWeb, FrameLayout.LayoutParams(-1, -1))
//    .useDefaultIndicator()
////                .setReceivedTitleCallback(mCallback)
//    .setWebChromeClient(mWebChromeClient)
//    .setWebViewClient(mWebViewClient)
//    .setMainFrameErrorView(R.layout.agentweb_error_page, R.id.reloadUrl)
//    .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
//    .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
//    .createAgentWeb()
//    .ready()
//    .go(Contacts.HomeActivityUrl)
////
//
//    mAgentWeb?.let {
//        var webSetting :WebSettings? =  it.agentWebSettings?.webSettings
//
//        webSetting?.apply {
//            userAgentString = Contacts.UserAgentAndroid
//            javaScriptEnabled = true
//        }
//
//        mWebView = it.webCreator?.webView
//        //设置webview取消右边滚动条和水波纹效果
//        it.webCreator.webView.overScrollMode = View.OVER_SCROLL_NEVER
//        it.webCreator.webView.isVerticalScrollBarEnabled = false
//        it.webCreator.webView.isHorizontalScrollBarEnabled = false
//
//        it.jsInterfaceHolder.addJavaObject("android", AndroidInterface())
//    }
//    /**
//     * JS 交互类
//     */
//    class AndroidInterface{
//        @JavascriptInterface //该注解一定要加让Javascript可以访问
//        fun getData(url: String) {
//
//        }
//    }
//    private val mWebViewClient: WebViewClient = object : WebViewClient() {
//        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//            //拦截操作
//            val url = view?.url?:""
//            //查看加载的地址
//            "活动WebView地址  = $url".logE()
//
//            if(url.isNotEmpty()){
//                //检测跳转
//                return mContext.checkUrl(url)
//            }
//            return super.shouldOverrideUrlLoading(view, request)
//        }
//        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
//            //do you  work
//        }
//    }
//    private val mWebChromeClient: WebChromeClient = object : WebChromeClient() {
//        override fun onProgressChanged(view: WebView?, newProgress: Int) {
//            //do you work
//        }
//    }
//
//    fun getCanBack(): Boolean {
//        mWebView?.let {
//            if (it.canGoBack()) {
//                //获取webView的浏览记录
//                val mWebBackForwardList: WebBackForwardList = it.copyBackForwardList()
//                //这里的判断是为了让页面在有上一个页面的情况下，跳转到上一个html页面，而不是退出当前activity
//                if (mWebBackForwardList.currentIndex > 0) {
//                    val historyUrl =
//                        mWebBackForwardList.getItemAtIndex(mWebBackForwardList.currentIndex - 1).url
//                    if (historyUrl != it.url) {
//                        it.goBack()
//                        return true
//                    }
//                }
//            } else {
//                return false
//            }
//        }
//
//        return false
//    }
//

}