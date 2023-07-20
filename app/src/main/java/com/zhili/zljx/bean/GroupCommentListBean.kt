package com.zhili.zljx.bean

data class GroupCommentListBean(
    var PageIndex: Int = 0, // 1
    var PageSize: Int = 0, // 20
    var Rows: List<Row> = listOf(),
    var Total: Int = 0 // 9
) {
    data class Row(
        var Content: String = "", // 99
        var CreateTime: String = "", // 2022-12-15 16:23:32
        var HeadPortraitUrl: String = "",
        var IsLike: Int = 0, // 0
        var LikeSubsetNumber: Int = 0, // 1
        var NickName: String = "",
        var ReplySubset: MutableList<ReplySubsetData> = mutableListOf(),
        var ReplySubsetNumber: Int = 0, // 1
        var SubjectCommentID: String = "", // 16
        var UserNo: String="" // 362140191735897
    ) {
        data class ReplySubsetData(
            var Content: String = "", // 1
            var CreateTime: String = "", // 2022-12-16 10:48:03
            var HeadPortraitUrl: String = "",
            var IsLike: Int = 0, // 0
            var LikeSubsetNumber: Int = 0, // 0
            var NickName: String = "",
            var ReplySubset: MutableList<ReplySubsetData> = mutableListOf(),
            var ReplySubsetNumber: Int = 0, // 0
            var SubjectCommentID: Int = 0, // 19
            var UserNo:String="" // 362140190335059
        )
    }
}