package com.klod.base.ext.lifecycle

import androidx.lifecycle.*



/**
 * 作者　: KLOD
 * 时间　: 2020/1/7
 * 描述　:
 */
object KtxAppLifeObserver : LifecycleEventObserver {

    var isForeground   = MutableLiveData<Boolean>().apply { value = false }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_START->{
                isForeground.value = true
            }
            Lifecycle.Event.ON_STOP->{
                isForeground.value = false
            }
            else -> {

            }
        }
    }

}