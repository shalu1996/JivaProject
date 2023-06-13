package com.example.jivaproject.jiva.domain.di

import com.example.jivaproject.db.AppDatabase
import com.example.jivaproject.jiva.data.repository.SellerDetailRepository
import com.example.jivaproject.jiva.domain.repoimpl.SellerDetailRepoImpl
import com.example.jivaproject.jiva.domain.usecase.SellerDetailUcImpl
import com.example.jivaproject.jiva.domain.usecase.SellerDetailUseCase
import com.example.jivaproject.jiva.domain.usecase.VillageUcImpl
import com.example.jivaproject.jiva.domain.usecase.VillageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SellerDetailModule {
    @Singleton
    @Provides
    fun provideSellerDao(db: AppDatabase) = db.getSellerDao()

    @Singleton
    @Provides
    fun provideSellerRepository(
        appDatabase: AppDatabase
    ): SellerDetailRepository = SellerDetailRepoImpl(appDatabase)

    @Singleton
    @Provides
    fun provideSellerUcImpl(sellerDetailRepository: SellerDetailRepository):
            SellerDetailUseCase = SellerDetailUcImpl(sellerDetailRepository)

    @Singleton
    @Provides
    fun provideVillageUcImpl(sellerDetailRepository: SellerDetailRepository):
            VillageUseCase = VillageUcImpl(sellerDetailRepository)
}
