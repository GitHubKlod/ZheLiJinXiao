package com.zhili.zljx.base

import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewbinding.ViewBinding


import com.hjq.toast.Toaster
import com.hwangjr.rxbus.RxBus
import com.kaopiz.kprogresshud.KProgressHUD
import com.klod.base.BaseViewModel
import com.klod.base.fragment.BaseVmVbFragment
import com.zhili.zljx.ui.home.search.GroupSearchActivityVm


/**
 * 作者　: KLOD
 * 时间　: 2023/4/21
 * 描述　: 你项目中的Fragment基类，在这里实现显示弹窗，吐司，还有自己的需求操作 ，如果不想用Databind，请继承
 * BaseVmFragment例如
 * abstract class BaseFragment<VM : BaseViewModel> : BaseVmFragment<VM>() {
 */
abstract class BaseMvvmFragment<VM : BaseViewModel, VB : ViewBinding> : BaseVmVbFragment<VM, VB>() {



    var cPage = 1
    var maxPage = 0


    abstract override fun initView(savedInstanceState: Bundle?)

    /**
     * 创建LiveData观察者 Fragment执行onViewCreated后触发
     */
    abstract override fun createObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RxBus.get().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        RxBus.get().unregister(this)

    }

    /**
     * 打开等待框
     */
    override fun showLoading(message: String) {
        showFragmentLoadingDialog(message)
    }

    /**
     * 关闭等待框
     */
    override fun dismissLoading() {
        dismissFragmentLoadingDialog()
    }

    fun showShortToast(msg: String) {
        Toaster.showShort(msg)
    }

    fun showToast(msg: String) {
        Toaster.show(msg)
    }

//    override fun onPause() {
//        super.onPause()
////        hideSoftKeyboard(mActivity)
//    }

    /**
     * 延迟加载 防止 切换动画还没执行完毕时数据就已经加载好了，这时页面会有渲染卡顿  bug
     * 这里传入你想要延迟的时间，延迟时间可以设置比转场动画时间长一点 单位： 毫秒
     * 不传默认 300毫秒
     * @return Long
     */
    override fun lazyLoadTime(): Long {
        return 0
    }

    private var hudFg: KProgressHUD? = null

    private fun showFragmentLoadingDialog(msg:String = "请求中..."){
        val imageView = ImageView(mContext)
//    imageView.setBackgroundResource(R.drawable.animation_loading_list1)
//    (imageView.background as AnimationDrawable).start()
        if(hudFg ==null){
            hudFg =  KProgressHUD.create(mContext)
                .setCustomView(imageView)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(null)
                .setDetailsLabel("\n"+msg)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show()
        }else{
            hudFg!!.setDetailsLabel("\n"+msg).show()
        }

    }

    private fun dismissFragmentLoadingDialog() {
        hudFg?.dismiss()
    }

    /**
     * 获取最大页数
     */
    fun getMaxPage(totalCount: Int, pageSize: Int=10):Int{
        var maxPage = totalCount / pageSize
        if (totalCount % pageSize != 0) {
            maxPage++
        }
        return  maxPage

    }
}