package com.zhili.zljx.ui.home.mine.setting

import android.content.Intent
import android.os.Bundle
import com.blankj.utilcode.util.CacheDiskStaticUtils
import com.huantansheng.easyphotos.EasyPhotos
import com.huantansheng.easyphotos.callback.SelectCallback
import com.huantansheng.easyphotos.engine.EasyPhotosGlideEngine
import com.huantansheng.easyphotos.models.album.entity.Photo
import com.klod.base.dialog.OnDialogClickListener
import com.klod.base.ext.parseState
import com.klod.base.ext.util.logE
import com.zhili.zljx.app.ActivityManege
import com.zhili.zljx.app.appViewModel
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.bean.UserInfo
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.databinding.ActivitySettingBinding
import com.zhili.zljx.ext.loadAnyHead
import com.zhili.zljx.ext.safePhone
import com.zhili.zljx.ext.startAC
import com.zhili.zljx.ui.login.LoginActivity
import com.zhili.zljx.utils.MMKVUtils
import com.zhili.zljx.widget.dialog.ChangeNicknameDialog
import java.io.File


/**
 *@Auther KLOD
 *2023/4/4 11:18
 */
class SettingActivity : BaseMvvmActivity<SettingActivityVm, ActivitySettingBinding>() {

    private var changeNicknameDialog: ChangeNicknameDialog? = null
    private var nicknameS = ""

    override fun initView(savedInstanceState: Bundle?) {

        appViewModel.userInfo.value?.let {
            setView(it)
        }

        mViewBind.apply {

            titleBar.titleTv.text = "设置"
            titleBar.ivBack.setOnClickListener { finish() }

            //退出登录点击事件
            logoutBtn.setOnClickListener {
                //清除登录信息 //如果需要走接口 那就在观察里执行这些
                MMKVUtils.removeValueForKey(Contacts.UserInfo)
                MMKVUtils.removeValueForKey(Contacts.TokenInfo)
                MMKVUtils.putBoolean(Contacts.IsLogin,false)
                appViewModel.userInfo.value = null

                startAC<LoginActivity> {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                ActivityManege.finishActivityExcept(LoginActivity::class.java)

            }
            //清除缓存点击事件
            clearCache.setOnClickListener {
                val isClear = CacheDiskStaticUtils.clear()
                showToast(if (isClear) "清除成功" else "清除失败")
            }
            //更换昵称点击事件
            changeNickName.setOnClickListener {
                showChangeNicknameDialog(nickName.text.toString())
            }
            //推送设置
            pushSwitch.setOnClickListener {
                startAC<PushSwitchActivity> { }
            }
            //关于我们
            aboutUs.setOnClickListener {
                startAC<AboutUsActivity> { }
            }

            //更换手机号点击事件
            changePhone.setOnClickListener {
                startAC<ChangePhoneActivity> { }
            }
            //实名认证点击事件
            realName.setOnClickListener {
                var userInfo = appViewModel.userInfo.value
                if((userInfo?.IsRealName ?: 0) == 0){
                    startAC<RealNameActivity> { }
                }else{
                    showToast("已经实名认证")
                }

            }

            //更换头像点击事件
            userHead.setOnClickListener {


                openAlbum()

            }

        }

    }

    private fun openAlbum() {

        EasyPhotos.createAlbum(
            this,
            true,
            false,
            EasyPhotosGlideEngine.getInstance()
        ) //参数说明：上下文，是否显示相机按钮，是否使用宽高数据（false时宽高数据为0，扫描速度更快），[配置Glide为图片加载引擎](https://github.com/HuanTanSheng/EasyPhotos/wiki/12-%E9%85%8D%E7%BD%AEImageEngine%EF%BC%8C%E6%94%AF%E6%8C%81%E6%89%80%E6%9C%89%E5%9B%BE%E7%89%87%E5%8A%A0%E8%BD%BD%E5%BA%93)
            .setFileProviderAuthority(Contacts.FileProvider) //参数说明：见下方`FileProvider的配置`
            .start(object : SelectCallback() {
                override fun onResult(photos: ArrayList<Photo>, isOriginal: Boolean) {
                    if (photos.isNotEmpty()) {
                        ("路径  : " + photos[0].path).logE()

                        mViewModel.postFile(File(photos[0].path))

                    }
                }

                override fun onCancel() {}

            })

    }

    private fun showChangeNicknameDialog(text: String) {

        if (changeNicknameDialog == null) {
            changeNicknameDialog = ChangeNicknameDialog(this, text, object : OnDialogClickListener {
                override fun onClick(index: Int, data: Any) {

                    when (index) {
                        1 -> {
                            nicknameS = data.toString()
                            mViewModel.postChangeNickname(nicknameS)
                        }
                    }
                }

            })
        }
        changeNicknameDialog?.show()

    }

    override fun createObserver() {

        appViewModel.userInfo.observe(this) {
//            showToast("${it==null}")
            it?.let {
                setView(it)
            }
        }

        mViewModel.changeNicknameResultState.observe(this) {
            parseState(it, {
                val userInfo = appViewModel.userInfo.value
                userInfo?.NickName = nicknameS
                appViewModel.userInfo.value = userInfo

                changeNicknameDialog?.dismiss()
            }, { appException ->
                showToast(appException.errorMsg)
            })
        }

        mViewModel.uploadResultState.observe(this) {
            //后端返回是字符串 非json格式 所有用TokenOutInterceptor自己编写的Json来处理
            parseState(it, { errorBean ->

                val userInfo = appViewModel.userInfo.value
                userInfo?.HeadPortraitUrl = errorBean.errorMsg
                appViewModel.userInfo.value = userInfo

                mViewModel.postUserHeadImage(errorBean.errorMsg)

            }, { appException ->
                showToast(appException.errorMsg)
            })
        }

        mViewModel.headUrlResultState.observe(this) {
            parseState(it,
                { showToast("修改成功") },
                { appException -> showToast(appException.errorMsg) })
        }

    }

    private fun setView(userInfo: UserInfo) {

        mViewBind.apply {
//          it.toString().logE()

            MMKVUtils.setUser(userInfo)

            userNo.setValueTvText(userInfo.UserNo.toString())

            userHead.loadAnyHead(userInfo.HeadPortraitUrl)

            nickName.text = userInfo.NickName

            phone.text = userInfo.Mobile.safePhone()

            realName.setValueTvText(if (userInfo.IsRealName == 1) userInfo.RealName else "未认证")

        }
    }
}