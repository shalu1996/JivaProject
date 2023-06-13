package com.example.jivaproject.jiva.domain.usecase

import com.example.jivaproject.jiva.data.model.VillageTable
import com.example.jivaproject.jiva.data.repository.SellerDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VillageUcImpl @Inject constructor(private val sellerDetailRepository: SellerDetailRepository) :
    VillageUseCase {
    override suspend fun getVillages(): Flow<MutableList<VillageTable>> {
        return sellerDetailRepository.getVillages()
    }

    override suspend fun insertVillages(villageTable: List<VillageTable>) {
        return sellerDetailRepository.insertVillage(villageTable)
    }
}