package me.kyuubiran.akinatorhelper.hook

abstract class BaseHook {
    var isInit: Boolean = false

    abstract fun init()
}