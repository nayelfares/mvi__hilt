package com.izadinia.mviwithhilt.di

import com.izadinia.mviwithhilt.network.IHttpCommands
import com.izadinia.mviwithhilt.network.NetworkMapper
import com.izadinia.mviwithhilt.repository.MainRepo
import com.izadinia.mviwithhilt.room.BlogDao
import com.izadinia.mviwithhilt.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepoModule {
    @Singleton
    @Provides
    fun provideMainRepo(
        blogDao: BlogDao,
        blogHttp: IHttpCommands,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepo {
        return MainRepo(
            blogDao,
            blogHttp,
            cacheMapper,
            networkMapper
        )
    }
}