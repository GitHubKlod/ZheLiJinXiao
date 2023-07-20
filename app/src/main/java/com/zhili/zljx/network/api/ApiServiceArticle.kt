package com.zhili.zljx.network.api

import com.zhili.zljx.bean.*
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap


/**
 * 作者　: KLOD
 * 时间　: 2023/4/23
 * 描述　: 网络API 数币
 */
interface ApiServiceArticle {


    /**
     * 获取 首页底部资讯
     */
    @GET("OpenService/v1/Article/GetList")
    suspend fun getArticleList(@QueryMap map :Map<String,@JvmSuppressWildcards Any>) : ArticleBean


      /**
     * 获取 活动圈
     */
    @GET("OpenService/v1/Subject/GetList")
    suspend fun getActivityGroupList(@QueryMap map :Map<String,@JvmSuppressWildcards Any>) : ActivityGroupBean


    /**
     * 获取 文章详情
     */
    @GET("OpenService/v1/Article/GetArticleDetails")
    suspend fun getArticleDetail(@QueryMap map :Map<String,@JvmSuppressWildcards Any>) : ArticleDetailBean


    /**
     * 获取评论列表
     */
    @GET("OpenService/v1/Subject/GetCommentList")
    suspend fun getArticleCommentList(@QueryMap map :Map<String,@JvmSuppressWildcards Any>) : GroupCommentListBean

    /**
     * 获取活动圈 详情
     */
    @GET("OpenService/v1/Subject/GetDetails")
    suspend fun getGroupDetail(@QueryMap map :Map<String,@JvmSuppressWildcards Any>) : ActivityGroupDetailBean


    /**
     * 点赞 活动圈
     */
    @POST("OpenService/v1/Subject/SubjectLike")
    @FormUrlEncoded
    suspend fun  postGroupLike(@FieldMap map :Map<String,@JvmSuppressWildcards Any>) :SingleDataBean
  /**
     * 点赞 活动圈 评论
     */
    @POST("OpenService/v1/Subject/SubjectCommentLike")
    @FormUrlEncoded
    suspend fun  postGroupCommentLike(@FieldMap map :Map<String,@JvmSuppressWildcards Any>) :SingleDataBean

    /**
     * 收藏活动圈
     */
    @POST("OpenService/v1/Subject/SubjectCollect")
    @FormUrlEncoded
    suspend fun  postGroupCollect(@FieldMap map :Map<String,@JvmSuppressWildcards Any>) :SingleDataBean

  /**
     * 发表评论
     */
    @POST("OpenService/v1/Subject/SubjectComment")
    @FormUrlEncoded
    suspend fun  postGroupComment(@FieldMap map :Map<String,@JvmSuppressWildcards Any>) :ErrorBean


    /**
     * 回复评论
     */
    @POST("OpenService/v1/Subject/SubjectCommentReply")
    @FormUrlEncoded
    suspend fun  postCommentReply(@FieldMap map :Map<String,@JvmSuppressWildcards Any>) :ErrorBean

    /**
     * 获取 全部回复列表
     */
    @GET("OpenService/v1/Subject/GetCommentReplyList")
    suspend fun  getCommentReplyList(@QueryMap map :Map<String,@JvmSuppressWildcards Any>) :GroupCommentListBean

}

