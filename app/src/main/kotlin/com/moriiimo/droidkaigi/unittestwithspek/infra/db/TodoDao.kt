package com.moriiimo.droidkaigi.unittestwithspek.infra.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface TodoDao {

  @Query("SELECT * FROM todo")
  fun selectAll(): Single<List<TodoEntity>>

  @Query("SELECT * FROM todo WHERE id = :id")
  fun selectById(id: Int): Single<TodoEntity>

  @Query("SELECT * FROM todo WHERE name LIKE :word")
  fun selectAllByWord(word: String): Single<List<TodoEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun upsertAll(list: List<TodoEntity>)

  @Query("SELECT COUNT(*) FROM todo")
  fun count(): Single<Int>
}