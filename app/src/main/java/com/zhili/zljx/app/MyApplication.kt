package                                         com.zhili.zljx.app

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.multidex.MultiDex
import com.amap.api.location.AMapLocationClient
import com.hjq.http.EasyConfig
import com.hjq.http.config.IRequestApi
import com.hjq.http.config.RequestServer
import com.hjq.http.model.HttpHeaders
import com.hjq.http.model.HttpParams
import com.hjq.toast.Toaster
import com.kongzue.dialogx.DialogX
import com.kongzue.dialogx.style.IOSStyle
import com.kongzue.dialogx.style.MIUIStyle
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tencent.mmkv.MMKV
import com.zhili.zljx.network.RequestHandler
import okhttp3.OkHttpClient

//Application全局的ViewModel，里面存放了一些账户信息，基本配置信息等
val appViewModel: AppViewModel by lazy {MyApplication.appViewModelInstance }


class MyApplication : Application(), ViewModelStoreOwner {

    companion object {
        lateinit var appContext: Context
        lateinit var application: Application
        lateinit var appViewModelInstance: AppViewModel
        //初始化危险方法
        fun initDangerousSdk(){

            AMapLocationClient.updatePrivacyShow(appContext,true,true)
            AMapLocationClient.updatePrivacyAgree(appContext,true)

        }
        @JvmStatic
        fun initSmartRefreshLayout(){
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                //layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                MaterialHeader(context) //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout -> //指定为经典Footer，默认是 BallPulseFooter
                ClassicsFooter(context)
            }
            //设置默认 Refresh 初始化器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout -> //指定为经典Footer，默认是 BallPulseFooter
                ClassicsFooter(context)
            }
        }
    }


    private lateinit var mAppViewModelStore: ViewModelStore

    private var mFactory: ViewModelProvider.Factory? = null


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        application = this

        //初始化没有隐私相关的SDK
        initSafeSdk()

        mAppViewModelStore = ViewModelStore()
        appViewModelInstance = getAppViewModelProvider()[AppViewModel::class.java]


    }

    /**
     * 初始化隐私安全的SDK
     */
    private fun initSafeSdk() {
        //初始化MMKV
        MMKV.initialize(this)
        //初始化Toast
        Toaster.init(this)
        //初始化Logger
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false) // (Optional) Whether to show thread info or not. Default true
            .methodCount(0)
            .tag("查看数据") // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()

        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))

        //全局设置刷新框架
        initSmartRefreshLayout()

        //初始化DialogX
        DialogX.init(this)
        //设置为IOS主题
        DialogX.globalStyle = IOSStyle()
        //设置为MIUI主题
//        DialogX.globalStyle = MIUIStyle()
        //设置为MaterialYou主题
//        DialogX.globalStyle = MaterialYouStyle()
//        handleSSLHandshake()


        //初始化网络框架
        // 网络请求框架初始化  随便设置 只用来下载安装包 , 后期考虑弃用自己实现, 为了一个内更新引入一大堆
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .build()
        EasyConfig.with(okHttpClient)
            // 是否打印日志
            .setLogEnabled(false)
            // 设置服务器配置
            .setServer(RequestServer("https://sso.yiuxiu.com/"))
            // 设置请求处理策略
            .setHandler(RequestHandler(application))
            // 设置请求重试次数
            .setRetryCount(1)
            .setInterceptor { api: IRequestApi, params: HttpParams, headers: HttpHeaders ->
                // 添加全局请求头
            }
            .into()
    }


    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }
    /**
     * 获取一个全局的ViewModel
     */
    private fun getAppViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, getAppFactory())
    }

    private fun getAppFactory(): ViewModelProvider.Factory {
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return mFactory as ViewModelProvider.Factory
    }


//    fun handleSSLHandshake() {
//        try {
//            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
//                override fun getAcceptedIssuers(): Array<X509Certificate?> {
//                    return arrayOfNulls<X509Certificate>(0)
//                }
//
//                override fun checkClientTrusted(
//                    certs: Array<X509Certificate?>?,
//                    authType: String?
//                ) {
//                }
//
//                override fun checkServerTrusted(
//                    certs: Array<X509Certificate?>?,
//                    authType: String?
//                ) {
//                }
//            })
//            val sc = SSLContext.getInstance("TLS")
//            // trustAllCerts信任所有的证书
//            sc.init(null, trustAllCerts, SecureRandom())
//            HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
//            HttpsURLConnection.setDefaultHostnameVerifier { hostname, session -> true }
//        } catch (e: Exception) {
//        }
//    }

}