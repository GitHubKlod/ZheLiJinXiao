package com.klod.base.ext.databind

import androidx.databinding.ObservableField

/**
 * 作者　: KLOD
 * 时间　: 2023/4/17
 * 描述　:自定义的 Byte 类型 ObservableField  提供了默认值，避免取值的时候还要判空
 */
class ByteObservableField(value: Byte = 0) : ObservableField<Byte>(value) {

    override fun get(): Byte {
        return super.get()!!
    }

}