
package com.zhili.zljx.ext

import android.content.res.Resources
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.klod.base.util.GlideApp
import com.zhili.zljx.R

/**
 * 加载普通图片
 */
fun ImageView.loadAny(
    data: Any?,
    placeholder:Int = 0) {

    Glide.with(context)
        .load(data)
        .apply(RequestOptions().placeholder(placeholder))
        .error(R.mipmap.img_load_error)
//        .transition(withCrossFade(factory))
//        .skipMemoryCache(true) // 跳过内存缓存
//        .diskCacheStrategy(DiskCacheStrategy.NONE) // 跳过磁盘缓存
//         .placeholder(
//         )
//        .error(
//            Glide.with(context).load(R.mipmap.learn_default_icon).apply(builder)
//        )
//        .apply(builder)
        .into(this)
}
fun ImageView.loadAnyEmpty(
    data: Any?,
) {

    Glide.with(context)
        .load(data)
        .into(this)
}
fun ImageView.loadAnyHead(
    data: Any?,
    placeholder: Int = R.mipmap.ic_header_default
) {

    Glide.with(context)
        .load(data)
        .apply(RequestOptions().placeholder(placeholder))
        .apply(RequestOptions().error(R.mipmap.ic_header_default))
        .into(this)

}

/**
 * 加载圆形图片
 */
fun ImageView.loadAny2Circle(
    data: Any?,
) {

   // val builder =
     Glide.with(context)
        .load(data)
//        .skipMemoryCache(true) // 跳过内存缓存
//        .diskCacheStrategy(DiskCacheStrategy.NONE) // 跳过磁盘缓存
//         .placeholder(
//         )
//        .error(
//            Glide.with(context).load(R.mipmap.learn_default_icon).apply(builder)
//        )
        .apply(RequestOptions().circleCrop())
        .into(this)
}

/**
 * 加载圆角图片
 */
fun ImageView.loadAny2Rounded(
    data: Any?,
    roundingRadius :Int = 8
) {

   // val builder =
     Glide.with(context)
        .load(data)
//        .skipMemoryCache(true) // 跳过内存缓存
//        .diskCacheStrategy(DiskCacheStrategy.NONE) // 跳过磁盘缓存
//         .placeholder(
//         )
//        .error(
//            Glide.with(context).load(R.mipmap.learn_default_icon).apply(builder)
//        )
        .apply(RequestOptions().optionalTransform(RoundedCorners(roundingRadius.toDp())))
        .into(this)
}


fun Int.toDp()=(this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()





