package com.zhili.zljx.ui.home.article

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.klod.base.ext.parseState
import com.klod.base.ext.util.logE
import com.kongzue.dialogx.dialogs.CustomDialog
import com.kongzue.dialogx.interfaces.OnBindView
import com.zhili.zljx.R
import com.zhili.zljx.base.BaseMvvmActivity
import com.zhili.zljx.bean.ActivityGroupDetailBean
import com.zhili.zljx.bean.ActivityGroupDetailContentBean
import com.zhili.zljx.bean.GroupCommentListBean
import com.zhili.zljx.databinding.ActivityGroupDetailBinding
import com.zhili.zljx.ext.loadAny
import com.zhili.zljx.ext.loadAnyHead
import com.zhili.zljx.ext.parseColor
import com.zhili.zljx.ext.setAdapterChildClickListener
import com.zhili.zljx.ext.setRefreshLayoutListener
import com.zhili.zljx.ui.home.article.adapter.CommentAdapter
import com.zhili.zljx.widget.dialog.AllCommentReplyDialog
import kotlinx.coroutines.Runnable


/**
 *@Auther KLOD
 *2023/4/20 9:46
 */
class GroupDetailActivity : BaseMvvmActivity<GroupDetailActivityVm, ActivityGroupDetailBinding>() {

    private val subjectId by lazy {
        intent?.getStringExtra("subjectId") ?: ""
    }
//    private var commentListIndex = -1

    private var sort = 0

    override fun initView(savedInstanceState: Bundle?) {


        mViewBind.apply {

            titleBar.titleTv.text = "详情"
            titleBar.ivBack.setOnClickListener { finish() }

            //发送评论点赞
            sendComment.setOnClickListener {
                showSendCommentDialog()
            }
            //点赞
            zanBtn.setOnClickListener {
                mViewModel.postLike(subjectId)
            }
            //收藏
            collectBtn.setOnClickListener {
                mViewModel.postCollect(subjectId)
            }

            groupRefreshLayout.setEnableRefresh(false)
            groupRefreshLayout.setEnableAutoLoadMore(true)
            groupRefreshLayout.setRefreshLayoutListener(
                onRefresh = {
//                    cPage = 1
//                    mViewModel.
                },
                onLoadMore = {
                    if (cPage <= maxPage) {
                        mViewModel.getArticleCommentList(subjectId, sort, cPage)
                    } else {
                        showToast("没有更多评论")
                        it.finishLoadMore()
                    }
                }
            )

            groupRv.apply {

                layoutManager = LinearLayoutManager(this@GroupDetailActivity)
                //RvItem刷新动画
                itemAnimator?.changeDuration = 0
//                (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

                adapter = commentAdapter

            }
            commentAdapter.isEmptyViewEnable = true
            commentAdapter.setEmptyViewLayout(this@GroupDetailActivity, R.layout.empty_layout1)

            commentAdapter.setAdapterChildClickListener(R.id.replyBtn, R.id.zanBtn, R.id.moreReply,
                onclick = { adapter, view, position ->
                    when (view.id) {
                        R.id.replyBtn -> {
                            //点击回复
//                            commentListIndex = position
                            showSendCommentDialog(adapter.items[position].SubjectCommentID)

                        }

                        R.id.zanBtn -> {
                            //点赞
//                            commentListIndex = position
                            mViewBind.groupRv.isEnabled = false

                            mViewModel.postCommentLike(
                                position,
                                adapter.items[position].SubjectCommentID
                            )
                        }

                        R.id.moreReply -> {
                            //查看更多评论
                            showAllReplyDialog(position, adapter.items[position])
                        }
                    }

                })

        }


        mViewModel.getGroupDetail(subjectId)
        mViewModel.getArticleCommentList(subjectId, sort, cPage)

    }

    private var allReplyDialog: AllCommentReplyDialog? = null

    private fun showAllReplyDialog(position: Int, row: GroupCommentListBean.Row) {


        allReplyDialog = AllCommentReplyDialog(this, this, subjectId,position,row,mViewModel,null)

        allReplyDialog?.show()
    }


    private var sendDialog: CustomDialog? = null

    /**
     * 显示发送评论弹窗
     */
    private fun showSendCommentDialog(subjectCommentID: String = "0") {

        if (sendDialog == null) {
            sendDialog = CustomDialog.build()
                .setCustomView(object : OnBindView<CustomDialog>(R.layout.dialog_send_comment) {
                    override fun onBind(dialog: CustomDialog, v: View) {
                        val sendBtn = v.findViewById<TextView>(R.id.sendCommentBtn)
                        val commentContent = v.findViewById<EditText>(R.id.commentContent)
                        //延迟获取焦点
                        commentContent.postDelayed(
                            Runnable { showKeyboard(commentContent) },
                            300
                        )
                        sendBtn.setOnClickListener {
                            //点击发送评论
                            if (commentContent.text.toString().isEmpty()) {
                                showToast("请输入评论内容")
                            } else {
                                if (subjectCommentID == "0") {
                                    mViewModel.postComment(
                                        commentContent.text.toString(),
                                        subjectId
                                    )
                                } else {
                                    mViewModel.postCommentReply(
                                        commentContent.text.toString(),
                                        subjectId,
                                        subjectCommentID
                                    )
                                }
                                commentContent.setText("")
                            }
                        }
                    }
                })
                .setMaskColor("#88000000".parseColor())
                .setAlign(CustomDialog.ALIGN.BOTTOM)
        }

        sendDialog?.show()

    }

