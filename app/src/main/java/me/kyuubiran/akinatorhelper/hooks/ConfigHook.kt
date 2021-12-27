package me.kyuubiran.akinatorhelper.hooks

import com.github.kyuubiran.ezxhelper.utils.findAllMethods
import com.github.kyuubiran.ezxhelper.utils.hookBefore

object ConfigHook : BaseHook() {
    override fun init() {
        findAllMethods("com.digidust.elokence.akinator.factories.AkConfigFactory") {
            name == "isFreemium" || name == "isGameCountLimited" || name == "canShowAd"
        }.hookBefore {
            it.result = false
        }
        findAllMethods("com.digidust.elokence.akinator.factories.AkConfigFactory") {
            name == "isPaid" || name == "isUnlocked"
        }.hookBefore {
            it.result = true
        }
    }
}