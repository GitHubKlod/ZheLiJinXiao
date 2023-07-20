package com.zhili.zljx.common

import com.zhili.zljx.BuildConfig

object Contacts {

    const val FileProvider = "com.zhili.zljx.fileprovider"

    //用户信息
    const val UserInfo  = "UserInfo"
    const val LocationInfo  = "LocationInfo"
    const val TokenInfo  = "TokenInfo"

    const val IsLogin  = "IsLogin"
    // 是否同意过用户协议
    const val IS_AGREE_AGREEMENT = "is_agree_agreement"

    //城市选择历史
    const val CitySelectHistory = "CitySelectHistory"

    //推送设置
    const val goodsPushSwitch  = "goodsPushSwitch"
    const val articlePushSwitch  = "articlePushSwitch"
    const val jPushSwitch  = "jPushSwitch"
    const val cnxhSwitch  = "cnxhSwitch"
    const val dxtsSwitch  = "dxtsSwitch"

    //分享链接
    const val ShareUrl_App = "ShareUrl_App"

    //客服电话
    const val CallService = "tel:4006666536"
    const val CallServiceNumber = "400-6666-536"
    const val CallServiceDesc = "拨打电话失败,请手动拨打客服电话\n400-6666-536"

    //隐私政策
    const val privatePolicy = "https://mall.yiuxiu.com/help/zljx_private_policy.html"
    //用户协议
    const val userPolicy = "https://mall.yiuxiu.com/help/zljx_policy.html"


    /**
     * WebView连接
     */
    const val UserAgentAndroid = "ZLJX_ANDROID"
    const val HomeMessageUrl = BuildConfig.HOME_MESSAGE_URL
    const val HomeConsumeUrl = BuildConfig.HOME_CONSUME_URL
    const val HomeExhibitionUrl = BuildConfig.HOME_EXHIBITION_URL
    const val JxHbUrl = BuildConfig.HOME_JXHB_URL+"#/pages/activityList/activityList"
    const val JxHbWalletUrl = BuildConfig.HOME_JXHB_URL+"#/pages/jinXiaoWallet/jinXiaoWallet"
    const val JxHbRightsValueUrl = BuildConfig.HOME_JXHB_URL+"#/pages/rightsValue/rightsValue"
    const val JxHbRecordUrl = BuildConfig.HOME_JXHB_URL+"#/pages/moneyRecord/moneyRecord"
    const val JxHbExtractUrl = BuildConfig.HOME_JXHB_URL+"#/pages/moneyExtract/moneyExtract"
    const val JcHbUrl = BuildConfig.HOME_JXHB_URL+"#/pages/guess/guess"
    const val PkUrl = BuildConfig.HOME_JXHB_URL+"#/pages/PK/PK"
    const val EnergyUrl = BuildConfig.HOME_JXHB_URL+"#/pages/energyValue/energyValue"
    const val CarShowUrl = "https://carshow.yiuxiu.com/#/pages/favourable/applyRecord"
    const val ShopEnterUrl = "https://zljxsj.yiuxiu.com/#/subPages/shop-info/shop-info"
    const val CompanyCouponUrl = "https://companycoupon.yiuxiu.com/#/pages_my/orderList"
    const val FeedBackUrl = BuildConfig.HOME_URL+"#/pagesMy/feedback"
    const val PublishServiceUrl = BuildConfig.HOME_URL+"#/pagesServices/myPublishServiceList"
    const val SuperMarketOrderUrl = BuildConfig.HOME_SUPERMARKET_URL+"#/pages_order/orderList"
    const val TourLineUrl = "https://culturaltourism.yiuxiu.com/#/routeDetail?travelLineId="

    //APP下载链接
    const val ApkDownloadUrl = "https://yiuxiuapp.oss-cn-hangzhou.aliyuncs.com/apk_download/zhelijinxiao/"







    //银行数据

