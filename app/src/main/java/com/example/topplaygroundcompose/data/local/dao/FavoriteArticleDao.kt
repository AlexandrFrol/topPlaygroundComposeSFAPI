package com.example.topplaygroundcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.topplaygroundcompose.data.local.entity.FavoriteArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteArticleDao {
    @Query("SELECT * FROM favorite_articles ORDER BY publishedAt DESC")
    fun getAllFavorites(): Flow<List<FavoriteArticleEntity>>
    
    @Query("SELECT * FROM favorite_articles WHERE id = :id")
    suspend fun getFavoriteById(id: Int): FavoriteArticleEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(article: FavoriteArticleEntity)
    
    @Query("DELETE FROM favorite_articles WHERE id = :id")
    suspend fun deleteFavorite(id: Int)
    
    @Query("SELECT EXISTS(SELECT 1 FROM favorite_articles WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean
}

