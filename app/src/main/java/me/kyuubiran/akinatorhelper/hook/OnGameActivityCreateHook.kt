package me.kyuubiran.akinatorhelper.hook

import com.github.kyuubiran.ezxhelper.init.EzXHelperInit
import com.github.kyuubiran.ezxhelper.init.InitFields.appContext
import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import de.robv.android.xposed.XC_MethodHook
import me.kyuubiran.akinatorhelper.R

object OnGameActivityCreateHook : BaseHook() {
    private var hook: XC_MethodHook.Unhook? = null

    override fun init() {
        hook = findMethod("com.digidust.elokence.akinator.activities.AkActivity") {
            name == "onCreate"
        }.hookAfter {
            hook?.unhook()
            EzXHelperInit.initAppContext(
                addPath = true,
                initModuleResources = true
            )
            Log.toast(appContext.getString(R.string.module_load))
            Log.i("Inited game hook")
        }
    }
}