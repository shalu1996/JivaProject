package com.example.jivaproject.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.jivaproject.jiva.data.model.SellerTable
import com.example.jivaproject.jiva.data.model.VillageTable
import com.example.jivaproject.jiva.domain.usecase.SellerDetailUseCase
import com.example.jivaproject.jiva.domain.usecase.VillageUseCase
import com.example.jivaproject.jiva.ui.viewmodel.SellerViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.*
import org.mockito.Mockito.*
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class SellerViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = TestCoroutineScope()

    private lateinit var viewModel: SellerViewModel
    private lateinit var sellerDetailUseCase: SellerDetailUseCase
    private lateinit var villageUseCase: VillageUseCase
    private lateinit var villagesObserver: Observer<MutableList<VillageTable>>

    @Before
    fun setup() {
        sellerDetailUseCase = mock(SellerDetailUseCase::class.java)
        villageUseCase = mock(VillageUseCase::class.java)
        viewModel = SellerViewModel(sellerDetailUseCase, villageUseCase)
        villagesObserver = mock(Observer::class.java) as Observer<MutableList<VillageTable>>
    }

    @After
    fun teardown() {
        // Cleanup
        coroutineScope.cleanupTestCoroutines()
    }

    @Test
    fun `testGetVillages`() = runBlocking {
        // Arrange
        val mockVillages = mutableListOf<VillageTable>()
        `when`(villageUseCase.getVillages()).thenReturn(flowOf(mockVillages))

        // Act
        viewModel.villages.observeForever(villagesObserver)
        viewModel.getVillages()

        // Assert
        verify(villageUseCase).getVillages()
        verify(villagesObserver).onChanged(mockVillages)
    }

    @Test
    fun `testInsertVillage`() = runBlocking {
        // Arrange
        val mockVillages = mutableListOf<VillageTable>()
        val mockVillageTable = VillageTable("Test Village", 100.0)
        mockVillages.add(mockVillageTable)
        `when`(villageUseCase.insertVillages(mockVillages)).thenReturn(Unit)

        // Act
        viewModel.insertVillage()

        // Assert
        verify(villageUseCase).insertVillages(mockVillages)
    }

//    @Test
//    fun `testAddSeller`() = runBlocking {
//        // Arrange
//        val mockSellerTable = listOf(SellerTable("Test Seller", "S12345", "Ramnagar"))
//        `when`(sellerDetailUseCase.addSeller(mockSellerTable)).thenReturn(Unit)
//
//        // Act
//        viewModel.addSeller(mockSellerTable)
//
//        // Assert
//        verify(sellerDetailUseCase, times(2)).addSeller(mockSellerTable)
//    }

    @Test
    fun `testGetSellerByName`() = runBlocking {
        // Arrange
        val sellerName = "Test Seller"
        val mockSellerTable = SellerTable(sellerName,"S12345","Ramnagar")
        `when`(sellerDetailUseCase.getSellerDetailByName(sellerName)).thenReturn(mockSellerTable)

        // Act
        val result = viewModel.getSellerByName(sellerName)

        // Assert
        verify(sellerDetailUseCase).getSellerDetailByName(sellerName)
        Assert.assertEquals(mockSellerTable, result)
    }

    @Test
    fun `testGetSellerByLoyalty`() = runBlocking {
        // Arrange
        val loyaltyCardNo = "123456789"
        val mockSellerTable = SellerTable("Test Seller","S12345","Ramnagar")
        `when`(sellerDetailUseCase.getSellerDetailByLoyalty(loyaltyCardNo)).thenReturn(
            mockSellerTable
        )

        // Act
        val result = viewModel.getSellerByLoyalty(loyaltyCardNo)

        // Assert
        verify(sellerDetailUseCase).getSellerDetailByLoyalty(loyaltyCardNo)
        Assert.assertEquals(mockSellerTable, result)
    }

    @Test
    fun `testCheckUserExist`() = runBlocking {
        // Arrange
        val sellerName = "Test Seller"
        val mockCount = 1
        `when`(sellerDetailUseCase.checkUserExist(sellerName)).thenReturn(mockCount)

        // Act
        val result = viewModel.checkUserExist(sellerName)

        // Assert
        verify(sellerDetailUseCase).checkUserExist(sellerName)
        Assert.assertTrue(result)
    }
}
