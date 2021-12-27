package me.kyuubiran.akinatorhelper.util

import android.content.Context
import com.github.kyuubiran.ezxhelper.init.InitFields.appContext

object ConfigManager {
    val sPrefs by lazy {
        appContext.getSharedPreferences("akinator_helper", Context.MODE_PRIVATE)
    }

    var unlockPro: Boolean
        set(value) = sPrefs.edit().putBoolean("unlock_pro", value).apply()
        get() = sPrefs.getBoolean("unlock_pro", false)

    var alwaysWinFirstTry: Boolean
        set(value) = sPrefs.edit().putBoolean("always_first_try", value).apply()
        get() = sPrefs.getBoolean("always_first_try", false)
}