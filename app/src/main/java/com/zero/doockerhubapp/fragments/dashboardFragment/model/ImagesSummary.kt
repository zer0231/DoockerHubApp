package com.zero.doockerhubapp.fragments.dashboardFragment.model

data class ImagesSummary(
    val active_from:String,
    val statistics:Stat
)

data class Stat(
    val total:Int,
    val active:Int,
    val inactive:Int
)

