package com.zhili.zljx.ui.home.article

import androidx.lifecycle.MutableLiveData
import com.klod.base.BaseViewModel
import com.klod.base.ext.requestNoCheck
import com.klod.base.state.ResultState
import com.zhili.zljx.bean.ArticleDetailBean
import com.zhili.zljx.network.apiServiceArticle

/**
 *@Auther KLOD
 *2023/4/19 19:13
 */
class ArticleDetailActivityVm : BaseViewModel(){

    var articleSate = MutableLiveData<ResultState<ArticleDetailBean>>()

    fun getArticleDetail(id:String){


        var map = mutableMapOf<String,Any>().apply {

            put("ArticleID",id)

        }

        requestNoCheck({ apiServiceArticle.getArticleDetail(map)},articleSate,true)

    }

}