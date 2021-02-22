package com.izadinia.mviwithhilt.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.izadinia.mviwithhilt.network.OnboardingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder{
        val logging =  HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.NONE
        val httpClient =  OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(300, TimeUnit.SECONDS)
        httpClient.readTimeout(300, TimeUnit.SECONDS)
        return Retrofit.Builder()
            .baseUrl("https://live.shaadoow.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
    }

    @Singleton
    @Provides
    fun provideOnboardingApi(retrofit: Retrofit.Builder): OnboardingApi{
        return retrofit.build()
            .create(OnboardingApi::class.java)
    }
}