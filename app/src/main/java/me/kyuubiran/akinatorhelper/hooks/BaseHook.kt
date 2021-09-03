package me.kyuubiran.akinatorhelper.hooks

abstract class BaseHook {
    var isInit: Boolean = false

    abstract fun init()
}