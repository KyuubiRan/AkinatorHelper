package me.kyuubiran.akinatorhelper.hook

import android.app.Activity
import android.widget.ImageView
import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.getObjectAs
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import me.kyuubiran.akinatorhelper.view.SettingDialog

object SettingHook : BaseHook() {
    override fun init() {
        Log.i("Start hook setting button")

        findMethod("com.digidust.elokence.akinator.activities.HomeActivity") {
            name == "onCreate"
        }.hookAfter {
            val ctx = it.thisObject as Activity
            val btn = ctx.getObjectAs<ImageView>("mUiButtonOpenMenu")
            btn.setOnLongClickListener {
                SettingDialog(ctx).show()
                true
            }
        }
    }
}