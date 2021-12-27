package me.kyuubiran.akinatorhelper.hook

import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import me.kyuubiran.akinatorhelper.util.ConfigManager

object GameResultHook : BaseHook() {
    override fun init() {
        Log.i("Start hook game result")
        // region <winFirstTry>
        findMethod("com.digidust.elokence.akinator.factories.AkGameFactory") {
            name == "winFirstTry"
        }.hookBefore {
            if (!ConfigManager.alwaysWinFirstTry) return@hookBefore
            Log.i("Hooked ${it.method.name}")
            it.result = true
        }
        // endregion

        // region <isItemBought>
        findMethod("com.digidust.elokence.akinator.factories.AkPlayerBelongingsFactory") {
            name == "isItemBought"
        }.hookBefore {
            if (!ConfigManager.allItemBought) return@hookBefore
            Log.i("Hooked ${it.method.name}")
            it.result = true
        }
    }
}