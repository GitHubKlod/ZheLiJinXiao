package com.zhili.zljx.base

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.KeyboardUtils

import com.gyf.immersionbar.ImmersionBar
import com.hjq.toast.Toaster

import com.hwangjr.rxbus.RxBus
import com.kaopiz.kprogresshud.KProgressHUD
import com.klod.base.BaseViewModel
import com.klod.base.activity.BaseVmVbActivity
import com.zhili.zljx.app.ActivityManege
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.widget.dialog.UpdateDialog


abstract class BaseMvvmActivity<VM : BaseViewModel, VB : ViewBinding> : BaseVmVbActivity<VM, VB>() {

    var cPage = 1
    var maxPage = 0

    /**
     * 当前Activityc创建后调用的方法 abstract修饰供子类实现
     */
    abstract override fun initView(savedInstanceState: Bundle?)

    /**
     * 创建liveData数据观察
     */
    abstract override fun createObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityManege.addActivity(this)
        ImmersionBar.with(this).transparentStatusBar()
            .statusBarDarkFont(true).init()
        RxBus.get().register(this)

//        mViewBind.root

    }


    override fun onDestroy() {
        super.onDestroy()
        ActivityManege.removeActivity(this)

        RxBus.get().unregister(this)
    }

    //显示加载框
    override fun showLoading(message: String) {

        showLoadingDialog(message)

    }
    //隐藏加载框
    override fun dismissLoading() {

        dismissLoadingDialog()

    }

    fun adapterStatusBar(adapterView: View){
        ImmersionBar.with(this).transparentStatusBar().titleBarMarginTop(adapterView)
            .statusBarDarkFont(true).init()

    }

    fun showLongToast(msg: String) {
        Toaster.show(msg)
    }

    fun showToast(msg: String) {
        Toaster.showShort(msg)
    }

    private var hudAc: KProgressHUD? = null

    private fun showLoadingDialog(msg:String = "请求中..."){
        val imageView = ImageView(this)
//    imageView.setBackgroundResource(R.drawable.animation_loading_list1)
//    (imageView.background as AnimationDrawable).start()
        if(hudAc ==null){
            hudAc =  KProgressHUD.create(this)
                .setCustomView(imageView)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(null)
                .setDetailsLabel("\n"+msg)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show()
        }else{
            hudAc!!.setDetailsLabel("\n"+msg).show()
        }

    }

    private fun dismissLoadingDialog() {
        hudAc?.dismiss()
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

    /**
     * 弹出软键盘
     */
    fun showKeyboard(view :View) {
        //其中editText为dialog中的输入框的 EditText
//        if(editText!=null){
//            //设置可获得焦点
//            editText.setFocusable(true);
//            editText.setFocusableInTouchMode(true);
//            //请求获得焦点
//            editText.requestFocus();
//            //调用系统输入法
//            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            inputManager.showSoftInput(editText, 0);
//        }

        KeyboardUtils.showSoftInput(view)

    }

    /**
     * 关闭软键盘
     */
    fun dismissKeyboard(view:View){
        KeyboardUtils.hideSoftInput(view)

    }

    fun showUpdateAppDialog(versionName:String,forceUpdate:Boolean,updateLog:String){

        // 升级对话框
        UpdateDialog.Builder(this)
            // 版本名
            .setVersionName("V${versionName}")
            // 是否强制更新
            .setForceUpdate(forceUpdate)
            // 更新日志
            .setUpdateLog(updateLog)
            // 下载 URL
            .setDownloadUrl(Contacts.ApkDownloadUrl+"v${versionName}.apk")
            // 文件 MD5
//            .setFileMd5("df2f045dfa854d8461d9cefe08b813c8")
            .show()


    }

}