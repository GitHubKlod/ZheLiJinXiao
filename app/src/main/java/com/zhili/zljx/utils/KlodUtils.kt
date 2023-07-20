package com.zhili.zljx.utils

import android.media.MediaCodecInfo
import android.media.MediaCodecList
import android.media.MediaCodecList.REGULAR_CODECS
import com.blankj.utilcode.util.TimeUtils

import java.text.SimpleDateFormat
import java.util.*


object KlodUtils {

    //12000  => 1.2W
    fun numberToW(num: String, desc: String) =num
//        if (num.length > 4 && RegexUtils.checkDigit(num)) {
//            val num1 = "${num.toInt() / 10000 }.${num.toInt() % 10000 / 1000 }$desc"
//            num1
//        } else { "点赞数$num" }


    fun getMaxPage(totalCount: Int, pageSize: Int=10):Int{
        var maxPage = totalCount / pageSize
        if (totalCount % pageSize != 0) {
            maxPage++
        }
        return  maxPage

    }

    /**
     * 根据时间戳生成一个随机字符串
     */
    fun getRandomStringByTimestamp(timeStamp: Long): String {
        val sb = StringBuilder()
//        val charArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray()
//        var t = timeStamp
//        while (t > 0) {
//            sb.append(charArray[(t % charArray.size).toInt()])
//            t /= charArray.size
//        }
        sb.append(timeStamp)
        sb.append("Android")
        sb.append((Math.random() * 1000000).toInt())
        return sb.toString()
    }

    /**
     * 是否支持 hevc 硬解
     * @return
     */
    fun isH265HWDecoderSupport(): Boolean {
        val codecList = MediaCodecList(REGULAR_CODECS)

        // 获取设备支持的所有 codec 信息
        val codecInfos = codecList.codecInfos
        for (i in codecInfos.indices) {
            val codecInfo: MediaCodecInfo = codecInfos[i]

            // 解码codec & 解码器名称包含'hevc' & 不是软件codec
            if (!codecInfo.isEncoder && (codecInfo.name.contains("hevc")
                            && !isSWCodec(codecInfo.name))) {
                return true
            }
        }
        return false
    }

    /**
     * 是否为软件 codec
     * @param codecName
     * @return
     */
    fun isSWCodec(codecName: String): Boolean {
        if (codecName.startsWith("OMX.google.")) {
            return true
        }
        return !codecName.startsWith("OMX.")
    }

//    fun is5HourLater():Boolean{
//
//        val start = PreferencesUtils.getInstance().getLong(AppConstant.Hour_5)
//        val c = System.currentTimeMillis()
//
//        val result = c - start
//        return if(result>0){
//            //超过5小时
//            result/1000/60/60>5
////            result/1000>30
//            //     秒    分 时 天
////            result/1000/60>1
//        }else{
//            false
//        }
//    }
//    fun is3DayLater():Boolean{
//
//        val start = PreferencesUtils.getInstance().getLong(AppConstant.GET_TOKEN_TIME)
//        val c = System.currentTimeMillis()
//
//        val result = c - start
//        return if(result>0){
//            //超过3天
//            result/1000/60/60/24>2
//            //     秒    分 时 天
////            result/1000/60>1
//        }else{
//            false
//        }
//    }
//    fun is7DayLater():Boolean{
//
//        val start = PreferencesUtils.getInstance().getLong(AppConstant.DAY_7)
//        if(start==0L){
//            return  false
//        }
//        val c = System.currentTimeMillis()
//
//        val result = c - start
//        return if(result>0){
//            //超过6天
//            result/1000/60/60/24>6
//            //     秒    分 时 天
////            result/1000/60>1
//        }else{
//            false
//        }
//    }

    fun  getTwoTimeSub(startTime: String, endTime: String) :Long{
        //设置时间格式
        val sdfStart = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val sdfEnd = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        //将输入时间转换为ms
        val result = sdfStart.parse(endTime).time-sdfStart.parse(startTime).time
        return if(result<0){
            0
        }else{
            result/1000;
        }

    }


    fun  formatDate(time: String, patterm: String = "yyyy-MM-dd HH:mm:ss") :String{
        //设置时间格式
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        //将输入时间转换为ms
        var cal = Calendar.getInstance()
        cal.timeInMillis = sdf.parse(time).time
        cal.add(Calendar.HOUR, +8)

        return TimeUtils.date2String(cal.time, patterm);
    }

    fun  formatDateJAVA(time: String) :String{
        //设置时间格式
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        //将输入时间转换为ms
        var cal = Calendar.getInstance();
        cal.setTimeInMillis(sdf.parse(time).getTime());
        cal.add(Calendar.HOUR, +8);

        return TimeUtils.date2String(cal.time, "yyyy-MM-dd HH:mm:ss");
    }
    fun getGapSecond(endTime: String):Long{
        //设置时间格式
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        //将输入时间转换为ms
        var cal = Calendar.getInstance();
        cal.setTimeInMillis(sdf.parse(endTime).getTime());
        cal.add(Calendar.HOUR, +8);
        //结束时间戳
        var end = cal.time.time
        //当前时间戳
        var start = System.currentTimeMillis()
        if(start>end){
            return 0L
        }
        return  (end-start)/1000
    }


}
