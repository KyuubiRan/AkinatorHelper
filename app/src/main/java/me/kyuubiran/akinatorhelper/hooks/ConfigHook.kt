package me.kyuubiran.akinatorhelper.hooks

import com.github.kyuubiran.ezxhelper.utils.findAllMethods
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import me.kyuubiran.akinatorhelper.view.sPrefs

object ConfigHook : BaseHook() {
    override fun init() {
        findAllMethods("com.digidust.elokence.akinator.factories.AkConfigFactory") {
            name == "isFreemium" || name == "isGameCountLimited" || name == "canShowAd"
        }.hookBefore {
            if (!sPrefs.getBoolean("unlock_pro", false)) return@hookBefore
            it.result = false
        }
        findAllMethods("com.digidust.elokence.akinator.factories.AkConfigFactory") {
            name == "isPaid" || name == "isUnlocked"
        }.hookBefore {
            if (!sPrefs.getBoolean("unlock_pro", false)) return@hookBefore
            it.result = true
        }
    }
}