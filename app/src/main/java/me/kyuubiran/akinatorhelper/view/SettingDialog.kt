@file:Suppress("DEPRECATION")

package me.kyuubiran.akinatorhelper.view

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import com.github.kyuubiran.ezxhelper.init.EzXHelperInit
import com.github.kyuubiran.ezxhelper.utils.Log
import me.kyuubiran.akinatorhelper.BuildConfig
import me.kyuubiran.akinatorhelper.R
import me.kyuubiran.akinatorhelper.util.AkinatorHelper
import me.kyuubiran.akinatorhelper.util.ConfigManager
import me.kyuubiran.akinatorhelper.util.openUrl

class SettingDialog(activity: Activity) : AlertDialog.Builder(activity) {

    init {
        EzXHelperInit.addModuleAssetPath(activity)

        val prefsFragment = PrefsFragment()
        activity.fragmentManager.beginTransaction().add(prefsFragment, "Setting").commit()
        activity.fragmentManager.executePendingTransactions()

        prefsFragment.onActivityCreated(null)

        setView(prefsFragment.view)
        setTitle(activity.getString(R.string.app_name))
    }

    class PrefsFragment : PreferenceFragment(), Preference.OnPreferenceChangeListener,
        Preference.OnPreferenceClickListener {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.setting_dialog_prefs)

            findPreference("unlock_pro").onPreferenceChangeListener = this
            findPreference("always_first_try").onPreferenceChangeListener = this
            findPreference("all_item_bought").onPreferenceChangeListener = this

            findPreference("unlock_game").onPreferenceClickListener = this
            findPreference("relock_game").onPreferenceClickListener = this
            findPreference("goto_github").onPreferenceClickListener = this

            findPreference("module_version").summary =
                "${BuildConfig.VERSION_NAME}(${BuildConfig.VERSION_CODE})"
        }

        override fun onPreferenceChange(p: Preference?, v: Any?): Boolean {
            when (p?.key) {
                "unlock_pro" -> {
                    (v as? Boolean)?.let {
                        ConfigManager.unlockPro = it
                    }
                }
                "always_first_try" -> {
                    (v as? Boolean)?.let {
                        ConfigManager.alwaysWinFirstTry = it
                    }
                }
                "all_item_bought" -> {
                    (v as? Boolean)?.let {
                        ConfigManager.allItemBought = it
                    }
                }
            }
            return true
        }

        override fun onPreferenceClick(p: Preference?): Boolean {
            when (p?.key) {
                "unlock_game" -> {
                    AkinatorHelper.isGameUnlocked = true
                    Log.toast(getString(R.string.unlock_game_success))
                }
                "relock_game" -> {
                    AkinatorHelper.isGameUnlocked = false
                    Log.toast(getString(R.string.relock_game_success))
                }
                "goto_github" -> {
                    openUrl("https://github.com/KyuubiRan/AkinatorHelper")
                }
            }
            return true
        }
    }
}