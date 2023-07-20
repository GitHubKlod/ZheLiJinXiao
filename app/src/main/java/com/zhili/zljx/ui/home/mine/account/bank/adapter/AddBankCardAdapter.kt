package com.zhili.zljx.ui.home.mine.account.bank.adapter

import android.content.Context
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseSingleItemAdapter
import com.chad.library.adapter.base.viewholder.QuickViewHolder
import com.zhili.zljx.R

/**
 *@Auther KLOD
 *2023/5/4 9:22
 */
class AddBankCardAdapter :BaseSingleItemAdapter<Any,QuickViewHolder>() {
    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): QuickViewHolder {
        return QuickViewHolder(R.layout.item_add_bank, parent)
    }

    override fun onBindViewHolder(holder: QuickViewHolder, item: Any?) {

    }
}