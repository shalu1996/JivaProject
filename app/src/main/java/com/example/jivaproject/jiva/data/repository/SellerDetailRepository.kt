package com.example.jivaproject.jiva.data.repository

import androidx.lifecycle.LiveData
import com.example.jivaproject.jiva.data.model.SellerTable
import com.example.jivaproject.jiva.data.model.VillageTable
import kotlinx.coroutines.flow.Flow

interface SellerDetailRepository {
    suspend fun getSellerDetailByName(name:String): SellerTable?
    suspend fun getSellerDetailByLoyalty(loyalty:String): SellerTable?
    suspend fun getVillages(): Flow<MutableList<VillageTable>>
    suspend fun insertVillage(villageTable: List<VillageTable>)
    suspend fun insertSeller(sellerTable: SellerTable)
    suspend fun checkUserExist(name: String): Int
}