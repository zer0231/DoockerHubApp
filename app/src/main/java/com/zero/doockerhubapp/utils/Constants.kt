package com.zero.doockerhubapp.utils

class Constants {

    companion object {
        const val USERNAME = "zer0231"
        const val REPO_NAME = "test"
        const val BASE_URL = "https://hub.docker.com/v2/"
        const val LOGIN = "users/login"
        const val ACCESS_TOKEN = "access-tokens"
        const val REPO_DETAIL = "${USERNAME}/repositories"
        const val IMAGE_DETAIL = "${REPO_DETAIL}/${REPO_NAME}/images-summary"
    }
}