package com.klod.base.dialog

interface OnDialogClickListener {
    fun onClick(index:Int,data:Any)
}

interface OnAdapterClickListener {
    fun onClick(index:Int,data:Any,data1:Any)
}