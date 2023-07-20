package com.zhili.zljx.widget.dialog

import android.app.Activity
import android.content.Context

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater

import android.view.ViewGroup
import android.view.WindowManager

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter

import com.klod.base.dialog.BaseDialog
import com.klod.base.dialog.OnDialogClickListener
import com.klod.base.ext.parseState
import com.klod.base.ext.util.logE
import com.zhili.zljx.R

import com.zhili.zljx.bean.GroupCommentListBean
import com.zhili.zljx.databinding.DialogAllCommentReplyBinding

import com.zhili.zljx.databinding.ItemCommentBinding

import com.zhili.zljx.ext.goneView
import com.zhili.zljx.ext.loadAnyHead
import com.zhili.zljx.ext.parseColor
import com.zhili.zljx.ext.setAdapterChildClickListener

import com.zhili.zljx.ui.home.article.GroupDetailActivity
import com.zhili.zljx.ui.home.article.GroupDetailActivityVm


class AllCommentReplyDialog(
    context: Context,
    var activity: Activity,
    var subjectId: String,
    var index: Int,
    var mainData: GroupCommentListBean.Row,
    var mViewModel: GroupDetailActivityVm,
    private val listener: OnDialogClickListener?
) :
    BaseDialog<DialogAllCommentReplyBinding>(context) {




    override fun initView(savedInstanceState: Bundle?) {
        initDialog()


        mViewBind.apply {

            mainComment.replyLl.goneView()
            mainComment.replyBtn.goneView()
            mainComment.view1.goneView()

            mainComment.head.loadAnyHead(mainData.HeadPortraitUrl)
            mainComment.name.text = mainData.NickName.ifEmpty { "浙里金消用户" }
            mainComment.content.text = mainData.Content
            mainComment.replyTime.text = "回复于" + mainData.CreateTime
            mainComment.zanCount.text = mainData.LikeSubsetNumber.toString()
            replyCount.text = mainData.ReplySubsetNumber.toString() + "条回复"

            if (mainData.IsLike == 1) {
                mainComment.zanImg.imageTintList = ColorStateList.valueOf("#FFE95929".parseColor())
                mainComment.zanCount.setTextColor("#FFE95929".parseColor())
            } else {
                mainComment.zanImg.imageTintList = ColorStateList.valueOf("#FF999999".parseColor())
                mainComment.zanCount.setTextColor("#FF999999".parseColor())

            }
            replyRvDialog.apply {
                "设置了ADapter".logE()
                layoutManager = LinearLayoutManager(mContext)

                adapter = mAdapter
            }

        }

        mAdapter.setEmptyViewLayout(mContext,R.layout.empty_layout1)
//        mAdapter.setAdapterChildClickListener(R.id.zanBtn,
//            onclick = { adapter, view, position ->
//
//
//            })


        initClickListener()

        mViewModel.getAllCommentReplyList(subjectId, mainData.SubjectCommentID)

        mViewModel.commentLikeUiState.observe(activity as AppCompatActivity) {
            mViewBind.mainComment.zanBtn.isEnabled = true
            if (it.isSuccess) {
                if (it.data == 1) {
                    mViewBind.mainComment.zanImg.imageTintList =
                        ColorStateList.valueOf("#FFE95929".parseColor())
                    mViewBind.mainComment.zanCount.setTextColor("#FFE95929".parseColor())


                } else {
                    mViewBind.mainComment.zanImg.imageTintList =
                        ColorStateList.valueOf("#FF999999".parseColor())
                    mViewBind.mainComment.zanCount.setTextColor("#FF999999".parseColor())
//                    mViewBind.mainComment.zanCount.text = "${mainData.LikeSubsetNumber}"

                }
                mViewBind.mainComment.zanCount.text = "${mainData.LikeSubsetNumber}"

            }

        }

        mViewModel.commentReplyState.observe(activity as GroupDetailActivity) { resultState ->
            (activity as GroupDetailActivity).parseState(resultState, {

                mAdapter.submitList(it.Rows)


            }, {
                showToast(it.errorMsg)
            })
        }
    }


    override fun initClickListener() {


        mViewBind.apply {
            closeDialog.setOnClickListener { dismiss() }

            //点赞
            mainComment.zanBtn.setOnClickListener {
                mViewBind.mainComment.zanBtn.isEnabled = false

                mViewModel.postCommentLike(index, mainData.SubjectCommentID)

            }
            //保存
//            saveChange.setOnClickListener {
//                if(newNickName.text.toString().isEmpty()){
//                    showToast("请输入昵称")
//                }else{
//                    listener.onClick(1, newNickName.text.toString())
//                }
//            }

        }
    }

    private var mAdapter = CommentReplyAdapter()

    //Dialog初始化
    private fun initDialog() {

        window?.setGravity(Gravity.BOTTOM)
        window?.setWindowAnimations(R.style.picker_view_slide_anim)
        setCanceledOnTouchOutside(true)
        //设置dialog没有边距
        window?.decorView?.setPadding(0, 0, 0, 0)
        val lp = window?.attributes
        lp?.width = WindowManager.LayoutParams.MATCH_PARENT
        lp?.height = WindowManager.LayoutParams.MATCH_PARENT
        window?.attributes = lp

    }

    private class CommentReplyAdapter :
        BaseQuickAdapter<GroupCommentListBean.Row, CommentReplyAdapter.VH>() {
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
                    "ADapterfs zsdf asd ".logE()
//                    var replyCount = item.ReplySubsetNumber
                    replyLl.goneView()

                    replyBtn.goneView()
                    zanBtn.goneView()
//                    moreReply.text = "查看全部${replyCount}条回复"
                    replyTime.text = "回复于" + item.CreateTime
//                    zanCount.text = item.LikeSubsetNumber.toString()
//                    if (item.IsLike == 1) {
//                        zanImg.imageTintList = ColorStateList.valueOf("#FFE95929".parseColor())
//                        zanCount.setTextColor("#FFE95929".parseColor())
//                    } else {
//                        zanImg.imageTintList = ColorStateList.valueOf("#FF999999".parseColor())
//                        zanCount.setTextColor("#FF999999".parseColor())
//
//                    }
                }

            }


        }
    }
}