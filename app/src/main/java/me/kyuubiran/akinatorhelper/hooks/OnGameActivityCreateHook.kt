package me.kyuubiran.akinatorhelper.hooks

import com.github.kyuubiran.ezxhelper.init.EzXHelperInit
import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.findMethodByCondition
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import de.robv.android.xposed.XC_MethodHook

object OnGameActivityCreateHook : BaseHook() {
    private var hook: XC_MethodHook.Unhook? = null

    override fun init() {
        hook = findMethodByCondition("com.digidust.elokence.akinator.activities.AkActivity") {
            it.name == "onCreate"
        }.hookAfter {
            hook?.unhook()
            EzXHelperInit.initAppContext()
            Log.toast("模块加载成功!")
        }
    }
}