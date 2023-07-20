package com.zhili.zljx.ui.home.mine.tour

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.klod.base.BaseViewModel
import com.klod.base.ext.request
import com.klod.base.state.ResultState
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.bean.TourLineDetailBean
import com.zhili.zljx.databinding.ActivityTourLineDetailBinding
import com.zhili.zljx.network.apiServiceTour
import com.zhili.zljx.ui.home.mine.tour.adapter.TagsAdapter

/**
 *@Auther KLOD
 *2023/5/3 9:56
 */
class TourLineDetailActivity :BaseMvvmActivity<TourLineDetailActivityVm,ActivityTourLineDetailBinding>() {
    override fun initView(savedInstanceState: Bundle?) {


        mViewBind.apply {

//            tagsRv.apply {
//                //设置布局管理器  流式布局 盒子布局
//                val flexBoxLayoutManager = FlexboxLayoutManager(context).apply {
//                    //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical和horizontal。
//                    flexDirection = FlexDirection.ROW //主轴为水平方向，起点在左端。
//                    //flexWrap 默认情况下 Flex 跟 LinearLayout 一样，都是不带换行排列的，但是flexWrap属性可以支持换行排列。
//                    flexWrap = FlexWrap.WRAP //按正常方向换行
//                    //justifyContent 属性定义了项目在主轴上的对齐方式。
//                    justifyContent = JustifyContent.FLEX_START //交叉轴的起点对齐。
//                }
//                layoutManager = flexBoxLayoutManager
//
//                adapter = TagsAdapter(item.LineTags)
//            }

            titleBar.titleTv.text = "旅游线路"
            titleBar.ivBack.setOnClickListener { finish() }


        }

    }

    override fun createObserver() {


    }


}