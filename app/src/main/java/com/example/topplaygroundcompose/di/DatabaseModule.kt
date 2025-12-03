package com.example.topplaygroundcompose.di

import android.content.Context
import androidx.room.Room
import com.example.topplaygroundcompose.data.local.SpaceFlightNewsDatabase
import com.example.topplaygroundcompose.data.local.dao.FavoriteArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SpaceFlightNewsDatabase {
        return Room.databaseBuilder(
            context,
            SpaceFlightNewsDatabase::class.java,
            "spaceflight_news_database"
        ).build()
    }
    
    @Provides
    fun provideFavoriteArticleDao(database: SpaceFlightNewsDatabase): FavoriteArticleDao {
        return database.favoriteArticleDao()
    }
}

