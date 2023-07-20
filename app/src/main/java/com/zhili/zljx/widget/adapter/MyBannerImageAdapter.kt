package com.zhili.zljx.widget.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.RecyclerView
import com.klod.base.ext.util.logE
import com.klod.base.util.GlideApp
import com.youth.banner.adapter.BannerAdapter
import com.zhili.zljx.R

import com.zhili.zljx.bean.ActivityHomeConfigBean
import com.zhili.zljx.ext.loadAny
import com.zhili.zljx.ext.toDp


/**
 *@Auther KLOD
 *2023/4/18 16:23
 */
class MyBannerImageAdapter(datas: MutableList<ActivityHomeConfigBean.Data>?) :
    BannerAdapter<ActivityHomeConfigBean.Data, MyBannerImageAdapter.BannerViewHolder>(datas) {

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder{
        val imageView = ImageFilterView(parent.context)
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.round = (8).toDp().toFloat()
//        imageView.scaleType = ImageView.ScaleType.CENTER
        imageView.adjustViewBounds=true
        return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder, data: ActivityHomeConfigBean.Data, position: Int, size: Int) {
//        holder.imageView.loadAny(data.img)


//        "查看首页banner图 ${data.img}".logE()
        GlideApp.with( holder.imageView.context)
            .load(data.img.ifEmpty {R.mipmap.img_load_error})
            .error(R.mipmap.img_load_error)
            .into( holder.imageView)
    }

    class BannerViewHolder(var imageView: ImageFilterView) :
        RecyclerView.ViewHolder(imageView)

}