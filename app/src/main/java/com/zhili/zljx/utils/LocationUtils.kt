package com.zhili.zljx.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener

import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.hjq.toast.Toaster
import com.klod.base.dialog.OnDialogClickListener
import com.klod.base.ext.util.locationManager
import com.klod.base.ext.util.logE
import java.io.IOException
import java.util.Locale


/**
 *@Auther KLOD
 *2023/4/15 13:34
 */
object LocationUtils {

    private val TAG = "LocationUtils"
    private val GPS_REQUEST_CODE = 10
    private val WIFI_REQUEST_CODE = 11
    private val LOCATION_REQUEST_CODE = 12

    private val locationManager: LocationManager? = null
    private val locationListener: LocationListener? = null

    @SuppressLint("MissingPermission")
    fun getLocation(context: Context, listener: OnDialogClickListener) {
        val locationManager = context.locationManager
        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                val latitude = location.latitude
                val longitude = location.longitude

                "onLocationChanged: latitude=$latitude,longitude=$longitude".logE()
                // 获取城市信息
                val geocoder = Geocoder(context, Locale.getDefault())
                try {
                    val addresses = geocoder.getFromLocation(latitude, longitude, 1)
                    "获取的地址信息: = $addresses".logE()
                    if (addresses != null && addresses.size > 0) {
                        val address = addresses[0]
                        val city = address.locality
                         "onLocationChanged: city=$city".logE()
                        listener.onClick(1,city)
                    }else{
                        "空了".logE()
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                    listener.onClick(2,e.message?:"空数据")
                }
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

            override fun onProviderEnabled(provider: String) {}

            override fun onProviderDisabled(provider: String) {}
        }

        // 判断是否开启了定位服务
        if (locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER)!=true
            && locationManager?.isProviderEnabled(LocationManager.NETWORK_PROVIDER)!=true) {
            Toaster.showShort("请开启定位服务")
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            (context as Activity).startActivityForResult(intent, LOCATION_REQUEST_CODE)
        } else {
            // 判断是否有定位权限
            "进来阿斯顿".logE()
            XXPermissions.with(context)
                .permission(Permission.ACCESS_FINE_LOCATION)
                .permission(Permission.ACCESS_COARSE_LOCATION)
                .request { _, allGranted ->
                    if(allGranted){
                        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                1000,
                                1f,
                                locationListener
                            )
                            "进来阿斯顿1".logE()

                            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                            if (location != null) {
                                val latitude = location.latitude
                                val longitude = location.longitude
                                "getLocation: latitude=$latitude,longitude=$longitude".logE()
                                // 获取城市信息
                                val geocoder = Geocoder(context, Locale.getDefault())
                                try {
                                    val addresses = geocoder.getFromLocation(latitude, longitude, 1)
                                    if (addresses != null && addresses.size > 0) {
                                        val address = addresses[0]
                                        val city = address.locality
                                         "getLocation: city=$city".logE()
                                        listener.onClick(1,city)
                                    }
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                    listener.onClick(2,e.message?:"")
                                }
                            }
                        } else { // Wifi定位
                            "进来阿斯顿2".logE()

                            locationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                1000,
                                1f,
                                locationListener
                            )
                            val location =
                                locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                            if (location != null) {
                                val latitude = location.latitude
                                val longitude = location.longitude

                                "getLocation: latitude=$latitude,longitude=$longitude".logE()
                                // 获取城市信息
                                val geocoder = Geocoder(context, Locale.getDefault())
                                try {
                                    val addresses = geocoder.getFromLocation(latitude, longitude, 1)
                                    if (addresses != null && addresses.size > 0) {
                                        val address = addresses[0]
                                        val city = address.locality
                                        "getLocation: city=$city".logE()
                                        listener.onClick(1,city)
                                    }
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                    listener.onClick(2,e.message?:"")
                                }
                            }
                        }
                    }
                }
            // GPS定位

        }
    }

}