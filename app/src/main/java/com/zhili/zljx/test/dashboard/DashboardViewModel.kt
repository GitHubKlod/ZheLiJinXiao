package com.zhili.zljx.test.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel

class DashboardViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}