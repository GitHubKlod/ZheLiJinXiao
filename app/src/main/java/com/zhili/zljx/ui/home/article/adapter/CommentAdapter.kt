package com.zhili.zljx.ui.home.article.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhili.zljx.bean.GroupCommentListBean
import com.zhili.zljx.databinding.ItemCommentBinding
import com.zhili.zljx.databinding.ItemCommentReplyBinding
import com.zhili.zljx.ext.goneView
import com.zhili.zljx.ext.loadAnyHead
import com.zhili.zljx.ext.parseColor
import com.zhili.zljx.ext.visibleView

/**
 *@Auther KLOD
 *2023/4/18 17:36
 */
class CommentAdapter:
    BaseQuickAdapter<GroupCommentListBean.Row, CommentAdapter.VH>() {
        // 自定义ViewHolder类
        class VH(
            parent: ViewGroup,
            val binding: ItemCommentBinding = ItemCommentBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
        ) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
            // 返回一个 ViewHolder
            return VH(parent)
        }


        override fun onBindViewHolder(holder: VH, position: Int, item: GroupCommentListBean.Row?) {
            // 设置item数据
            holder.binding.apply {
                item?.let { item ->

                    head.loadAnyHead(item.HeadPortraitUrl)
                    name.text = item.NickName.ifEmpty { "浙里金消用户" }
                    content.text = item.Content

                    var replyCount = item.ReplySubsetNumber
                    replyLl.goneView(replyCount==0)
                    replyRv.apply {
                        layoutManager = LinearLayoutManager(context)
                        //不需要判断
//                        adapter = CommentReplyAdapter(if(replyCount>3) item.ReplySubset.subList(0,3) else  item.ReplySubset)
                        adapter = CommentReplyAdapter(item.ReplySubset)

                    }
                    moreReply.visibleView(replyCount>2)
                    moreReply.text = "查看全部${replyCount}条回复"
                    replyTime.text = "回复于"+item.CreateTime
                    zanCount.text = item.LikeSubsetNumber.toString()
                    if(item.IsLike==1){
                        zanImg.imageTintList = ColorStateList.valueOf("#FFE95929".parseColor())
                        zanCount.setTextColor("#FFE95929".parseColor())
                    }else{
                        zanImg.imageTintList = ColorStateList.valueOf("#FF999999".parseColor())
                        zanCount.setTextColor("#FF999999".parseColor())

                    }
                }

            }


        }

    private class CommentReplyAdapter(items: List<GroupCommentListBean.Row.ReplySubsetData>) :
        BaseQuickAdapter<GroupCommentListBean.Row.ReplySubsetData, CommentReplyAdapter.VH>(items) {
        // 自定义ViewHolder类
        class VH(
            parent: ViewGroup,
            val binding: ItemCommentReplyBinding = ItemCommentReplyBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
        ) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
            // 返回一个 ViewHolder
            return VH(parent)
        }


        override fun onBindViewHolder(holder: VH, position: Int, item: GroupCommentListBean.Row.ReplySubsetData?) {
            // 设置item数据
            holder.binding.apply {
                item?.let { item ->

                    text.text = "${item.NickName.ifEmpty { "浙里金消用户" }}: ${item.Content}"

                }

            }


        }
    }

}