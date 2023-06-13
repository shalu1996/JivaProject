package com.example.jivaproject.jiva.domain.usecase

import com.example.jivaproject.jiva.data.model.VillageTable
import kotlinx.coroutines.flow.Flow

interface VillageUseCase {
    suspend fun getVillages(): Flow<MutableList<VillageTable>>
    suspend fun insertVillages(villageTable: List<VillageTable>)
}