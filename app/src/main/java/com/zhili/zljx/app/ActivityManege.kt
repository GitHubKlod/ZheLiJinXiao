package com.zhili.zljx.app

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.os.Process
import java.util.LinkedList
import java.util.Stack
import kotlin.system.exitProcess


/**
 *@Auther KLOD
 *2023/4/6 15:47
 */
 object ActivityManege {

    private var activityStack: Stack<Activity>? = null
    private var instance: ActivityManege? = null


    fun finishActivityExcept( cls: Class< * >) {

            var y =0
            for (activityIndex in activityStack!!.indices) {
                val activity = activityStack!![activityIndex-y]
//                Log.e("FinishManege","${activity.javaClass}+${cls}")
                if (activity.javaClass != cls) {

                    finishActivity(activityStack!![activityIndex-y])
                    y++
                }
            }

    }

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity? {
        if (null != activityStack) {
            try {
                return activityStack!!.lastElement()
            } catch (e: NoSuchElementException) {
            }
        }
        return null
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        if (null != activityStack) {
            var activity = activityStack!!.lastElement()
            if (activity != null) {
                activity.finish()
                activity = null
            }
        }
    }

    fun removeActivity(activity: Activity?) {
        var activity = activity
        if (activity != null && null != activityStack) {
            activityStack!!.remove(activity)
            activity = null
        }
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        var activity = activity
        if (activity != null && null != activityStack) {
            activityStack!!.remove(activity)
            activity.finish()
            activity = null
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        if (null != activityStack) {
            val activities: MutableList<Activity> = LinkedList()
            //查找需要结束的Activity
            for (activity in activityStack!!) {
                if (activity.javaClass == cls) {
                    activities.add(activity)
                }
            }

            //结束Activity
            for (activity in activities) {
                finishActivity(activity)
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        if (null != activityStack) {
            for (activity in activityStack!!) {
                activity.finish()
            }
            activityStack!!.clear()
        }
    }


    /**
     * 结束除了当前Activity之外的所有 Activity
     */
    fun finishAllActivityWithoutNow(nowActivity: Activity) {
        if (null != activityStack) {
            for (activity in activityStack!!) {
                if (activity.javaClass.name.contains(nowActivity.javaClass.name)) {
                    return
                } else {
                    activity.finish()
                }
            }
            activityStack!!.clear()
        }
    }

    /**
     * 退出应用程序
     *
     * @param context
     */
    @SuppressLint("MissingPermission")
    fun appExit(context: Context) {
        try {
            finishAllActivity()
            val activityMgr = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityMgr.killBackgroundProcesses(context.packageName)
            exitProcess(0)
            Process.killProcess(Process.myPid())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}