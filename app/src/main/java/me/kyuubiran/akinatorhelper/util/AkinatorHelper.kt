package me.kyuubiran.akinatorhelper.util

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.loadClass

object AkinatorHelper {
    object AkPlayerBelongingsFactory {
        private val C_AK_PLAYER_BELONGINGS_FACTORY by lazy {
            loadClass("com.digidust.elokence.akinator.factories.AkPlayerBelongingsFactory")
        }

        private val sInstance: Any by lazy {
            findMethod(C_AK_PLAYER_BELONGINGS_FACTORY) {
                name == "sharedInstance"
            }.invoke(null)!!
        }

        private val mSetGenizBalance by lazy {
            findMethod(C_AK_PLAYER_BELONGINGS_FACTORY) {
                name == "setGenizBalance"
            }
        }

        private val mSetGenizBalanceAccount by lazy {
            findMethod(C_AK_PLAYER_BELONGINGS_FACTORY) {
                name == "setGenizBalanceAccount"
            }
        }

        fun setGenizBalance(balance: Int) {
            mSetGenizBalance.invoke(sInstance, balance)
        }

        fun setGenizBalanceAccount(balance: Int) {
            mSetGenizBalanceAccount.invoke(sInstance, balance)
        }

        private val mAddXp by lazy {
            findMethod(C_AK_PLAYER_BELONGINGS_FACTORY) {
                name == "addXp"
            }
        }

        fun addXp(xp: Int) {
            mAddXp.invoke(sInstance, xp)
        }
    }

    object AKConfigFactory {
        private val C_AK_CONFIG_FACTORY by lazy {
            loadClass("com.digidust.elokence.akinator.factories.AkConfigFactory")
        }

        private val sInstance: Any by lazy {
            findMethod(C_AK_CONFIG_FACTORY) {
                name == "sharedInstance"
            }.invoke(null)!!
        }

        private val mIsUserConnected by lazy {
            findMethod(C_AK_CONFIG_FACTORY) {
                name == "isUserConnected"
            }
        }

        fun isUserConnected(): Boolean {
            return mIsUserConnected.invoke(sInstance) as Boolean
        }
    }

    object AKGameFactory {
        private val C_AK_GAME_FACTORY by lazy {
            loadClass("com.digidust.elokence.akinator.factories.AkGameFactory")
        }

        private val sInstance: Any by lazy {
            findMethod(C_AK_GAME_FACTORY) {
                name == "sharedInstance"
            }.invoke(null)!!
        }

        private val mIsGameUnlocked by lazy {
            findMethod(C_AK_GAME_FACTORY) {
                name == "isUnlocked"
            }
        }

        fun isGameUnlocked(): Boolean {
            return mIsGameUnlocked.invoke(sInstance) as Boolean
        }

        private val mSetGameLocked by lazy {
            findMethod(C_AK_GAME_FACTORY) {
                name == "lockGame"
            }
        }

        fun setGameLocked() {
            mSetGameLocked.invoke(sInstance)
        }


        private val mSetGameUnlocked by lazy {
            findMethod(C_AK_GAME_FACTORY) {
                name == "unlockGame"
            }
        }

        fun setGameUnlocked() {
            mSetGameUnlocked.invoke(sInstance)
        }

        private val mAddOneWonGame by lazy {
            findMethod(C_AK_GAME_FACTORY) {
                name == "addOneWonGame"
            }
        }

        fun addOneWonGame() {
            mAddOneWonGame.invoke(sInstance)
        }

        private val mAddOneLostGame by lazy {
            findMethod(C_AK_GAME_FACTORY) {
                name == "addOneLostGame"
            }
        }

        fun addOneLostGame() {
            mAddOneLostGame.invoke(sInstance)
        }
    }

    var isGameUnlocked: Boolean
        set(value) {
            if (value) AKGameFactory.setGameUnlocked()
            else AKGameFactory.setGameLocked()
        }
        get() = AKGameFactory.isGameUnlocked()

}