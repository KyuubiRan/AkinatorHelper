@file:Suppress("DEPRECATION")

package me.kyuubiran.akinatorhelper.view

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import com.github.kyuubiran.ezxhelper.init.EzXHelperInit
import me.kyuubiran.akinatorhelper.R
import me.kyuubiran.akinatorhelper.util.ConfigManager


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
        }

        override fun onPreferenceChange(p: Preference?, v: Any?): Boolean {
            when (p?.key) {
                "unlock_pro" -> {
                    (v as? Boolean)?.let {
                        ConfigManager.unlockPro = it
                    }
                }
            }
            return true
        }

        override fun onPreferenceClick(p0: Preference?): Boolean {
            return true
        }
    }
}