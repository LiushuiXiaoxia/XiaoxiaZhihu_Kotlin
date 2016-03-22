package cn.mycommons.xiaoxiazhihu.core.io

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import cn.mycommons.xiaoxiazhihu.BuildConfig
import cn.mycommons.xiaoxiazhihu.core.log.logi
import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * SharedPreferencesPlus <br/>
 * Created by xiaqiulei on 2016-03-22.
 */

private val KEY_VERION_POSTFIX = "___version"

class SharedPreferencesPlus(context: Context, name: String) {

    private val sp: SharedPreferences
    private val gson: Gson

    init {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        gson = Gson()
    }

    // 生成一个key对应版本的key
    private fun generateVersionKey(key: String): String = key + KEY_VERION_POSTFIX

    fun getString(key: String, defaultValue: String = ""): String = sp.getString(key, defaultValue)

    fun putString(key: String, content: String): Boolean = sp.edit().putString(key, content).commit()

    fun getInt(key: String, defValue: Int): Int = sp.getInt(key, defValue)

    fun getLong(key: String, defValue: Long): Long = sp.getLong(key, defValue)

    fun putInt(key: String, value: Int): Boolean = sp.edit().putInt(key, value).commit()

    fun putLong(key: String, value: Long): Boolean = sp.edit().putLong(key, value).commit()

    fun remove(key: String): Boolean = sp.edit().remove(key).remove(generateVersionKey(key)).commit()


    fun putBoolean(key: String, value: Boolean): Boolean = sp.edit().putBoolean(key, value).commit()

    fun getBoolean(key: String, defValue: Boolean): Boolean = sp.getBoolean(key, defValue)

    /**
     * 保存对象,对象是以json字符串形式保存，如果对象是null，则保存为null
     * 保存对象，暂时不处理对象的class类型

     * @param key
     * *
     * @param obj
     * *
     * @return
     */
    fun putObject(key: String, obj: Any?): Boolean {
        var json: String? = null
        if (obj != null) {
            try {
                json = gson.toJson(obj)
            } catch (e: Exception) {
                logi(String.format("save (%s,%s) fail.", key, obj))
                return false
            }
        }
        return putString(key, json as String)
    }

    /**
     * 保存对象,对象是以json字符串形式保存，如果对象是null，则保存为null
     * 保存对象，暂时不处理对象的class类型
     * 同时保存该对象保存时候，app的版本

     * @param key
     * *
     * @param object
     * *
     * @return
     */
    fun putObjectWithVersion(key: String, `object`: Any): Boolean = putObject(key, `object`) && putVersion(key)

    /**
     * 获取保存的对象，由于保存的时候是以json字符串保存，没有保存class类型，所以如果type与原先保存的对象的类型有冲突，结果返回是null

     * @param key
     * *
     * @param type 对象的type，一般情况下是 object.class
     * *
     * @param
     * *
     * @return
     */
    fun <T> getObject(key: String, type: Type?): T {

        var t: T? = null

        val string = getString(key)
        if (!TextUtils.isEmpty(string) && type != null) {
            try {
                t = gson.fromJson<T>(string, type)
            } catch (e: Exception) {
                // json解析出错
                val msg = String.format("get %s of %s fail", key, type)
                logi(msg)
            }

        }

        return t as T
    }

    /**
     * 提供键值对保存时保存版本
     * key : value
     * key_version : version_code

     * @param key
     * *
     * @return
     */
    fun putVersion(key: String): Boolean = putInt(generateVersionKey(key), BuildConfig.VERSION_CODE)

    /**
     * 获取某个字段对应的版本，如没有则为-1

     * @param key
     * *
     * @return
     */
    fun getObjectVersion(key: String): Int = getInt(generateVersionKey(key), -1)
}