    const val banksDate = """
  {
  "banks": [
    {
      "ID": 1,
      "BankName": "中国农业银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301175933884007.png"
    },
    {
      "ID": 2,
      "BankName": "中国工商银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301175922415038.png"
    },
    {
      "ID": 3,
      "BankName": "中国建设银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301180027401066.png"
    },
    {
      "ID": 4,
      "BankName": "中国银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301180120121040.png"
    },
    {
      "ID": 7,
      "BankName": "中国邮政储蓄银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301180206403077.png"
    },
    {
      "ID": 8,
      "BankName": "上海浦东发展银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301180316467045.png"
    },
    {
      "ID": 12,
      "BankName": "招商银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301180355358010.png"
    },
    {
      "ID": 13,
      "BankName": "广发银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301180504578033.png"
    },
    {
      "ID": 14,
      "BankName": "中国民生银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301180643908079.png"
    },
    {
      "ID": 15,
      "BankName": "廊坊银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301180838410049.png"
    },
    {
      "ID": 16,
      "BankName": "平安银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301181024490085.png"
    },
    {
      "ID": 17,
      "BankName": "光大银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301181212805078.png"
    },
    {
      "ID": 18,
      "BankName": "恒丰银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301183238831021.png"
    },
    {
      "ID": 19,
      "BankName": "华夏银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301183417051030.png"
    },
    {
      "ID": 20,
      "BankName": "交通银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301183517849020.png"
    },
    {
      "ID": 21,
      "BankName": "兴业银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301183751665048.png"
    },
    {
      "ID": 22,
      "BankName": "中信银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301183856432088.png"
    },
    {
      "ID": 23,
      "BankName": "浙江省农村信用社联合社",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301184254874040.png"
    },
    {
      "ID": 24,
      "BankName": "安徽省农村信用社联合社",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301184855131012.png"
    },
    {
      "ID": 25,
      "BankName": "鞍山银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301185411246099.png"
    },
    {
      "ID": 26,
      "BankName": "包商银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301185522662065.png"
    },
    {
      "ID": 27,
      "BankName": "北京农商银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301185643593083.png"
    },
    {
      "ID": 28,
      "BankName": "北京顺义银座村镇银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304162222960052.png"
    },
    {
      "ID": 29,
      "BankName": "北京银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301190038473047.png"
    },
    {
      "ID": 30,
      "BankName": "渤海银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301190115692062.png"
    },
    {
      "ID": 31,
      "BankName": "沧州银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301190506447026.png"
    },
    {
      "ID": 32,
      "BankName": "常熟农商银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301190935702028.png"
    },
    {
      "ID": 33,
      "BankName": "成都农商银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301191025797065.png"
    },
    {
      "ID": 34,
      "BankName": "成都银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301191136971070.png"
    },
    {
      "ID": 35,
      "BankName": "承德银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301191441491001.png"
    },
    {
      "ID": 36,
      "BankName": "稠州商业银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301191608868092.png"
    },
    {
      "ID": 37,
      "BankName": "大连银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301191528742061.png"
    },
    {
      "ID": 38,
      "BankName": "德阳银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190305081921107042.png"
    },
    {
      "ID": 39,
      "BankName": "东莞农村商业银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301191854840033.png"
    },
    {
      "ID": 40,
      "BankName": "东莞银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301192040498070.png"
    },
    {
      "ID": 41,
      "BankName": "东亚银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301191948950076.png"
    },
    {
      "ID": 42,
      "BankName": "东营莱商村镇银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301192316188005.png"
    },
    {
      "ID": 43,
      "BankName": "鄂尔多斯银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301192403533076.png"
    },
    {
      "ID": 44,
      "BankName": "福建海峡银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301192503347006.png"
    },
    {
      "ID": 45,
      "BankName": "福建农村信用社",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301192921008008.png"
    },
    {
      "ID": 46,
      "BankName": "阜新银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301193241606046.png"
    },
    {
      "ID": 47,
      "BankName": "富滇银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301193325481087.png"
    },
    {
      "ID": 48,
      "BankName": "广东华兴银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301193555375072.png"
    },
    {
      "ID": 49,
      "BankName": "广东南粤银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301193810502044.png"
    },
    {
      "ID": 50,
      "BankName": "广西北部湾银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301194025161015.png"
    },
    {
      "ID": 51,
      "BankName": "广西农村信用社",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301194253508081.png"
    },
    {
      "ID": 52,
      "BankName": "广州农村商业银行股份有限公司",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301194341243026.png"
    },
    {
      "ID": 53,
      "BankName": "广州银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301194515464065.png"
    },
    {
      "ID": 54,
      "BankName": "贵阳银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301194633496002.png"
    },
    {
      "ID": 55,
      "BankName": "桂林银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301194714372015.png"
    },
    {
      "ID": 56,
      "BankName": "哈尔滨银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301194751029017.png"
    },
    {
      "ID": 57,
      "BankName": "海口联合农商银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301195104612074.png"
    },
    {
      "ID": 58,
      "BankName": "海南省农村信用社",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301195159270042.png"
    },
    {
      "ID": 59,
      "BankName": "邯郸银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301195300693083.png"
    },
    {
      "ID": 60,
      "BankName": "韩亚银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301195402335013.png"
    },
    {
      "ID": 61,
      "BankName": "杭州银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301195459586098.png"
    },
    {
      "ID": 62,
      "BankName": "河北银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190301195540056001.png"
    },
    {
      "ID": 63,
      "BankName": "衡水银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304162113162080.png"
    },
    {
      "ID": 64,
      "BankName": "葫芦岛银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304162829811011.png"
    },
    {
      "ID": 65,
      "BankName": "湖北省农村信用社",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302084613049004.png"
    },
    {
      "ID": 66,
      "BankName": "湖北银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302084741332027.png"
    },
    {
      "ID": 67,
      "BankName": "湖州银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302084852349031.png"
    },
    {
      "ID": 68,
      "BankName": "黄河农村商业银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302085142431088.png"
    },
    {
      "ID": 69,
      "BankName": "徽商银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302085348745047.png"
    },
    {
      "ID": 70,
      "BankName": "吉林省农村信用社",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302085709593046.png"
    },
    {
      "ID": 71,
      "BankName": "吉林银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302085757297071.png"
    },
    {
      "ID": 72,
      "BankName": "嘉兴银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302085905127078.png"
    },
    {
      "ID": 73,
      "BankName": "江苏省农村信用社联合社",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304165042995055.png"
    },
    {
      "ID": 74,
      "BankName": "江苏银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302090228912013.png"
    },
    {
      "ID": 75,
      "BankName": "江苏长江商业银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302090400257055.png"
    },
    {
      "ID": 76,
      "BankName": "江西赣州银座村镇银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304162926578058.png"
    },
    {
      "ID": 77,
      "BankName": "江西省农村信用社",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304162942094053.png"
    },
    {
      "ID": 78,
      "BankName": "江阴农商银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304164347487083.png"
    },
    {
      "ID": 79,
      "BankName": "锦州银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302090817122019.png"
    },
    {
      "ID": 80,
      "BankName": "晋商银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302090912404091.png"
    },
    {
      "ID": 81,
      "BankName": "昆仑银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302091128892060.png"
    },
    {
      "ID": 82,
      "BankName": "昆山农村商业银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302091237550024.png"
    },
    {
      "ID": 83,
      "BankName": "柳州银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302091349270006.png"
    },
    {
      "ID": 84,
      "BankName": "龙江银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302091655852013.png"
    },
    {
      "ID": 85,
      "BankName": "绵阳银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302092300187030.png"
    },
    {
      "ID": 86,
      "BankName": "南充市商业银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302092427251021.png"
    },
    {
      "ID": 87,
      "BankName": "南京银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302092513299033.png"
    },
    {
      "ID": 88,
      "BankName": "内蒙古银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302092613394044.png"
    },
    {
      "ID": 89,
      "BankName": "宁波通商银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304164614521055.png"
    },
    {
      "ID": 90,
      "BankName": "宁波银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302092817646078.png"
    },
    {
      "ID": 91,
      "BankName": "宁夏银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302092907944021.png"
    },
    {
      "ID": 92,
      "BankName": "攀枝花商业银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302093034571030.png"
    },
    {
      "ID": 93,
      "BankName": "企业银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304163605055088.png"
    },
    {
      "ID": 94,
      "BankName": "青海银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302093508592019.png"
    },
    {
      "ID": 95,
      "BankName": "山东省农村信用社",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302093726235034.png"
    },
    {
      "ID": 96,
      "BankName": "上海农村商业银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302093914159000.png"
    },
    {
      "ID": 97,
      "BankName": "上海银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302093955238071.png"
    },
    {
      "ID": 98,
      "BankName": "绍兴银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302094032551044.png"
    },
    {
      "ID": 99,
      "BankName": "深圳福田银座村镇银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304163713276021.png"
    },
    {
      "ID": 100,
      "BankName": "深圳农村商业银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302094137677039.png"
    },
    {
      "ID": 101,
      "BankName": "深圳前海微众银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190302094351899079.png"
    },
    {
      "ID": 102,
      "BankName": "顺德农商银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304153437075043.png"
    },
    {
      "ID": 103,
      "BankName": "四川省农村信用社",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304153524795004.png"
    },
    {
      "ID": 104,
      "BankName": "苏州银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304153618218052.png"
    },
    {
      "ID": 105,
      "BankName": "台州银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304153711953005.png"
    },
    {
      "ID": 106,
      "BankName": "太仓农村商业银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304154054504023.png"
    },
    {
      "ID": 107,
      "BankName": "泰隆银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304154157271026.png"
    },
    {
      "ID": 108,
      "BankName": "天津农商银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304154255929016.png"
    },
    {
      "ID": 109,
      "BankName": "天津银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304154328742098.png"
    },
    {
      "ID": 110,
      "BankName": "温州银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304154356977013.png"
    },
    {
      "ID": 111,
      "BankName": "乌鲁木齐银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304154747028051.png"
    },
    {
      "ID": 112,
      "BankName": "武汉农村商业银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304154942718085.png"
    },
    {
      "ID": 113,
      "BankName": "西安银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304155132204006.png"
    },
    {
      "ID": 114,
      "BankName": "新韩银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304155320175080.png"
    },
    {
      "ID": 115,
      "BankName": "邢台银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304170202900082.png"
    },
    {
      "ID": 116,
      "BankName": "鄞州银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304155541881004.png"
    },
    {
      "ID": 117,
      "BankName": "友利银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304165304779054.png"
    },
    {
      "ID": 118,
      "BankName": "云南省农村信用社",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304155658742012.png"
    },
    {
      "ID": 119,
      "BankName": "张家港农村商业银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304155911822096.png"
    },
    {
      "ID": 120,
      "BankName": "张家口银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304160038543064.png"
    },
    {
      "ID": 121,
      "BankName": "长安银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304160213452046.png"
    },
    {
      "ID": 122,
      "BankName": "长沙银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304165722004025.png"
    },
    {
      "ID": 123,
      "BankName": "浙江景宁银座村镇银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304165428906057.png"
    },
    {
      "ID": 124,
      "BankName": "浙江民泰银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304160538238024.png"
    },
    {
      "ID": 125,
      "BankName": "浙江三门银座村镇银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304165403874074.png"
    },
    {
      "ID": 126,
      "BankName": "浙商银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304160730865039.png"
    },
    {
      "ID": 127,
      "BankName": "重庆农村商业银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304160803225055.png"
    },
    {
      "ID": 128,
      "BankName": "重庆黔江银座村镇银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304161759049034.png"
    },
    {
      "ID": 129,
      "BankName": "重庆银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304160902617091.png"
    },
    {
      "ID": 130,
      "BankName": "重庆渝北银座村镇银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304161618250044.png"
    },
    {
      "ID": 131,
      "BankName": "珠海华润银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304161354888055.png"
    },
    {
      "ID": 132,
      "BankName": "自贡市商业银行",
      "ImgUrl": "https://yiuxiuimg.oss-cn-hangzhou.aliyuncs.com/images/Upload/act/2019/3/20190304154442712026.png"
    },
    {
      "ID": 133,
      "BankName": "金华银行",
      "ImgUrl": "https://yiuxiu123.oss-cn-hangzhou.aliyuncs.com/act/202005/c0941574a2b6433e94832dbe3b9b7f68.png"
    }
  ]
}
        
    """

}