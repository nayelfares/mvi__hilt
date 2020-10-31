package com.izadinia.mviwithhilt.network

import retrofit2.http.GET

interface IHttpCommands {
    @GET("blogs")
    suspend fun get() : List<BlogNetworkEntity>
}