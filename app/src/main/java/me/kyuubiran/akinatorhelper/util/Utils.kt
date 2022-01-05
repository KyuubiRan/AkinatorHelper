package me.kyuubiran.akinatorhelper.util

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openUrl(url: String) {
    this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}