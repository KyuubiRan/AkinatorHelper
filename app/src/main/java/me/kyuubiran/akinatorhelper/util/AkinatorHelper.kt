package me.kyuubiran.akinatorhelper.util

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.loadClass

object AkinatorHelper {
    object Methods {
        private val C_AK_GAME_FACTORY by lazy {
            loadClass("com.digidust.elokence.akinator.factories.AkGameFactory")
        }

        private val mGetAkGameFactoryInstance by lazy {
            findMethod(C_AK_GAME_FACTORY) {
                name == "sharedInstance"
            }
        }

        private val akGameFactoryInstance: Any by lazy {
            mGetAkGameFactoryInstance(null)!!
        }

        private val mIsGameUnlocked by lazy {
            findMethod(C_AK_GAME_FACTORY) {
                name == "isUnlocked"
            }
        }

        fun isGameUnlocked(): Boolean {
            return mIsGameUnlocked.invoke(akGameFactoryInstance) as Boolean
        }

        private val mSetGameLocked by lazy {
            findMethod(C_AK_GAME_FACTORY) {
                name == "lockGame"
            }
        }

        fun setGameLocked() {
            mSetGameLocked.invoke(akGameFactoryInstance)
        }


        private val mSetGameUnlocked by lazy {
            findMethod(C_AK_GAME_FACTORY) {
                name == "unlockGame"
            }
        }

        fun setGameUnlocked() {
            mSetGameUnlocked.invoke(akGameFactoryInstance)
        }

        private val mAddOneWonGame by lazy {
            findMethod(C_AK_GAME_FACTORY) {
                name == "addOneWonGame"
            }
        }

        fun addOneWonGame() {
            mAddOneWonGame.invoke(akGameFactoryInstance)
        }

        private val mAddOneLostGame by lazy {
            findMethod(C_AK_GAME_FACTORY) {
                name == "addOneLostGame"
            }
        }

        fun addOneLostGame() {
            mAddOneLostGame.invoke(akGameFactoryInstance)
        }

    }

    var isGameUnlocked: Boolean
        set(value) {
            if (value) Methods.setGameUnlocked()
            else Methods.setGameLocked()
        }
        get() = Methods.isGameUnlocked()

}