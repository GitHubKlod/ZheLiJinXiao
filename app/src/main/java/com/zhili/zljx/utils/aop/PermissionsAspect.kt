package com.zhili.zljx.utils.aop

import android.app.Activity
import com.hjq.permissions.OnPermissionCallback

import com.hjq.permissions.XXPermissions
import com.zhili.zljx.app.ActivityManege

import com.zhili.zljx.utils.aop.Permissions
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import timber.log.Timber

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject-Kotlin
 *    time   : 2019/12/06
 *    desc   : 权限申请切面
 */
@Suppress("unused")
@Aspect
class PermissionsAspect {

    /**
     * 方法切入点
     */
    @Pointcut("execution(@com.zhili.zljx.utils.aop.Permissions * *(..))")
    fun method() {}

    /**
     * 在连接点进行方法替换
     */
    @Around("method() && @annotation(permissions)")
    fun aroundJoinPoint(joinPoint: ProceedingJoinPoint, permissions: Permissions) {
        var activity: Activity? = null

        // 方法参数值集合
        val parameterValues: Array<Any?> = joinPoint.args
        for (arg: Any? in parameterValues) {
            if (arg !is Activity) {
                continue
            }
            activity = arg
            break
        }
        if ((activity == null) || activity.isFinishing || activity.isDestroyed) {
            activity = ActivityManege.currentActivity()
        }
        if ((activity == null) || activity.isFinishing || activity.isDestroyed) {
            Timber.e("The activity has been destroyed and permission requests cannot be made")
            return
        }
        requestPermissions(joinPoint, activity, permissions.value)
    }

    private fun requestPermissions(joinPoint: ProceedingJoinPoint, activity: Activity, permissions: Array<out String>) {
        XXPermissions.with(activity)
            .permission(*permissions)
            .request { _, allGranted ->
                if (allGranted) {
                    try {
                        // 获得权限，执行原方法
                        joinPoint.proceed()
                    } catch (e: Throwable) {
                        //                            CrashReport.postCatchedException(e)
                    }
                }
            }
    }
}