package com.example.jivaproject.jiva.domain.usecase

import com.example.jivaproject.jiva.data.model.SellerTable

interface SellerDetailUseCase {
    suspend fun getSellerDetailByName(name:String): SellerTable?
    suspend fun getSellerDetailByLoyalty(loyalty:String): SellerTable?
    suspend fun addSeller(sellerTable: List<SellerTable>)
    suspend fun checkUserExist(name: String): Int
}