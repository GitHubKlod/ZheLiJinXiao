package com.zhili.zljx.bean

data class MyCouponListBean(
    var PageIndex: Int = 0, // 1
    var PageSize: Int = 0, // 20
    var Rows: MutableList<Row> = mutableListOf(),
    var Total: Int = 0 // 8
) {
    data class Row(
        var Amount: Double = 0.0, // 2500
        var CouponNo: String = "", // ZX021714042347437294
        var CouponStatus: Int = 0, // 4
        var CouponType: Int = 0, // 1
        var CoverImage: String = "", // https://yiuxiu123.oss-cn-hangzhou.aliyuncs.com/YiuXiuMarketPayment/MarketPayment/202302/994f7faf3d7f4e9da2464b10cbaed53b.png
        var ExpiryBeginTime: String = "", // 2023-02-20 10:00:00
        var ExpiryEndTime: String = "", // 2023-03-01 23:59:59
        var Explain: String = "", // <p>使用规则</p><p>1.每轮消费券以10天为有效期，消费者在领取后需在有效期内使用，有效期内未使用的消费券将由发券平台统一收回，滚入下一轮发放，直至活动结束；</p><p>2.每张消费券只限一次使用，单次消费仅限使用一张，不找零；</p><p>3.每位消费者每轮每次只能中签1个券包；</p><p>4.消费券使用时，可与商家自有的电子优惠券叠加使用。</p><p>可使用商家：</p><p>浙江仙华之旅国际旅行社</p><p>浙江指南针旅行社有限公司浦江分公司</p><p>达众旅行社</p><p>浦江龙峰国际旅行社</p><p>江南第一家</p><p>浦江趣野吧探索营地</p><p>浦江鱼泡泡营地</p><p>诗人小镇探险乐园</p><p>田后蓬人家</p><p>浦江县山水人家</p><p>浦江新华书店</p><p>数极影城</p><p>青致吾庐民宿</p><p>花果山民宿</p><p>嵩溪山房民宿</p><p>双源漫居民宿</p><p>ANNY生活馆</p><p>浦江泊隐柳秀轻奢度假酒店</p><p>浦江塔山宾馆</p><p>浦江国际大酒店</p><p>浦江檀宫国际度假酒店</p><p>浦江县顺风饭店有限公司</p><p>浦江天慧商务酒店有限公司</p><p>浦江江南一埠酒店管理有限公司（开元曼居）</p><p>浙江罗家源君澜酒店有限公司</p><p>浦江大厦宾馆</p><p>浦江县新浦江饭店</p><p>浦江盛宴大酒店</p><p>映象丰安大酒店</p><p>浦江县老钵头饭店</p><p>浦江天悦旅行社有限公司</p><p>浦江县文旅集团疗休养有限公司</p><p>浦江老地方影城</p><p>浦江县印象田园饭店</p><p>浦江嘉里影视传媒有限公司</p><p>浦江数极影视传媒有限公司</p><p>浦江老地方影视传播有限公司</p><p>浦江诗画农庄</p><p>龙源渔宿</p><p>浦江县旅游发展有限公司</p><p>浦江县天逸民宿</p><p>江南书堂</p><p>金华市石柒凯露营地服务有限公司</p><p>浦江丰安旅行社有限公司</p><p>浙江莳光国际旅行社有限公司</p><p>浦江县锦翠饭店有限公司</p><p>浦江县青云亭书店</p><p>万达影城（海乐城店）</p><p>横店影城（浦江店）</p><p>浦江县登云山居民宿有限公司</p><p>老钵头风味土菜（浙江省金华市浦江县平七路与前方大道交叉口(顺丰南侧60米)）</p><p>OUR我们的咖餐厅（浦江县新城盛昱万苑路152号）</p><p>玩家先锋家庭娱乐中心（浦江县S210浦江县海乐城3楼）</p><p>浦江县酱小七火锅店（浦江县和平南路123号）</p><p>浦江县齿香小吃店（隔壁妈妈文创园店）：浦江县人民西路文创园内</p><p>浦江县伊云小吃店（隔壁妈妈中山路店）：浦江县中山北路150号</p><p>浦江辣冠餐饮有限公司（好得火锅）：浦江县人民西路29号</p><p>浦江县巴图鲁烧烤店：浦江县1967老齿轮厂文创园G区G1一1F一5室</p><p>湖鲜8号外甥囡饭店</p><p>浦江县乔木餐厅（花漾餐厅）：浦江县中心城区月泉东路87号(庭歌汽车美容会所旁)</p><p>浦江县诗画里幸福产业有限公司;浦江县仙华街道金狮岭公园</p><p>浦江米兰酒店有限公司：浦江县江滨中路18号(客运中心正对面)</p><p>浦江县爱利根酒店：浦江县江滨东路29-31号</p><p>浦江县江涵餐饮有限公司（陈家厨房）：浦江县一点红大道58号</p><p>浦江县兴丰客餐饮有限公司（兴丰客大饭堂）：浦江县中山南路20-24号</p><p>浦江县金腿轩面馆：浦江县浦南街道文溪东路236号</p><p><span style="background-color: rgb(245, 247, 250); color: rgb(96, 98, 102);">东山小炒：浙江省金华市浦江县浦阳街道东山路108号</span></p><p>浦江县恩哥饭店（陈家食府）：浦江县宏业大道990号后门</p><p>浦江县浦阳街道健哥饭店：浦江县和平北路54号</p><p>老重庆火锅店：浦江县江滨东路7号</p><p>浦江县姚一肴饭店（蹄花老鸭煲）：浦江县观华路与凤荷路交叉口北60米</p><p>浦江县厨禾饭店（楼小厨）: 浦江县东山路86号</p><p>浦江县三城山餐饮休闲有限公司（三城山农庄）; 浦江县郑宅镇三郑村3区145号</p><p>浦江县陈胜露营服务部（仙华少女峰营地）;	浦江县仙华村</p><p>浦江县南北草堂餐馆&nbsp;:	浦江县凤荷路晶都二区18-19f号</p><p>浦江县好露味餐饮有限公司（兴丰客饭堂）:	浦江县和平北路96号</p>
        var GrantNo: String = "", // 411016f498826c8bb06df282e903427d
        var LimitAmount: Double = 0.0, // 5000
        var Name: String = "", // 浦江25元文旅消费券（线下领取1）
        var PageConfigure: String = "", // {"sponsor":""}
        var Status: Int = 0, // 2
        var VerifyTarget: Int = 0, // 3
        var VerifyType: Int = 0, // 2
        var showMore: Boolean = false
    )
}
