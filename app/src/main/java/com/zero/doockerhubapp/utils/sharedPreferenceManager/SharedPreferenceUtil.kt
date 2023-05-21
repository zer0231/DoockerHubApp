package com.zero.doockerhubapp.utils.sharedPreferenceManager

import android.content.Context
import android.content.SharedPreferences
import com.zero.doockerhubapp.utils.Constants.Companion.PERSONAL_TOKEN_SP
import com.zero.doockerhubapp.utils.Constants.Companion.TOKEN_SP
import com.zero.doockerhubapp.utils.Constants.Companion.USERNAME_SP
import com.zero.doockerhubapp.utils.Constants.Companion.USER_DETAIL
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceUtil @Inject constructor(@ApplicationContext private val context: Context) {

    private val userSharedPreference =
        context.getSharedPreferences(USER_DETAIL, Context.MODE_PRIVATE)

    fun getSharedPreference():SharedPreferences{
        return context.getSharedPreferences(USER_DETAIL,Context.MODE_PRIVATE)
    }


    fun setUserName(username: String) {
        val editor = userSharedPreference.edit()
        editor.putString(USERNAME_SP, "N/A")
        editor.apply()
    }

    fun getUserName(): String? {
        return userSharedPreference.getString(USERNAME_SP, "N/A")
    }

    fun setUserToken(token: String) {
        val editor = userSharedPreference.edit()
        editor.putString(TOKEN_SP, token)
        editor.apply()
    }

    fun getUserToken(): String? {
        return userSharedPreference.getString(TOKEN_SP, "*****")
    }

    fun setPersonalToken(personalToken: String) {
        val editor = userSharedPreference.edit()
        editor.putString(PERSONAL_TOKEN_SP, "N/A")
        editor.apply()
    }

    fun getPersonalToken(): String? {
        return userSharedPreference.getString(PERSONAL_TOKEN_SP, "******")
    }
}