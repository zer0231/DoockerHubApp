package com.zero.doockerhubapp.utils

class Constants {

    companion object {
        const val BASE_URL = "https://hub.docker.com/v2/"
        const val LOGIN = "users/login"
        const val PERSONAL_ACCESS_TOKEN = "access-tokens"
        const val REPO_DETAIL = "/repositories"  //username/repositories
        const val IMAGE_DETAIL = "/images-summary" //username/repositories/repo_name/images-summary

        /**--SHARED PREFERENCE CONSTANTS--**/

        const val USER_DETAIL = "userDetail"
        const val USERNAME_SP = "username"
        const val TOKEN_SP = "token"
        const val PERSONAL_TOKEN_SP = "personalToken"
        const val PERSONAL_TOKEN_LABEL_SP = "personalTokenLabel"
        const val UUID_SP = "uuid"
    }
}