@file:Suppress("DEPRECATION")

package me.kyuubiran.akinatorhelper.view

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import com.github.kyuubiran.ezxhelper.init.EzXHelperInit
import com.github.kyuubiran.ezxhelper.init.InitFields.appContext
import me.kyuubiran.akinatorhelper.R

val sPrefs by lazy {
    appContext.getSharedPreferences("akinator_helper", Context.MODE_PRIVATE)
}

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
        }

        override fun onPreferenceChange(p: Preference?, v: Any?): Boolean {
            when (p?.key) {
                "unlock_pro" -> {
                    (v as? Boolean)?.let {
                        sPrefs.edit().putBoolean(p.key, it)
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