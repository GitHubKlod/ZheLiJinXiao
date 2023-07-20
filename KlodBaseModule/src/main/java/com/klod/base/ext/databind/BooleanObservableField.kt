package com.klod.base.ext.databind

import androidx.databinding.ObservableField

/**
 * 作者　: KLOD
 * 时间　: 2023/4/17
 * 描述　: 自定义的Boolean类型ObservableField 提供了默认值，避免取值的时候还要判空
 */
class BooleanObservableField(value: Boolean = false) : ObservableField<Boolean>(value) {
    override fun get(): Boolean {
        return super.get()!!
    }

}