package com.zero.doockerhubapp.utils.sharedPreferenceManager

import android.content.Context
import com.zero.doockerhubapp.utils.Constants.Companion.PERSONAL_TOKEN_LABEL_SP
import com.zero.doockerhubapp.utils.Constants.Companion.PERSONAL_TOKEN_SP
import com.zero.doockerhubapp.utils.Constants.Companion.TOKEN_SP
import com.zero.doockerhubapp.utils.Constants.Companion.USERNAME_SP
import com.zero.doockerhubapp.utils.Constants.Companion.USER_DETAIL
import com.zero.doockerhubapp.utils.Constants.Companion.UUID_SP
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceUtil @Inject constructor(@ApplicationContext private val context: Context) {

    private val userSharedPreference =
        context.getSharedPreferences(USER_DETAIL, Context.MODE_PRIVATE)


    fun setUserName(username: String) {
        val editor = userSharedPreference.edit()
        editor.putString(USERNAME_SP, username)
        editor.apply()
    }

    fun getUserName(): String? {
        return userSharedPreference.getString(USERNAME_SP, "*")
    }

    fun setUserToken(token: String) {
        val editor = userSharedPreference.edit()
        editor.putString(TOKEN_SP, token)
        editor.apply()
    }

    fun getUserToken(): String? {
        return userSharedPreference.getString(TOKEN_SP, "*")
    }

    fun setPersonalToken(personalToken: String) {
        val editor = userSharedPreference.edit()
        editor.putString(PERSONAL_TOKEN_SP, personalToken)
        editor.apply()
    }

    fun getPersonalToken(): String? {
        return userSharedPreference.getString(PERSONAL_TOKEN_SP, "*")
    }

    fun setPersonalTokenLabel(label: String) {
        val editor = userSharedPreference.edit()
        editor.putString(PERSONAL_TOKEN_LABEL_SP, label)
        editor.apply()
    }

    fun getPersonalTokenLabel(): String? {
        return userSharedPreference.getString(PERSONAL_TOKEN_LABEL_SP, "*")
    }

    fun clearUserData() {
        val editor = userSharedPreference.edit().clear()
        editor.apply()
    }
    fun getUUID(): String?{
        return userSharedPreference.getString(UUID_SP,"*")
    }

    fun setUUID(uuid:String){
        val editor = userSharedPreference.edit()
        editor.putString(UUID_SP,uuid)
        editor.apply()
    }
}