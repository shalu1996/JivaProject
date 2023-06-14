package com.example.jivaproject.jiva.domain.repoimpl

import com.example.jivaproject.jiva.data.model.SellerTable
import com.example.jivaproject.jiva.data.repository.SellerDetailRepository
import com.example.jivaproject.db.AppDatabase
import com.example.jivaproject.jiva.data.model.VillageTable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SellerDetailRepoImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : SellerDetailRepository {
    override suspend fun getSellerDetailByName(name: String): SellerTable? {
        return appDatabase.getSellerDao().getSellerByName(name)
    }

    override suspend fun getSellerDetailByLoyalty(loyalty: String): SellerTable? {
        return appDatabase.getSellerDao().getSellerByLoyalty(loyalty)
    }

    override suspend fun getVillages(): Flow<MutableList<VillageTable>> {
       return appDatabase.getSellerDao().getVillages()
    }

    override suspend fun insertVillage(villageTable: List<VillageTable>) {
        appDatabase.getSellerDao().insertVillages(villageTable)
    }

    override suspend fun insertSeller(sellerTable: List<SellerTable>) {
        appDatabase.getSellerDao().insertSeller(sellerTable)
    }

    override suspend fun checkUserExist(name: String) :Int{
        return appDatabase.getSellerDao().count(name)
    }
}