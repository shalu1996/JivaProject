package com.example.jivaproject.jiva.domain.usecase

import androidx.lifecycle.LiveData
import com.example.jivaproject.jiva.data.model.SellerTable
import com.example.jivaproject.jiva.data.repository.SellerDetailRepository
import javax.inject.Inject

class SellerDetailUcImpl @Inject constructor(private val sellerDetailRepository: SellerDetailRepository) :
    SellerDetailUseCase {
    override suspend fun getSellerDetailByName(name: String): SellerTable? {
        return sellerDetailRepository.getSellerDetailByName(name)
    }

    override suspend fun getSellerDetailByLoyalty(loyalty: String): SellerTable? {
        return sellerDetailRepository.getSellerDetailByLoyalty(loyalty)
    }

    override suspend fun addSeller(sellerTable: SellerTable) {
        sellerDetailRepository.insertSeller(sellerTable)
        sellerDetailRepository.insertSeller(sellerTable)
    }

    override suspend fun checkUserExist(name: String):Int {
       return sellerDetailRepository.checkUserExist(name)
    }
}