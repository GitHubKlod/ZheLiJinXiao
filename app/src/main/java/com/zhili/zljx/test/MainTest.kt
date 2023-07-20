package com.zhili.zljx.test

import com.klod.base.ext.util.logE
import okhttp3.internal.toHexString
import java.util.regex.Pattern

/**
 * @Auther KLOD
 * 2023/4/10 10:35
 */
object MainTest {
    @JvmStatic
    fun main(args: Array<String>) {


//        val dec = 20.052f
////        val df = DecimalFormat("#%") //乘以100后以百分比形式输出,此处输出"12%"
//
////        val df = DecimalFormat("#.##") //输出"0.12"
//
//        val df = DecimalFormat("00.00") //输出"00.12"
//
//        val s: String = df.format(dec)
//
//        println(s)

        var a = "2".toInt(2).toHexString()

        val sss ="""
                  <p>\t<img src=\"https://img.yiuxiu.com/YiuXiuMarketPayment/MarketPayment/202303/0d997760ec504a40b929e1c05f73b06d.jpg\"></p><p>3月13日，婺城区区管领导干部学习贯彻党的二十大精神专题培训班开班。区委书记高峰做开班动员时强调，要深入学习贯彻习近平新时代中国特色社会主义思想，深刻学习领会党的二十大精神，坚定捍卫“两个确立”、坚决做到“两个维护”，紧扣“三条先行路径”和三个“一号工程”，感恩奋进、奋楫赶超、实干争先，在中国式现代化金华实践中当先锋作示范。区委常委、组织部部长何军杰主持开班仪式。</p><p class=\"ql-align-center\"><img src=\"https://mmbiz.qpic.cn/mmbiz_jpg/T3S12HnoI1VbSbeKckg76xz1QmG8Q3nYLElo1x5WX51PfgokRPuObu078vfeMNQ2C3ZbMK4tYJOPT9b9wJiasBw/640?wx_fmt=jpeg&amp;tp=wxpic&amp;wxfrom=5&amp;wx_lazy=1&amp;wx_co=1\" alt=\"图片\"></p><p><br></p><p>\t高峰指出，党的二十大报告进一步阐述了中国式现代化的科学内涵、中国特色和本质要求，是以中国式现代化推进中华民族伟大复兴的政治宣言、行动纲领、进军号角，要坚持以党的二十大精神指引方向、凝聚共识，确保党的二十大精神在婺城落地生根、开花结果。</p><p>\t\t\t\t\t要在深刻领悟“两个确立”中永葆忠诚，不断增进政治认同、思想认同、理论认同、情感认同，以实干实绩诠释对党的绝对忠诚。</p><p><br></p><p>\t\t\t\t\t要在深刻领悟“两个大局”中勇毅前行，把学习贯彻党的二十大精神与全省奋力推动“两个先行”、全市“打造国际枢纽城、奋进现代都市区”的大场景结合起来，争当高水平建设内陆开放枢纽中心城市中坚力量。</p><p><br></p><p>\t\t\t\t\t要在深刻领悟“两大奇迹”中坚定信心，在中国式现代化新征程上凝聚强大动力、昂扬奋斗精神、坚定必胜信念。</p><p><br></p><p>\t\t\t\t\t要在深刻领悟“两个结合”中守正创新，坚持“四个自信”，坚定不移把党的创新理论贯彻落实到工作的各方面、全过程。</p><p><br></p><p>\t\t\t\t\t要在深刻领悟“两个答案”中自我革新，认真贯彻落实新时代党的建设总要求，聚力锻造实干争先婺城铁军。</p><p><br></p><p>\t\t\t\t\t要在深刻领悟“两个先行”中勠力同心，加快推动“1483”战略和“一带七心”布局落地成景，带动婺城发展实现新跨越、跑出加速度、开创新局面。</p><p><br></p><p class=\"ql-align-center\"><img src=\"https://mmbiz.qpic.cn/mmbiz_jpg/T3S12HnoI1VbSbeKckg76xz1QmG8Q3nYTkT3TpEdn106iaHpVzuzrs8Y07pk26K2jUnkterGxkRLw0okIBUDjlQ/640?wx_fmt=jpeg&amp;tp=wxpic&amp;wxfrom=5&amp;wx_lazy=1&amp;wx_co=1\" alt=\"图片\"></p><p>\t高峰强调，全区上下要学深悟透党的二十大精神，锚定中国式现代化金华实践，切实把学习成果转化为谋发展、抓发展、促发展的具体行动和实际成效。</p><p>\t要在知重负重中扛起主城担当，深化认识、主动作为，不断增强主城区的承载力吸附力，在“打造国际枢纽城、奋进现代都市区”中担起主城之责。</p><p><br></p><p>\t要在知短补短中提升主城能级，正视差距、找准路径，做强城市建设的主平台，打造产业发展的主战场，以“一带七心”的“一子落”实现全区的“满盘活”。</p><p><br></p><p>\t要在知新创新中干出主城作为，牢牢抓住省委实施三个“一号工程”的重要机遇期，重整全区资源、重塑发展格局，实现婺城全面赶超崛起。</p><p>\t</p><p>\t高峰强调，学习好宣传好贯彻好党的二十大精神，是当前和今后一个时期的首要政治任务和长期战略任务，要充分发挥“关键少数”的示范引领作用，牢固树立“大干项目大抓落实大转作风”的鲜明导向，以领导干部先学一步、深悟一层，加快锻造实干争先婺城铁军。</p><p>\t要进一步筑牢政治忠诚，深入开展党的理论学习和党性教育，实施领导干部政治能力提升计划，定期开展政治纪律和政治规矩教育，以上率下打造政治忠诚婺城铁军。</p><p><br></p><p>\t要进一步深化思想解放，一体推进、全面贯通创新、改革、开放要求，以重要领域和关键环节的突破带动全局，以干部的“一马当先”引领高质量发展的“万马奔腾”。</p><p><br></p><p>\t要进一步强化干事担当，坚持省委“四个坚持、八个不”、市委“六个突出、六个坚决不用”的选贤任能导向，坚持“实干实绩”的用人导向，完善领导干部考核机制，加大“不担当不作为”专项整治力度，进一步形成良好的选人用人氛围。</p><p><br></p><p>\t要进一步建设务实作风，坚持严的主基调，建章立制、晾晒比拼，大力弘扬求真务实、真抓实干的工作作风。</p><p><br></p><p>\t\t\t要进一步推动严格自律，加强领导干部日常监督管理，全覆盖进行警示教育，开展亲清政商关系专项检查，提高领导干部遵规守纪意识。</p><p class=\"ql-align-justify\"><br></p><p>\t本次专题培训班为期五天，全区区管领导干部分两批参加学习培训。</p>
        """.trimIndent()
        val input = "ASDASD content:hello world}] SADasd}]v bnm"
//        val pattern ="(?<=content:).*?(?=;\"}])".toRegex()
//        val pattern ="content:(.*)}]".toRegex()
//        val result = pattern.find(sss)?.value


//        val regex = Regex("""http(.*?)(\.jpg|\.png|(?=\\"))""")
        val regex = Regex("""http(.*?)(?=\\")""")
        val matches = regex.findAll(sss)
        val imgTags = matches.map { it.value }.toList()


        imgTags.forEach {
            println(it)

        }



//        val compile = Pattern.compile("""content:(.*)}]""")
//        val matcher = compile.matcher(sss)
            //val isMatch = Pattern.matches("columnNum", sss)
        //println("${isMatch}   ")//${matcher.group()}

    }



}

