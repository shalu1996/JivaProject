package com.example.jivaproject.jiva.domain.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jivaproject.jiva.data.model.SellerTable
import com.example.jivaproject.jiva.data.model.VillageTable
import kotlinx.coroutines.flow.Flow

@Dao
interface SellerDao {
    @Query("SELECT * FROM sellerTable WHERE name = :name")
    suspend fun getSellerByName(name: String): SellerTable?

    @Query("SELECT * FROM sellerTable WHERE loyaltyCardNumber = :loyalty")
    suspend fun getSellerByLoyalty(loyalty: String): SellerTable?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeller(sellerTable: SellerTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVillages(villages: List<VillageTable>)

    @Query("SELECT * FROM villageTable")
    fun getVillages(): Flow<MutableList<VillageTable>>

    @Query("SELECT COUNT() FROM sellerTable WHERE name = :name")
    suspend fun count(name: String): Int
}