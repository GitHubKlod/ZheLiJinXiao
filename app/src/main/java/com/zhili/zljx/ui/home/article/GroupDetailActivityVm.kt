package com.zhili.zljx.ui.home.article

import androidx.lifecycle.MutableLiveData
import com.hjq.toast.Toaster
import com.klod.base.BaseViewModel
import com.klod.base.ext.requestNoCheck
import com.klod.base.state.ResultState
import com.zhili.zljx.bean.ActivityGroupDetailBean
import com.zhili.zljx.bean.ErrorBean
import com.zhili.zljx.bean.GroupCommentListBean
import com.zhili.zljx.network.apiServiceArticle
import com.zhili.zljx.network.stateCallback.ListUiState

/**
 *@Auther KLOD
 *2023/4/20 9:46
 */
class GroupDetailActivityVm :BaseViewModel() {



    var isLike = MutableLiveData(0)
    var isCollect = MutableLiveData(0)

    //评论 点赞
    var commentLikeUiState = MutableLiveData<ListUiState<Int>>()

    //详情
    var detailState = MutableLiveData<ResultState<ActivityGroupDetailBean>>()
    //评论数据
    var commentState = MutableLiveData<ResultState<GroupCommentListBean>>()

    //弹窗里的全部回复
    var commentReplyState = MutableLiveData<ResultState<GroupCommentListBean>>()
    //提交评论
    var postCommentState = MutableLiveData<ResultState<ErrorBean>>()




    /**
     * 获取活动圈详情
     */

    fun getGroupDetail(subjectId:String){


        var map = mutableMapOf<String,Any>().apply {
            put("SubjectID",subjectId)
        }

        requestNoCheck({ apiServiceArticle.getGroupDetail(map)},detailState)
    }

    /**
     * 获取活动圈评论列表
     */

    fun getArticleCommentList(subjectId:String,sort:Int,cPage:Int){

//        Sort=0&PageIndex=1&PageSize=20
        var map = mutableMapOf<String,Any>().apply {
            put("SubjectID",subjectId)
            put("Sort",sort)
            put("PageIndex",cPage)
            put("PageSize",pageSize)
        }

        requestNoCheck({ apiServiceArticle.getArticleCommentList(map)},commentState)
    }


    /**
     * 点赞
     */
    fun postLike(subjectId:String){
        var map = mutableMapOf<String,Any>().apply {
            put("SubjectID",subjectId)
        }
        requestNoCheck({ apiServiceArticle.postGroupLike(map)},{
            getGroupDetail(subjectId)
        },{
            Toaster.showShort(it.errorMsg)
        })

    }
    /**
     * 评论点赞
     */
    fun postCommentLike(index :Int,commentId:String){
        var map = mutableMapOf<String,Any>().apply {
            put("SubjectCommentID",commentId)
        }
        requestNoCheck({ apiServiceArticle.postGroupCommentLike(map)},{

            commentLikeUiState.value = ListUiState(isSuccess = true, data = it.IsLike, index = index )

        },{
            commentLikeUiState.value = ListUiState(isSuccess = false, errorMsg = it.errorMsg)
        })

    }
    /**
     * 收藏
     */
    fun postCollect(subjectId:String){
        var map = mutableMapOf<String,Any>().apply {
            put("SubjectID",subjectId)
        }
        requestNoCheck({ apiServiceArticle.postGroupCollect(map)},{
            getGroupDetail(subjectId)
        },{
            Toaster.showShort(it.errorMsg)
        })

    }

    /**
     * 发送评论
     */
    fun postComment(content:String,subjectId:String){

        var map = mutableMapOf<String,Any>().apply {
            put("Content",content)
            put("subjectId",subjectId)
        }

        requestNoCheck({ apiServiceArticle.postGroupComment(map)},postCommentState,true,"评论发送中")

    }
    /**
     * 回复评论
     */
    fun postCommentReply(content:String,subjectId:String,subjectCommentID:String){

        var map = mutableMapOf<String,Any>().apply {
            put("Content",content)
            put("subjectId",subjectId)
            put("SubjectCommentID",subjectCommentID)
        }

        requestNoCheck({ apiServiceArticle.postCommentReply(map)},postCommentState,true,"评论发送中")

    }


    /**
     * 全部回复列表
     */

    fun getAllCommentReplyList(subjectId:String,subjectCommentID:String){

        var map = mutableMapOf<String,Any>().apply {
            put("Sort",0)
            put("subjectId",subjectId)
            put("SubjectCommentID",subjectCommentID)
            put("PageIndex",1)
            put("PageSize",500)
        }

        requestNoCheck({ apiServiceArticle.getCommentReplyList(map)},commentReplyState)


    }

}

