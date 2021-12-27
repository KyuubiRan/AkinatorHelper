package me.kyuubiran.akinatorhelper.util

import android.content.Intent
import android.net.Uri
import com.github.kyuubiran.ezxhelper.init.InitFields.appContext

fun openUrl(url: String) {
    appContext.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}