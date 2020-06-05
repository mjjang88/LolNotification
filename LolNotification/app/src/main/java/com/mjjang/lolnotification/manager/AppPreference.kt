package com.mjjang.lolnotification.manager

import android.content.SharedPreferences
import android.preference.PreferenceManager

object AppPreference {

    fun getInstance(): SharedPreferences {
        return preference
    }

    private val preference: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.applicationContext())

    private operator fun set(key: String, value: Any?) {
        when (value) {
            is String? -> preference.edit().putString(key, value).apply()
            is Int -> preference.edit().putInt(key, value).apply()
            is Boolean -> preference.edit().putBoolean(key, value).apply()
            is Float -> preference.edit().putFloat(key, value).apply()
            is Long -> preference.edit().putLong(key, value).apply()
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    private inline operator fun <reified T : Any> get(key: String, defaultValue: T? = null): T? {
        return when (T::class) {
            String::class -> preference.getString(key, defaultValue as? String) as T?
            Int::class -> preference.getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> preference.getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> preference.getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> preference.getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    const val CHECKED = 1
    const val NOT_CHECKED = 0

    fun putRecomChecked(id: String?, value: Int) {
        if (id == null) {
            return;
        }
        val key = "recom_$id";
        preference.edit().putInt(key, value).apply()
    }

    fun getRecomChecked(id: String?) : Int {
        if (id == null) {
            return 0;
        }
        val key = "recom_$id";
        return preference.getInt(key, 0)
    }

    fun putNotRecomChecked(id: String?, value: Int) {
        if (id == null) {
            return;
        }
        val key = "notrecom_$id";
        preference.edit().putInt(key, value).apply()
    }

    fun getNotRecomChecked(id: String?) : Int {
        if (id == null) {
            return 0;
        }
        val key = "notrecom_$id";
        return preference.getInt(key, 0)
    }

    fun putFirstVisited(isVisited: Boolean) {
        val key = "first_visit"
        preference.edit().putBoolean(key, isVisited).apply()
    }

    fun getFirstVisited() : Boolean {
        val key = "first_visit"
        return preference.getBoolean(key, true)
    }
}