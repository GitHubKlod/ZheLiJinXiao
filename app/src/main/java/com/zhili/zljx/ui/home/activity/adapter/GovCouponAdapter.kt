package com.zhili.zljx.ui.home.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseMultiItemAdapter
import com.zhili.zljx.bean.ActivityHomeConfigBean
import com.zhili.zljx.databinding.ItemActivityGovcouponImgBinding
import com.zhili.zljx.databinding.ItemActivityGovcouponLargeBinding
import com.zhili.zljx.databinding.ItemActivityGovcouponSmallBinding
import com.zhili.zljx.ext.loadAny


/**
 *@Auther KLOD
 *2023/4/19 10:40
 */
class GovCouponAdapter:
    BaseMultiItemAdapter<ActivityHomeConfigBean.Data>() {

        // 类型 1 的 viewholder
        class CouponType1(val viewBinding: ItemActivityGovcouponImgBinding) : RecyclerView.ViewHolder(viewBinding.root)
        // 类型 2 的 viewholder
        class CouponType2(val viewBinding: ItemActivityGovcouponLargeBinding) : RecyclerView.ViewHolder(viewBinding.root)
    // 类型 3 的 viewholder
        class CouponType3(val viewBinding: ItemActivityGovcouponSmallBinding) : RecyclerView.ViewHolder(viewBinding.root)


        init {

            addItemType(ArticleType1,object :
                BaseMultiItemAdapter.OnMultiItemAdapterListener<ActivityHomeConfigBean.Data, CouponType1> {

                override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): CouponType1 {
                    // 创建 viewholder
                    val viewBinding = ItemActivityGovcouponImgBinding.inflate(LayoutInflater.from(context), parent, false)
                    return CouponType1(viewBinding)
                }
                override fun onBind(holder: CouponType1, position: Int, item: ActivityHomeConfigBean.Data?) {

                    holder.viewBinding.apply {

                        couponImg.loadAny(item?.img)

                    }

                }

            })
            addItemType(ArticleType2,object :
                BaseMultiItemAdapter.OnMultiItemAdapterListener<ActivityHomeConfigBean.Data, CouponType2> {

                override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): CouponType2 {
                    // 创建 viewholder
                    val viewBinding = ItemActivityGovcouponLargeBinding.inflate(LayoutInflater.from(context), parent, false)
                    return CouponType2(viewBinding)
                }
                override fun onBind(holder: CouponType2, position: Int, item: ActivityHomeConfigBean.Data?) {

                    holder.viewBinding.apply {

                        item?.let {
                            couponImg.loadAny(item.img)
                            couponName.text = item.name.ifEmpty { if(item.textList.isEmpty()) "" else item.textList[0].text }
                            couponDesc.text = item.text.ifEmpty { if(item.textList.isNotEmpty()&&item.textList.size>=2) item.textList[1].text else  ""}
                            couponBtn.text = item.linkBtn.ifEmpty {"去看看"  }
                        }


                    }

                }

            })
            addItemType(CouponTypeSmall,object :
                BaseMultiItemAdapter.OnMultiItemAdapterListener<ActivityHomeConfigBean.Data, CouponType3> {

                override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): CouponType3 {
                    // 创建 viewholder
                    val viewBinding = ItemActivityGovcouponSmallBinding.inflate(LayoutInflater.from(context), parent, false)
                    return CouponType3(viewBinding)
                }
                override fun onBind(holder: CouponType3, position: Int, item: ActivityHomeConfigBean.Data?) {

                    holder.viewBinding.apply {
                        item?.let {
                            couponImg.loadAny(item.img)
                            couponName.text =
                                if(item.textList.isEmpty())
                                    ""
                                else
                                    item.textList[0].text

                            couponDesc.text =
                                if(item.textList.isNotEmpty()&&item.textList.size>=2)
                                    item.textList[1].text
                                else
                                    ""

                            couponBtn.text = item.linkBtn.ifEmpty {"去看看"  }
                        }


                    }

                }

            })
            
            onItemViewType { position, list -> // 根据数据，返回对应的 ItemViewType
                list[position].itemType
            }
        }

        companion object {
            const val ArticleType1 = 1
            const val ArticleType2 = 2
            const val CouponTypeSmall = 3
        }

}