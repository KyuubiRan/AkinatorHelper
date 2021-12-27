package me.kyuubiran.akinatorhelper.util

import com.github.kyuubiran.ezxhelper.utils.findMethod

object AkinatorHelper {
    object Methods {
        private val mGetAkGameFactoryInstance by lazy {
            findMethod("com.digidust.elokence.akinator.factories.AkGameFactory") {
                name == "sharedInstance"
            }
        }

        private val akGameFactoryInstance: Any by lazy {
            mGetAkGameFactoryInstance(null)!!
        }

        private val mIsGameUnlocked by lazy {
            findMethod("com.digidust.elokence.akinator.factories.AkGameFactory") {
                name == "isUnlocked"
            }
        }

        fun isGameUnlocked(): Boolean {
            return mIsGameUnlocked.invoke(akGameFactoryInstance) as Boolean
        }

        private val mSetGameLocked by lazy {
            findMethod("com.digidust.elokence.akinator.factories.AkGameFactory") {
                name == "lockGame"
            }
        }

        fun setGameLocked() {
            mSetGameLocked.invoke(akGameFactoryInstance)
        }


        private val mSetGameUnlocked by lazy {
            findMethod("com.digidust.elokence.akinator.factories.AkGameFactory") {
                name == "unlockGame"
            }
        }

        fun setGameUnlocked() {
            mSetGameUnlocked.invoke(akGameFactoryInstance)
        }

    }

    var isGameUnlocked: Boolean
        set(value) {
            if (value) Methods.setGameUnlocked()
            else Methods.setGameLocked()
        }
        get() = Methods.isGameUnlocked()

}