package com.izadinia.mviwithhilt.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * this class is used to retrieve or insert data entities from or into
 * the database respectively. the difference between this class and
 * [com.izadinia.mviwithhilt.network.BlogNetworkEntity] is that this class is
 * for usages by database (Room) instead of network (retrofit)
 */
@Entity(tableName = "blogs")
data class BlogCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "body")
    var body: String,
    @ColumnInfo(name = "image")
    var image: String,
    @ColumnInfo(name = "category")
    var category: String,

    )