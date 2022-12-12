package me.dio.vinicius.businesscard.data

import androidx.lifecycle.LiveData
import androidx.room.*
import me.dio.vinicius.businesscard.domain.BusinessCard

@Dao
interface BusinessCardDao {

    @Query("SELECT * FROM BusinessCard")
    fun getAll(): LiveData<List<BusinessCard>>

    @Query("SELECT * FROM BusinessCard WHERE id = :id")
    fun getById(id: Int) : LiveData<BusinessCard>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(newCard: BusinessCard)

    @Delete
    suspend fun delete(card: BusinessCard)

    @Update
    suspend fun update(card: BusinessCard)
}