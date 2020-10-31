package com.izadinia.mviwithhilt.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.izadinia.mviwithhilt.model.Blog
import com.izadinia.mviwithhilt.network.BlogNetworkEntity
import com.izadinia.mviwithhilt.network.IHttpCommands
import com.izadinia.mviwithhilt.network.NetworkMapper
import com.izadinia.mviwithhilt.util.EntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * dependency injection module for all the network stuff
 */
@Module
@InstallIn(ApplicationComponent::class)
object MyNetworkModule {

    @Singleton
    @Provides
    fun provideGsonBuilder():Gson{
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("https://open-api.xyz/placeholder/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun ProvideHttpService(retrofit: Retrofit.Builder): IHttpCommands{
        return retrofit.build()
            .create(IHttpCommands::class.java)
    }
}