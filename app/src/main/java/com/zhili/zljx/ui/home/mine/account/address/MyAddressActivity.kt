package com.zhili.zljx.ui.home.mine.account.address

import android.os.Bundle
import com.github.gzuliyujiang.dialog.DialogConfig
import com.github.gzuliyujiang.dialog.DialogStyle
import com.github.gzuliyujiang.wheelpicker.AddressPicker
import com.github.gzuliyujiang.wheelpicker.annotation.AddressMode
import com.github.gzuliyujiang.wheelpicker.contract.OnAddressPickedListener
import com.github.gzuliyujiang.wheelpicker.entity.CityEntity
import com.github.gzuliyujiang.wheelpicker.entity.CountyEntity
import com.github.gzuliyujiang.wheelpicker.entity.ProvinceEntity
import com.klod.base.ext.parseState
import com.klod.base.ext.util.logE
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.bean.MyAddressBean
import com.zhili.zljx.databinding.ActivityEditAddressBinding
import com.zhili.zljx.ext.isPhoneNum


/**
 *@Auther KLOD
 *2023/5/3 14:18
 */
class MyAddressActivity : BaseMvvmActivity<MyAddressActivityVm, ActivityEditAddressBinding>() {


    private var cAddressBean = MyAddressBean(deliveryMode = "送货上门")

    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.apply {

            titleBar.titleTv.text = "收货地址"
            titleBar.ivBack.setOnClickListener {
                finish()
            }


            citySelectCl.setOnClickListener {
                //城市选择
                selectCity()

            }

            submitAddress.setOnClickListener {

                when {

                    nameEt.text.toString().isEmpty() -> {
                        showToast("请填写收货人姓名")
                    }

                    phoneEt.text.toString().isPhoneNum() -> {
                        showToast("请填写正确的手机号")
                    }

                    addressEt.text.toString().isEmpty() -> {
                        showToast("请填写收货地址")

                    }
                    cAddressBean.province.isEmpty()->{
                        showToast("请选择地区")
                    }
                    else -> {

                        cAddressBean.recipient = nameEt.text.toString()
                        cAddressBean.telephone = phoneEt.text.toString()
                        cAddressBean.subAddr = addressEt.text.toString()

                        mViewModel.postUserAddress(cAddressBean)


                    }

                }

            }


        }

        mViewModel.getUserAddress()


    }

    /**
     * 选择城市
     */
    private fun selectCity() {

        DialogConfig.setDialogStyle(DialogStyle.Three)
        val picker = AddressPicker(this)
        picker.setAddressMode(AddressMode.PROVINCE_CITY_COUNTY)

        picker.setDefaultValue("浙江省", "金华市", "婺城区")

        picker.setTitle("地址选择");
        //picker.setDefaultValue("贵州省", "毕节市", "纳雍县");
        picker.setOnAddressPickedListener { province, city, county ->


            ("城市选择: ${province.name} ${province.code} # " +
                    "${city.name} ${city.code} #${county.name} ${county.code}").logE()

            cAddressBean.address =  province.name+city.name+county.name
            cAddressBean.province = province.name
            cAddressBean.provinceCode = province.code
            cAddressBean.city = city.name
            cAddressBean.cityCode = city.code
            cAddressBean.county = county.name
            cAddressBean.countyCode = county.code

            mViewBind.cityTv.text = province.name+" "+city.name+" "+county.name

        }

        picker.show()

    }

    override fun createObserver() {


        mViewModel.addressState.observe(this) { resultState ->
            parseState(resultState, {
                "进来了3".logE()

                setAddressLayout(it)
            }, {
                "进来了4".logE()

                showToast(it.errorMsg)
            })
        }

        mViewModel.postAddressState.observe(this) { resultState ->

            parseState(resultState, {
                showToast("保存成功")
                finish()
            }, {
                showToast(it.errorMsg)
            })

        }

    }

    private fun setAddressLayout(it: MyAddressBean) {
        "进来了1".logE()
        mViewBind.apply {

            cAddressBean = it
            "进来了2".logE()

            nameEt.setText(it.recipient)
            phoneEt.setText(it.telephone)
            addressEt.setText(it.subAddr)

            cityTv.text = it.address


        }

    }
}