package me.kyuubiran.akinatorhelper.util

import android.content.Context
import android.content.SharedPreferences
import com.github.kyuubiran.ezxhelper.init.InitFields.appContext

object ConfigManager {
    val sPrefs: SharedPreferences by lazy {
        appContext.getSharedPreferences("akinator_helper", Context.MODE_PRIVATE)
    }

    var unlockPro: Boolean
        set(value) = sPrefs.edit().putBoolean("unlock_pro", value).apply()
        get() = sPrefs.getBoolean("unlock_pro", false)

    var alwaysWinFirstTry: Boolean
        set(value) = sPrefs.edit().putBoolean("always_first_try", value).apply()
        get() = sPrefs.getBoolean("always_first_try", false)

    var allItemBought: Boolean
        set(value) = sPrefs.edit().putBoolean("all_item_bought", value).apply()
        get() = sPrefs.getBoolean("all_item_bought", false)

}