//        .replace(/\/\/.+?\r/g, '').replace(/\/\/.+?\n/g, '').replace(/\r/g,'').replace(/\n/g,'').replace(/\/\*.+?\*\//g, '')
//        val aaa = sss.regionMatches()
//            .replace("""\n""", "")
//            .replace("""content[:](.*)}]""".toRegex(), "")
//            .replace("content:(.*)}]".toRegex(), "提出按阿三大苏打实打实的")
//        var bbb = aaa.removeRange(aaa.indexOf("mainFunction"),aaa.length-1)
//            .replace("shareTitle:","\"shareTitle\":")
//            .replace("shareDesc:","\"shareDesc\":")
//            .replace("shareImg:","\"shareImg\":")
//            .replace("mainColor:","\"mainColor\":")
//            .replace("backgroundColor:","\"backgroundColor\":")
//            .replace("backgroundImg:","\"backgroundImg\":")
//            .replace("content","\"content\"")
//            .removeRange(0..1)
//            .replace("""\"""", "\"")
//            .replace("""\\"""", "\\\"")
//            .replace("""//主色调""", "")
//            .replace("""//背景颜色""", "")
//            .replace("""//背景图""", "")
//            .replace("'","\"")
//            .replace("\n", "")
//            .replace(":\\\"\"",":\"\"")
//            .replace("\\\"color\"","\"color\"")
//            .replace(";\\",";")

//        var ccc = bbb.removeRange(bbb.lastIndexOf(","),bbb.lastIndexOf(",")+1)
//            .replace("//.*".toRegex(),"")
