package com.izadinia.mviwithhilt.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BlogCacheEntity::class], version = 1)
abstract class BlogDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "blog_db"
    }

    abstract fun getDAO(): BlogDao
}