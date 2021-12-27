package me.kyuubiran.akinatorhelper.hook

import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.findAllMethods
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import me.kyuubiran.akinatorhelper.util.ConfigManager

object ConfigHook : BaseHook() {
    override fun init() {
        Log.i("Start hook configs")
        findAllMethods("com.digidust.elokence.akinator.factories.AkConfigFactory") {
            name == "isFreemium" || name == "isGameCountLimited" || name == "canShowAd"
        }.hookBefore {
            if (!ConfigManager.unlockPro) return@hookBefore
            Log.i("Hooked ${it.method.name}")
            it.result = false
        }
        findAllMethods("com.digidust.elokence.akinator.factories.AkConfigFactory") {
            name == "isPaid" || name == "isUnlocked"
        }.hookBefore {
            if (!ConfigManager.unlockPro) return@hookBefore
            Log.i("Hooked ${it.method.name}")
            it.result = true
        }
    }
}