    override fun createObserver() {


        //详情
        mViewModel.detailState.observe(this) { resultState ->

            parseState(resultState, {
                setDetailLayout(it)
            }, {
                showToast(it.errorMsg)
            })

        }

        //评论
        mViewModel.commentState.observe(this) { resultState ->

            parseState(resultState, {

                setCommentRv(it)

            }, {
                mViewBind.groupRefreshLayout.finishRefresh()
                mViewBind.groupRefreshLayout.finishLoadMore()
                showToast(it.errorMsg)
            })

        }

        //是否收藏
        mViewModel.isCollect.observe(this) {
            if (it == 1) {
                mViewBind.collectImg.imageTintList =
                    ColorStateList.valueOf("#FFE95929".parseColor())
                mViewBind.collectCount.setTextColor("#FFE95929".parseColor())
            } else {
                mViewBind.collectImg.imageTintList =
                    ColorStateList.valueOf("#FF999999".parseColor())
                mViewBind.collectCount.setTextColor("#FF999999".parseColor())
            }
        }
        //是否点赞
        mViewModel.isLike.observe(this) {
            if (it == 1) {
                mViewBind.zanImg.imageTintList = ColorStateList.valueOf("#FFE95929".parseColor())
                mViewBind.zanCount.setTextColor("#FFE95929".parseColor())
            } else {
                mViewBind.zanImg.imageTintList = ColorStateList.valueOf("#FF999999".parseColor())
                mViewBind.zanCount.setTextColor("#FF999999".parseColor())
            }
        }

        //评论点赞
        mViewModel.commentLikeUiState.observe(this) {
            mViewBind.groupRv.isEnabled = true

            if (it.isSuccess) {
                commentAdapter.items[it.index].IsLike = it.data ?: 0
                if (it.data == 1) {
                    commentAdapter.items[it.index].LikeSubsetNumber++
                } else {
                    commentAdapter.items[it.index].LikeSubsetNumber--
                }
//                "查看评论 ${commentAdapter.items[it.index].SubjectCommentID} ${commentAdapter.items[it.index].IsLike}".logE()

                commentAdapter.notifyItemChanged(it.index)
            } else {
                showToast(it.errorMsg)
            }
        }
        //评论发送成功监听
        mViewModel.postCommentState.observe(this) { resultState ->

            parseState(resultState, {
                showToast(it.errorMsg)


                sendDialog?.dismiss()
                cPage = 1
                mViewModel.getArticleCommentList(subjectId, sort, cPage)
                mViewModel.getGroupDetail(subjectId)
            }, {
                sendDialog?.dismiss()
                showToast(it.errorMsg)
            })

        }

    }

    private var commentAdapter = CommentAdapter()

    /**
     * 设置评论详情
     */
    private fun setCommentRv(it: GroupCommentListBean) {
        if (cPage == 1) {
            mViewBind.groupRefreshLayout.finishRefresh()
            commentAdapter.submitList(it.Rows)
        } else {
            mViewBind.groupRefreshLayout.finishLoadMore()
            commentAdapter.addAll(it.Rows)
        }
        cPage++
        maxPage = getMaxPage(it.Total)

//        "查看当前页 = $cPage  $maxPage".logE()

    }

    /**
     * 设置详情
     */
    private fun setDetailLayout(it: ActivityGroupDetailBean) {

        mViewBind.apply {

            title.text = it.Title

            upHeader.loadAnyHead(it.HeadPortraitUrl)

            upName.text = it.NickName.ifEmpty { "浙里金消用户" }

            upTime.text = "创建于:${it.CreateTime}"


            var contentList: MutableList<ActivityGroupDetailContentBean> =
                Gson().fromJson(
                    it.Content,
                    object : TypeToken<MutableList<ActivityGroupDetailContentBean>>() {}.type
                )
//        =
            if (contentList.isNotEmpty()) {
                content.text = contentList[0].content
            }

            if (it.Media.isNotEmpty()) {
                contentImg.loadAny(it.Media[0].FileUrl)
            }

            contentData.text = "${it.CollectNumber}人关注了话题\n" +
                    "${it.BrowseNumber}浏览 · ${it.CommentNumber}回复 · ${it.LikeNumber}点赞"

            collectCount.text = it.CollectNumber.toString().ifEmpty { "0" }
            commentCount.text = it.CommentNumber.toString().ifEmpty { "0" }
            zanCount.text = it.LikeNumber.toString().ifEmpty { "0" }

            mViewModel.isCollect.value = it.IsCollect
            mViewModel.isLike.value = it.IsLike


        }

    }
}