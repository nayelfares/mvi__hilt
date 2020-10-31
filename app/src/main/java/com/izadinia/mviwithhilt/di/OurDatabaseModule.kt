package com.izadinia.mviwithhilt.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.izadinia.mviwithhilt.room.BlogDao
import com.izadinia.mviwithhilt.room.BlogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object OurDatabaseModule {
    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context):BlogDatabase{
        return Room.databaseBuilder(
            context,
            BlogDatabase::class.java,
            BlogDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(blogDatabase: BlogDatabase):BlogDao{
        return blogDatabase.getDAO()
    }
}