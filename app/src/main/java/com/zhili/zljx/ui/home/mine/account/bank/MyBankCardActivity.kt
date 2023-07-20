package com.zhili.zljx.ui.home.mine.account.bank

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.QuickAdapterHelper
import com.klod.base.dialog.OnDialogClickListener
import com.klod.base.ext.parseState
import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.bean.BanksDataBean
import com.zhili.zljx.bean.request.CommonRequestBean
import com.zhili.zljx.bean.request.MyBankCardBean
import com.zhili.zljx.bean.request.UnBindCardBean
import com.zhili.zljx.common.Contacts
import com.zhili.zljx.databinding.ActivityCommonListBinding
import com.zhili.zljx.ext.fromJson
import com.zhili.zljx.ext.setAdapterChildClickListener
import com.zhili.zljx.ext.setRefreshLayoutListener
import com.zhili.zljx.ext.startAC
import com.zhili.zljx.ui.home.mine.account.bank.adapter.AddBankCardAdapter
import com.zhili.zljx.ui.home.mine.account.bank.adapter.MyBankCardAdapter
import com.zhili.zljx.widget.dialog.UnbindBankCardDialog

/**
 *@Auther KLOD
 *2023/5/3 20:28
 */
class MyBankCardActivity : BaseMvvmActivity<MyBankCardActivityVm, ActivityCommonListBinding>() {


    private var unBindCardDialog: UnbindBankCardDialog? = null

    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.apply {

            titleBar.titleTv.text = "银行卡"
            titleBar.ivBack.setOnClickListener { finish() }

            commonRefreshLayout.setEnableLoadMore(false)
            commonRefreshLayout.setRefreshLayoutListener(
                onRefresh = {
                    mViewModel.getBankCardList()
                },
                onLoadMore = {

                }
            )

            //添加银行卡
            addCardAdapter.setOnItemClickListener { _, _, _ ->

                startAC<AddCardActivity> { }
            }
            //删除银行卡
            bankCardAdapter.setAdapterChildClickListener(R.id.unbindCard,
                onclick = { adapter, view, position ->
                    showUnbindDialog(adapter.items[position].id)
                })
            helper.addAfterAdapter(addCardAdapter)


            commonRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@MyBankCardActivity)


                adapter = helper.adapter
            }




        }


    }

    override fun onResume() {
        super.onResume()
        mViewBind.commonRefreshLayout.autoRefresh()

    }

    private fun showUnbindDialog(cardId: Int) {

        unBindCardDialog = UnbindBankCardDialog(this,cardId,object : OnDialogClickListener {
            override fun onClick(index: Int, data: Any) {
                when(index){
                    1->{
                        //解绑
                        mViewModel.postDeleteBankCard(data as UnBindCardBean)
                    }
                    2->{
//                        发送短信
                        mViewModel.sendSms(CommonRequestBean(phone = data.toString()))
                    }
                }
            }
        })
        unBindCardDialog?.show()

    }

    override fun createObserver() {

        mViewModel.cardListState.observe(this) { resultState ->
            mViewBind.commonRefreshLayout.finishRefresh()
            parseState(resultState, {
                setBankCardRv(it)
            }, {
                showToast(it.errorMsg)
            })
        }
        //发送短信监听
        mViewModel.sendSmsState.observe(this) { resultState ->
            parseState(resultState, {
                showToast("发送成功")
                unBindCardDialog?.startCountDown()
            }, {
                showToast(it.errorMsg)
            })
        }
        //删除银行卡监听
        mViewModel.deleteCardState.observe(this) { resultState ->
            parseState(resultState, {
                showToast("删除成功")
                unBindCardDialog?.dismiss()
                mViewBind.commonRefreshLayout.autoRefresh()
            }, {
                showToast(it.errorMsg)
            })
        }


    }

    private var bankCardAdapter = MyBankCardAdapter()
    private var addCardAdapter = AddBankCardAdapter()
    private var helper = QuickAdapterHelper.Builder(bankCardAdapter)
        .build()

    private fun setBankCardRv(it: MutableList<MyBankCardBean>) {

        var banksData = Contacts.banksDate.fromJson(BanksDataBean::class.java)
//        val map<String,Person> = list.map { it.name to it }.toMap()
        val map = banksData.banks.associateBy {
            it.BankName
        }



        bankCardAdapter.submitList(it.map {
            it.backgroundImg = map[it.bank]?.ImgUrl?:""
            it.icon = map[it.bank]?.ImgUrl?:""
            it
        })


    }
}