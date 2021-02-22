package com.izadinia.mviwithhilt.di

import com.izadinia.mviwithhilt.network.OnboardingApi
import com.izadinia.mviwithhilt.repository.MainRepo
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
        onboardingApi: OnboardingApi
    ): MainRepo {
        return MainRepo(
            onboardingApi
        )
    }
}