package com.izadinia.mviwithhilt.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * entity class specialized to be used as the data model in order to
 * retrieve data from the network and then convert it to a domain model
 * and add that models list to database.
 * for more info see [com.izadinia.mviwithhilt.room.BlogCacheEntity]
 */
data class BlogNetworkEntity(
    @SerializedName("pk")
    @Expose
    var id: Int,
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("body")
    @Expose
    var body: String,
    @SerializedName("image")
    @Expose
    var image: String,
    @SerializedName("category")
    @Expose
    var category: String,

    ) {
}