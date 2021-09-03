package me.kyuubiran.akinatorhelper.hooks

import com.github.kyuubiran.ezxhelper.utils.getMethodsByCondition
import com.github.kyuubiran.ezxhelper.utils.hookBefore

object ConfigHook : BaseHook() {
    override fun init() {
        getMethodsByCondition("com.digidust.elokence.akinator.factories.AkConfigFactory") {
            it.name == "isFreemium" || it.name == "isGameCountLimited" || it.name == "canShowAd"
        }.hookBefore { it.result = false }
        getMethodsByCondition("com.digidust.elokence.akinator.factories.AkConfigFactory") {
            it.name == "isPaid" || it.name == "isUnlocked"
        }.hookBefore { it.result = true }
    }
}