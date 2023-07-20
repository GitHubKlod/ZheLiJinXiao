package com.zhili.zljx.ui.home.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseMultiItemAdapter
import com.zhili.zljx.bean.ArticleBean
import com.zhili.zljx.databinding.ItemFragmentActivityAdBinding
import com.zhili.zljx.databinding.ItemFragmentActivityImg3aBinding
import com.zhili.zljx.databinding.ItemFragmentActivityImg3bBinding
import com.zhili.zljx.databinding.ItemFragmentActivitySingleImgBinding
import com.zhili.zljx.databinding.ItemFragmentActivityTextBinding
import com.zhili.zljx.ext.invisibleView
import com.zhili.zljx.ext.loadAny
import com.zhili.zljx.ext.loadAnyEmpty


/**
 *@Auther KLOD
 *2023/4/19 10:40
 */
class ArticleAdapter:
    BaseMultiItemAdapter<ArticleBean.Row>() {

        // 类型 1 的 viewholder
        class ArticleType1(val viewBinding: ItemFragmentActivityAdBinding) : RecyclerView.ViewHolder(viewBinding.root)
        // 类型 2 的 viewholder
        class ArticleType2(val viewBinding: ItemFragmentActivityImg3aBinding) : RecyclerView.ViewHolder(viewBinding.root)
    // 类型 3 的 viewholder
        class ArticleType3(val viewBinding: ItemFragmentActivityImg3bBinding) : RecyclerView.ViewHolder(viewBinding.root)
    // 类型 4 的 viewholder
    class ArticleType4(val viewBinding: ItemFragmentActivitySingleImgBinding) : RecyclerView.ViewHolder(viewBinding.root)
    // 类型 5 的 viewholder
    class ArticleType5(val viewBinding: ItemFragmentActivityTextBinding) : RecyclerView.ViewHolder(viewBinding.root)


        init {

            addItemType(ArticleTypeAD,object :
                BaseMultiItemAdapter.OnMultiItemAdapterListener<ArticleBean.Row, ArticleType1> {

                override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): ArticleType1 {
                    // 创建 viewholder
                    val viewBinding = ItemFragmentActivityAdBinding.inflate(LayoutInflater.from(context), parent, false)
                    return ArticleType1(viewBinding)
                }
                override fun onBind(holder: ArticleType1, position: Int, item: ArticleBean.Row?) {
                    //一个图片广告

                    holder.viewBinding.apply {

                        adImg.loadAny(item?.imageUrl1)

                    }

                }

            })
            addItemType(ArticleTypeImg3a,object :
                BaseMultiItemAdapter.OnMultiItemAdapterListener<ArticleBean.Row, ArticleType2> {

                override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): ArticleType2 {
                    // 创建 viewholder
                    val viewBinding = ItemFragmentActivityImg3aBinding.inflate(LayoutInflater.from(context), parent, false)
                    return ArticleType2(viewBinding)
                }
                override fun onBind(holder: ArticleType2, position: Int, item: ArticleBean.Row?) {
                    //3个图片平铺的文章类型

                    holder.viewBinding.apply {
                        item?.let {
                            contentImg1.loadAnyEmpty(item.imageUrl1)
                            contentImg2.loadAnyEmpty(item.imageUrl2)
                            contentImg3.loadAnyEmpty(item.imageUrl3)
                            title.text = item.Title
                            upTime.text = item.CreateTime

                        }


                    }

                }

            })
            addItemType(ArticleTypeImg3b,object :
                BaseMultiItemAdapter.OnMultiItemAdapterListener<ArticleBean.Row, ArticleType3> {

                override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): ArticleType3 {
                    // 创建 viewholder
                    val viewBinding = ItemFragmentActivityImg3bBinding.inflate(LayoutInflater.from(context), parent, false)
                    return ArticleType3(viewBinding)
                }
                override fun onBind(holder: ArticleType3, position: Int, item: ArticleBean.Row?) {
                    //一个图片在左边大 两个图片在右边小 的文章类型

                    holder.viewBinding.apply {
                        item?.let {
                            contentImg1.loadAny(item.imageUrl1)
                            contentImg2.loadAny(item.imageUrl2)
                            contentImg3.loadAny(item.imageUrl3)

                            upTime.text = item.CreateTime
                            title.text = item.Title
                        }


                    }

                }

            })
            addItemType(ArticleTypeSingleImg,object :
                BaseMultiItemAdapter.OnMultiItemAdapterListener<ArticleBean.Row, ArticleType4> {

                override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): ArticleType4 {
                    // 创建 viewholder
                    val viewBinding = ItemFragmentActivitySingleImgBinding.inflate(LayoutInflater.from(context), parent, false)
                    return ArticleType4(viewBinding)
                }
                override fun onBind(holder: ArticleType4, position: Int, item: ArticleBean.Row?) {
                    //一个图片的文章类型
                    holder.viewBinding.apply {
                        item?.let {
                            contentImg1.loadAny(item.imageUrl1)
                            contentImg1.invisibleView(item.imageUrl1.isEmpty())
                            upTime.text = item.CreateTime
                            title.text = item.Title
                        }
                    }
                }
            })

              addItemType(ArticleTypeText,object :
                BaseMultiItemAdapter.OnMultiItemAdapterListener<ArticleBean.Row, ArticleType5> {

                override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): ArticleType5 {
                    // 创建 viewholder
                    val viewBinding = ItemFragmentActivityTextBinding.inflate(LayoutInflater.from(context), parent, false)
                    return ArticleType5(viewBinding)
                }
                override fun onBind(holder: ArticleType5, position: Int, item: ArticleBean.Row?) {
                    //一个图片的文章类型
                    holder.viewBinding.apply {
                        item?.let {
                            upTime.text = item.CreateTime
                            title.text = item.Title
                        }
                    }
                }
            })

            onItemViewType { position, list -> // 根据数据，返回对应的 ItemViewType
                list[position].itemType
            }
        }

        companion object {
            const val ArticleTypeAD = 1
            const val ArticleTypeImg3a = 2
            const val ArticleTypeImg3b = 3
            const val ArticleTypeSingleImg = 4
            const val ArticleTypeText = 5
        }

}