package com.zero.doockerhubapp.fragments.dashboardFragment.model

import com.google.gson.annotations.SerializedName

data class RepoSummary(
    @SerializedName("count") var count: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("previous") var previous: String? = null,
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf()
)

data class Results(
    @SerializedName("name") var name: String? = null,
    @SerializedName("namespace") var namespace: String? = null,
    @SerializedName("repository_type") var repositoryType: String? = null,
    @SerializedName("status") var status: Int? = null,
    @SerializedName("status_description") var statusDescription: String? = "N/a",
    @SerializedName("description") var description: String? = "N/A",
    @SerializedName("is_private") var isPrivate: Boolean? = null,
    @SerializedName("star_count") var starCount: Int? = 0,
    @SerializedName("pull_count") var pullCount: Int? = 0,
    @SerializedName("last_updated") var lastUpdated: String? = null,
    @SerializedName("date_registered") var dateRegistered: String? = null,
    @SerializedName("affiliation") var affiliation: String? = null,
    @SerializedName("media_types") var mediaTypes: ArrayList<String> = arrayListOf(),
    @SerializedName("content_types") var contentTypes: ArrayList<String> = arrayListOf()
)