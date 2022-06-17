package me.kyuubiran.akinatorhelper

import com.github.kyuubiran.ezxhelper.init.EzXHelperInit
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.callbacks.XC_LoadPackage
import me.kyuubiran.akinatorhelper.hook.*

class HookEntry : IXposedHookLoadPackage, IXposedHookZygoteInit {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (!lpparam.packageName.startsWith("com.digidust.elokence.akinator") || lpparam.packageName != lpparam.processName) return
        EzXHelperInit.initHandleLoadPackage(lpparam)
        EzXHelperInit.setLogTag("Akinator Helper")
        EzXHelperInit.setToastTag("Akinator Helper")
        init
    }

    private val init: Unit by lazy {
        initHooks(AppHook, SettingHook, ConfigHook, GameResultHook)
    }

    private fun initHooks(vararg hooks: BaseHook) {
        hooks.forEach {
            if (it.isInit) return
            it.init()
            it.isInit = true
        }
    }

    override fun initZygote(startupParam: IXposedHookZygoteInit.StartupParam) {
        EzXHelperInit.initZygote(startupParam)
    }
}