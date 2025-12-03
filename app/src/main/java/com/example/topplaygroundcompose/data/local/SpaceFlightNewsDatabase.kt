package com.example.topplaygroundcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.topplaygroundcompose.data.local.dao.FavoriteArticleDao
import com.example.topplaygroundcompose.data.local.entity.FavoriteArticleEntity

@Database(
    entities = [FavoriteArticleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SpaceFlightNewsDatabase : RoomDatabase() {
    abstract fun favoriteArticleDao(): FavoriteArticleDao
}

