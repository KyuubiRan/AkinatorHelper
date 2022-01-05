@file:Suppress("DEPRECATION")

package me.kyuubiran.akinatorhelper.view

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.text.InputType
import android.widget.EditText
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

        setPositiveButton(R.string.close, null)
        setCancelable(false)
    }

    class PrefsFragment : PreferenceFragment(), Preference.OnPreferenceChangeListener,
        Preference.OnPreferenceClickListener {

        private fun showEditDialog(
            title: String,
            hint: String = "",
            cancelable: Boolean = true,
            defText: String = "",
            inputType: Int = InputType.TYPE_CLASS_TEXT,
            onConfirm: ((String) -> Unit)? = null,
            onCancel: ((String) -> Unit)? = null
        ) {
            val et = EditText(this.activity).also {
                it.hint = hint
                it.inputType = inputType
                it.setText(defText)
            }
            AlertDialog.Builder(this.activity).run {
                setTitle(title)
                setView(et)
                setCancelable(cancelable)
                setPositiveButton(R.string.confirm) { _, _ -> onConfirm?.invoke(et.text.toString()) }
                setNegativeButton(R.string.cancel) { _, _ -> onCancel?.invoke(et.text.toString()) }
                show()
            }
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.setting_dialog_prefs)

            findPreference("unlock_pro").onPreferenceChangeListener = this
            findPreference("always_first_try").onPreferenceChangeListener = this
            findPreference("all_item_bought").onPreferenceChangeListener = this

            findPreference("unlock_game").onPreferenceClickListener = this
            findPreference("relock_game").onPreferenceClickListener = this
            findPreference("add_win_times").onPreferenceClickListener = this
            findPreference("add_lose_times").onPreferenceClickListener = this
            findPreference("set_gz").onPreferenceClickListener = this
            findPreference("add_xp").onPreferenceClickListener = this
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
                "add_win_times" -> {
                    showEditDialog(
                        getString(R.string.increase_win_times_title),
                        getString(R.string.times_to_add),
                        defText = "1",
                        inputType = InputType.TYPE_CLASS_NUMBER,
                        onConfirm = {
                            (it.toIntOrNull() ?: 0).let { num ->
                                if (num < 1) {
                                    Log.toast(getString(R.string.num_must_be_greater_than_zero))
                                    return@showEditDialog
                                }
                                repeat(num) {
                                    AkinatorHelper.AKGameFactory.addOneWonGame()
                                }
                                Log.toast(getString(R.string.success_increate_win_times, num))
                            }
                        }
                    )
                }
                "add_lose_times" -> {
                    showEditDialog(
                        getString(R.string.increase_lose_times_title),
                        getString(R.string.times_to_add),
                        defText = "1",
                        inputType = InputType.TYPE_CLASS_NUMBER,
                        onConfirm = {
                            (it.toIntOrNull() ?: 0).let { num ->
                                if (num < 1) {
                                    Log.toast(getString(R.string.num_must_be_greater_than_zero))
                                    return@showEditDialog
                                }
                                repeat(num) {
                                    AkinatorHelper.AKGameFactory.addOneWonGame()
                                }
                                Log.toast(getString(R.string.success_increate_lose_times, num))
                            }
                        }
                    )
                }
                "set_gz" -> {
                    showEditDialog(
                        getString(R.string.set_gz_title),
                        defText = "100000",
                        inputType = InputType.TYPE_CLASS_NUMBER,
                        onConfirm = { num ->
                            num.toIntOrNull().let {
                                if (it == null) {
                                    Log.toast(getString(R.string.invalid_num, num))
                                } else {
                                    if (AkinatorHelper.AKConfigFactory.isUserConnected())
                                        AkinatorHelper.AkPlayerBelongingsFactory.setGenizBalanceAccount(
                                            it
                                        )
                                    else
                                        AkinatorHelper.AkPlayerBelongingsFactory.setGenizBalance(it)
                                    Log.toast(getString(R.string.success_set_gz, it))
                                }
                            }
                        }
                    )
                }
                "add_xp" -> {
                    showEditDialog(
                        getString(R.string.add_xp_title),
                        defText = "100",
                        inputType = InputType.TYPE_CLASS_NUMBER,
                        onConfirm = { num ->
                            num.toIntOrNull().let {
                                if (it == null) {
                                    Log.toast(getString(R.string.invalid_num, num))
                                } else {
                                    AkinatorHelper.AkPlayerBelongingsFactory.addXp(it)
                                    Log.toast(getString(R.string.success_add_xp, it))
                                }
                            }
                        }
                    )
                }
                "goto_github" -> {
                    this.activity.openUrl("https://github.com/KyuubiRan/AkinatorHelper")
                }
            }
            return true
        }
    }
}