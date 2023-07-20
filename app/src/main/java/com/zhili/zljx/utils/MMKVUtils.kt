package com.zhili.zljx.utils

import com.zhili.zljx.common.Contacts
import com.zhili.zljx.ext.fromJson
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tencent.mmkv.MMKV
import com.zhili.zljx.bean.LocationDataInfo
import com.zhili.zljx.bean.TokenBean
import com.zhili.zljx.bean.UserInfo
import com.zhili.zljx.ext.toJson

/**
 * 替换成MMKV
 */
object MMKVUtils {
    private val mmkv: MMKV = MMKV.defaultMMKV()

    private const val PREFERENCES_NAME = "preferences_mall"

    init {
        //迁移数据
//        SharedPreferences sharedPreferences = MyApplication.getMyApplication().
//                getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
//        if(!sharedPreferences.getAll().isEmpty()){
//            mmkv.importFromSharedPreferences(sharedPreferences);
//            sharedPreferences.edit().clear().commit();
//        }
    }

    fun getUser():UserInfo? {
        val json = mmkv.decodeString(Contacts.UserInfo, null)
        return json?.fromJson(UserInfo::class.java)
    }


    fun setUser(userInfo: UserInfo) {
        putString(Contacts.UserInfo,userInfo.toJson())
    }

    fun getLocationInfo():LocationDataInfo? {
        val json = mmkv.decodeString(Contacts.LocationInfo, null)
        return json?.fromJson(LocationDataInfo::class.java)
    }
    fun setLocationInfo(locationDataInfo: LocationDataInfo) {
        putString(Contacts.LocationInfo,locationDataInfo.toJson())
    }

   fun getToken():TokenBean?{
        val json = mmkv.decodeString(Contacts.TokenInfo, null)
        return json?.fromJson(TokenBean::class.java)
    }
    fun setToken(tokenInfo: TokenBean){
        putString(Contacts.TokenInfo,tokenInfo.toJson())
//        putString(Contacts.TokenString,tokenInfo.)
    }

    fun removeValueForKey(key:String){
        mmkv.removeValueForKey(key)
    }

    fun putBoolean(key: String?, value: Boolean) {
        mmkv.encode(key, value)
    }

    fun getBoolean(key: String?): Boolean {
        return mmkv.decodeBool(key, false)
    }
    fun getBoolean(key: String?,def:Boolean): Boolean {
        return mmkv.decodeBool(key, def)
    }

    fun putString(key: String?, value: String?) {
        mmkv.encode(key, value)
    }

    fun getString(key: String?)=mmkv.decodeString(key, "") ?: ""


    fun getString(key: String?, def: String?)=mmkv.decodeString(key, def) ?: ""


    fun putInt(key: String?, value: Int) {
        mmkv.encode(key, value)
    }

    fun getInt(key: String?) = mmkv.decodeInt(key, 0)


    fun putLong(key: String?, value: Long) {
        mmkv.encode(key, value)

    }

    fun getLong(key: String?): Long {
        return mmkv.decodeLong(key, 0)
    }

    fun putList(key: String?, dataList: List<*>?) {
        val gson = Gson()
        val json = gson.toJson(dataList)
        mmkv.encode(key, json)
    }

    fun getList(key: String?): MutableList<String> {
        val dataList: MutableList<String>
        val json = mmkv.decodeString(key, null) ?: return mutableListOf()
        val gson = Gson()
        dataList = gson.fromJson(json, object : TypeToken<MutableList<String?>?>() {}.type)
        return dataList
    }

}