package com.zhili.zljx.ui.home.mine

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseMultiItemAdapter
import com.klod.base.util.GlideApp
import com.zhili.zljx.R
import com.zhili.zljx.bean.MyBottomGameBean
import com.zhili.zljx.databinding.ItemMyGameType1Binding
import com.zhili.zljx.databinding.ItemMyGameType2Binding
import com.zhili.zljx.ext.loadAny

/**
 *@Auther KLOD
 *2023/4/8 16:25
 */
class MineGameAdapter :
    BaseMultiItemAdapter<MyBottomGameBean>() {

    // 类型 1 的 viewholder
    class Game1(val viewBinding: ItemMyGameType1Binding) : RecyclerView.ViewHolder(viewBinding.root)

    // 类型 2 的 viewholder
    class Game2(val viewBinding: ItemMyGameType2Binding) : RecyclerView.ViewHolder(viewBinding.root)


    init {

        addItemType(Game1Type, object : OnMultiItemAdapterListener<MyBottomGameBean, Game1> {

            override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): Game1 {
                // 创建 viewholder
                val viewBinding =
                    ItemMyGameType1Binding.inflate(LayoutInflater.from(context), parent, false)
                return Game1(viewBinding)
            }

            override fun onBind(holder: Game1, position: Int, item: MyBottomGameBean?) {

                holder.viewBinding.apply {

                    gameImg.loadAny(item?.contentImg)

                }

            }

        })
        addItemType(Game2Type, object : OnMultiItemAdapterListener<MyBottomGameBean, Game2> {

            override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): Game2 {
                // 创建 viewholder
                val viewBinding =
                    ItemMyGameType2Binding.inflate(LayoutInflater.from(context), parent, false)
                return Game2(viewBinding)
            }

            override fun onBind(holder: Game2, position: Int, item: MyBottomGameBean?) {


                holder.viewBinding.apply {

                    item?.let {
                        GlideApp.with(context)
                            .load(item.iconUrl)
                            .error(R.mipmap.img_load_error)
                            .diskCacheStrategy(DiskCacheStrategy.NONE) // 跳过磁盘缓存
                            .into(gameIcon)
                        GlideApp.with(context)
                            .load(item.contentImg)
                            .error(R.mipmap.img_load_error)
                            .diskCacheStrategy(DiskCacheStrategy.NONE) // 跳过磁盘缓存
                            .into(gameImg)
                        gameName.text = item.title

                    }

                }

            }

        })
        onItemViewType { position, list -> // 根据数据，返回对应的 ItemViewType
            list[position].type
        }
    }

    companion object {
        const val Game1Type = 1
        const val Game2Type = 2
    }

}