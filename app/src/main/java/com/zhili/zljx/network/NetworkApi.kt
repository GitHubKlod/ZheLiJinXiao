package com.zhili.zljx.network


import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import com.klod.base.network.BaseNetworkApi
import com.zhili.zljx.app.MyApplication
import com.zhili.zljx.network.api.ApiService
import com.zhili.zljx.network.api.ApiServiceArticle
import com.zhili.zljx.network.api.ApiServiceConfig
import com.zhili.zljx.network.api.ApiServiceECNY
import com.zhili.zljx.network.api.ApiServiceHb
import com.zhili.zljx.network.api.ApiServiceIM
import com.zhili.zljx.network.api.ApiServiceMarketPayment
import com.zhili.zljx.network.api.ApiServiceMarketPromotion
import com.zhili.zljx.network.api.ApiServiceTour
import com.zhili.zljx.network.interceptor.FaultTolerantInterceptor
import com.zhili.zljx.network.interceptor.MyHeadInterceptor
import com.zhili.zljx.network.interceptor.TokenOutInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


/**
 * 作者　: KLOD
 * 时间　: 2023/4/23
 * 描述　: 网络请求构建器，继承BasenetworkApi 并实现setHttpClientBuilder/setRetrofitBuilder方法，
 * 在这里可以添加拦截器，设置构造器可以对Builder做任意操作
 */


//双重校验锁式-单例 封装NetApiService 方便直接快速调用简单的接口
val apiServiceSSO: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.INSTANCE.getApi(ApiService::class.java, ApiService.SERVER_URL_SSO)
}


val apiServiceMarket: ApiServiceMarketPromotion by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.INSTANCE.getApi(ApiServiceMarketPromotion::class.java, ApiService.SERVER_URL_MARKET_PROMOTION)
}
//红包
val apiServiceHb: ApiServiceHb by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.INSTANCE.getApi(ApiServiceHb::class.java, ApiService.SERVER_URL_HB)
}
val apiServiceActivityConfig: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.INSTANCE.getApi(ApiService::class.java, "https://img.yiuxiu.com/")
}
val apiServiceMarketPayment: ApiServiceMarketPayment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.INSTANCE.getApi(ApiServiceMarketPayment::class.java, ApiService.SERVER_URL_MARKET_PAYMENT)
}
//数币
val apiServiceECNY: ApiServiceECNY by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.INSTANCE.getApi(ApiServiceECNY::class.java, ApiService.SERVER_URL_ECNY)
}
//文旅
val apiServiceTour: ApiServiceTour by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.INSTANCE.getApi(ApiServiceTour::class.java, ApiService.SERVER_URL_TOUR)
}
//文章资讯
val apiServiceArticle: ApiServiceArticle by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.INSTANCE.getApi(ApiServiceArticle::class.java, ApiService.SERVER_URL_CIRCLE)
}
//获取配置信息
val apiServiceConfig: ApiServiceConfig by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.INSTANCE.getApi(ApiServiceConfig::class.java, ApiService.SERVER_URL_CONFIG)
}
//IM聊天
val apiServiceIM: ApiServiceIM by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.INSTANCE.getApi(ApiServiceIM::class.java, ApiService.SERVER_URL_IM)
}

class NetworkApi : BaseNetworkApi() {

    companion object {
        val INSTANCE: NetworkApi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkApi()
        }
    }

    /**
     * 实现重写父类的setHttpClientBuilder方法，
     * 在这里可以添加拦截器，可以对 OkHttpClient.Builder 做任意操作
     */
    override fun setHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        builder.apply {
            //设置缓存配置 缓存最大10M
//            cache(Cache(File(appContext.cacheDir, "cxk_cache"), 10 * 1024 * 1024))
            //添加Cookies自动持久化
//            cookieJar(cookieJar)
            //添加接口容错机制
            addInterceptor(FaultTolerantInterceptor())
            //示例：添加公共heads 注意要设置在日志拦截器之前，不然Log中会不显示head信息
            addInterceptor(MyHeadInterceptor())
            //添加缓存拦截器 可传入缓存天数，不传默认7天
//            addInterceptor(CacheInterceptor())
            addInterceptor(TokenOutInterceptor())
            //添加日志拦截器
            addInterceptor(ChuckerInterceptor.Builder(MyApplication.appContext).build())
            // 日志拦截器
//            addInterceptor(LogInterceptor())
            //超时时间 连接、读、写
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)

            //信任所有证书
            sslSocketFactory(getSSLSocketFactory(),trustAllCert)
            // 信任手机所有CA证书
            hostnameVerifier { _, _ -> true }


        }
        return builder
    }

    /**
     * 实现重写父类的setRetrofitBuilder方法，
     * 在这里可以对Retrofit.Builder做任意操作，比如添加GSON解析器，protobuf等
     */
    override fun setRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder {
        return builder.apply {

            addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().setLenient().create()))

        }
    }

    //    val cookieJar: PersistentCookieJar by lazy {
//        PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(appContext))
//    }

    @Throws(Exception::class)
    private fun getSSLSocketFactory(): SSLSocketFactory {
        val trustAllCerts = arrayOf<TrustManager>(
            object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                    // 信任客户端证书
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                    // 信任服务端证书
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        )
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustAllCerts, SecureRandom())
        return sslContext.socketFactory
    }
    //定义一个信任所有证书的TrustManager
    private val trustAllCert = object : X509TrustManager {
        override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {
        }

        override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }

    }
}



