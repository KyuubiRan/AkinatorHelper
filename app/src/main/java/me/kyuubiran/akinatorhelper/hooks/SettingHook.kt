package me.kyuubiran.akinatorhelper.hooks

import android.app.Activity
import android.widget.ImageView
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.getObjectAs
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import me.kyuubiran.akinatorhelper.view.SettingDialog

object SettingHook : BaseHook() {
    override fun init() {
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