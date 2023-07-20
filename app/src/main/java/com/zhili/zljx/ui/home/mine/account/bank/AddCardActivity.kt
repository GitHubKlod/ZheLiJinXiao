package com.zhili.zljx.ui.home.mine.account.bank

import android.os.Bundle
import com.github.gzuliyujiang.dialog.DialogConfig
import com.github.gzuliyujiang.dialog.DialogStyle
import com.github.gzuliyujiang.wheelpicker.AddressPicker
import com.github.gzuliyujiang.wheelpicker.OptionPicker
import com.github.gzuliyujiang.wheelpicker.annotation.AddressMode
import com.klod.base.ext.parseState
import com.klod.base.ext.util.logE
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.bean.request.BankCardJsonBean
import com.zhili.zljx.bean.BankMyInfoBean
import com.zhili.zljx.bean.BanksDataBean
import com.zhili.zljx.bean.request.CommonRequestBean
import com.zhili.zljx.databinding.ActivityAddBankCardBinding
import com.zhili.zljx.ext.isIDCardNum
import com.zhili.zljx.ext.isPhoneNum
import com.zhili.zljx.ext.loadAnyEmpty


/**
 *@Auther KLOD
 *2023/5/4 10:28
 */
class AddCardActivity :BaseMvvmActivity<AddCardActivityVm,ActivityAddBankCardBinding>(){


    var cardJsonBean = BankCardJsonBean()

    override fun initView(savedInstanceState: Bundle?) {

        //初始化银行卡数据
        mViewModel.getBanksData()


        mViewBind.apply {

            titleBar.titleTv.text = "添加银行卡"
            titleBar.ivBack.setOnClickListener { finish() }

            citySelectCl.setOnClickListener {
                selectCity()
            }

            bankSelectCl.setOnClickListener {
                selectBank()
            }
            getSmsCode.setOnClickListener{
                when{
                    phoneEt.text.toString().isEmpty()|| !phoneEt.text.toString().isPhoneNum()->{
                        showToast("请输入正确的手机号吗")
                    }
                    else->{
                        mViewModel.sendSms(CommonRequestBean(phone = phoneEt.text.toString()) )
                    }
                }
            }



            submitBankCard.setOnClickListener {

                when{

                    nameEt.text.toString().isEmpty()->{
                        showToast("请输入姓名")
                    }

                    idCardEt.text.toString().isEmpty()|| !idCardEt.text.toString().isIDCardNum()->{
                        showToast("请输入正确的身份证号码")
                    }

                    bankCardEt.text.toString().isEmpty()->{
                        showToast("请输入银行卡号")
                    }

                    phoneEt.text.toString().isEmpty()|| !phoneEt.text.toString().isPhoneNum()->{
                        showToast("请输入正确的手机号吗")
                    }

                    codeEt.text.toString().isEmpty()->{
                        showToast("请输入验证码")
                    }
                    cardJsonBean.bank.isEmpty()->{
                        showToast("请选择开户银行")
                    }
                    cardJsonBean.provinceCode.isEmpty()->{
                        showToast("请选择开户行省市")
                    }
                    else->{
                        cardJsonBean.cardID = idCardEt.text.toString().uppercase()
                        cardJsonBean.realName = nameEt.text.toString()
                        cardJsonBean.phone = phoneEt.text.toString()
                        cardJsonBean.code = codeEt.text.toString()
                        cardJsonBean.cardNo = bankCardEt.text.toString()

                        mViewModel.postCard(cardJsonBean)
                    }

                }

            }

        }

        mViewModel.getMyInfo()

    }

    override fun createObserver() {

        //我的信息
        mViewModel.myInfoState.observe(this){ resultState ->

            parseState(resultState,{
                setMyInfoLayout(it)
            },{
                showToast(it.errorMsg)
            })

        }

        mViewModel.submitCardState.observe(this){resultState ->
            parseState(resultState,{
                showToast("添加成功")
                finish()
            },{
                showToast(it.errorMsg)
            })

        }
        mViewModel.sendSmsState.observe(this){resultState ->
            parseState(resultState,{
                mViewBind.getSmsCode.startCountDown()

            },{
                showToast(it.errorMsg)
            })

        }




    }


    /**
     * 设置个人信息
     */
    private fun setMyInfoLayout(it: BankMyInfoBean) {

//        cardJsonBean.cardID = it.
        mViewBind.apply {
            nameEt.setText(it.RealName)
            phoneEt.setText(it.Phone)
            idCardEt.setText(it.CardID)
        }

    }

    /**
     * 选择城市
     */
    private fun selectCity() {

        DialogConfig.setDialogStyle(DialogStyle.Three)
        val picker = AddressPicker(this)
        picker.setAddressMode(AddressMode.PROVINCE_CITY)

        picker.setDefaultValue("浙江省", "金华市", "婺城区")

        picker.setTitle("地址选择");
        //picker.setDefaultValue("贵州省", "毕节市", "纳雍县");
        picker.setOnAddressPickedListener { province, city, county ->


            ("城市选择: ${province.name} ${province.code} # " +
                    "${city.name} ${city.code}").logE()


            cardJsonBean.province = province.name
            cardJsonBean.provinceCode = province.code
            cardJsonBean.city = city.name
            cardJsonBean.cityCode = city.code


            mViewBind.cityTv.text = province.name+" "+city.name//+" "+county.name

        }

        picker.show()

    }
    /**
     * 选择银行
     */
    private fun selectBank() {
        val bankDate = mViewModel.bansDate.value?.banks?: mutableListOf()
        val picker = OptionPicker(this)
        picker.setTitle("选择开户银行")
        picker.setBodyWidth(300)
        picker.setData(bankDate.map {
            it.BankName
        })
        picker.setDefaultPosition(0)
        picker.setOnOptionPickedListener { position, item ->

//            "银行选择 : ${(item as BanksDataBean.Bank).BankName}"

            (item.toString()).let {
               "银行选择 : $it"
                cardJsonBean.bank = it

                mViewBind.bankTv.text =  it
                mViewBind.bankTv.hint =  ""
                mViewBind.bankLittleIcon.loadAnyEmpty(bankDate[position].ImgUrl)

            }


        }
//OptionWheelLayout wheelLayout = picker.getWheelLayout();
//wheelLayout.setIndicatorEnabled(false);
//wheelLayout.setTextColor(0xFFFF00FF);
//wheelLayout.setSelectedTextColor(0xFFFF0000);
//wheelLayout.setTextSize(15 * view.getResources().getDisplayMetrics().scaledDensity);
//wheelLayout.setSelectedTextBold(true);
//wheelLayout.setCurtainEnabled(true);
//wheelLayout.setCurtainColor(0xEEFF0000);
//wheelLayout.setCurtainCorner(CurtainCorner.ALL);
//wheelLayout.setCurtainRadius(5 * view.getResources().getDisplayMetrics().density);
//wheelLayout.setOnOptionSelectedListener(new OnOptionSelectedListener() {
//    @Override
//    public void onOptionSelected(int position, Object item) {
//        picker.getTitleView().setText(picker.getWheelView().formatItem(position));
//    }
//});

        picker.show()

    }

}