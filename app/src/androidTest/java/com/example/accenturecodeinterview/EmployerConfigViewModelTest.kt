package com.example.accenturecodeinterview

import com.example.accenturecodeinterview.domian.EmployerData
import com.example.accenturecodeinterview.domian.GetEmployerUseCase
import com.example.accenturecodeinterview.presentation.viewmodel.EmployerConfigViewModel
import com.example.accenturecodeinterview.utils.UseCaseResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
class EmployerConfigViewModelTest {
    private lateinit var getEmployerUseCase: GetEmployerUseCase
    private lateinit var viewModel: EmployerConfigViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getEmployerUseCase = mockk<GetEmployerUseCase>()
        viewModel = EmployerConfigViewModel(getEmployerUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun searchEmployerEmitSuccessWIthData() = runTest {
        val employers = listOf(
            EmployerData(
                EmployerID = 1,
                Name = "Employer A",
                Place = "abc",
                DiscountPercentage = 10
            ),
            EmployerData(EmployerID = 2, Name = "Employer B", Place = "xyz", DiscountPercentage = 0)
        )
        coEvery { getEmployerUseCase("Employer", 2) } returns UseCaseResult.Success(employers)

        viewModel.searchEmployer("Employer", 2)
        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertEquals(employers, uiState.employers)
    }

    @Test
    fun searchEmployerEmitErrorWithData() = runTest {
        val errorMessage = "Network Error"
        coEvery { getEmployerUseCase("Employer", 2) } returns UseCaseResult.Error(
            Exception(
                errorMessage
            )
        )

        viewModel.searchEmployer("Employer", 2)
        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertEquals(errorMessage, uiState.error)
        assertEquals(emptyList<EmployerData>(), uiState.employers)
    }

    @Test
    fun getEmployerDataWIthCorrectValue() = runTest {
        val employers = listOf(
            EmployerData(
                EmployerID = 10,
                Name = "Test Employer",
                Place = "abc",
                DiscountPercentage = 15
            )
        )
        coEvery { getEmployerUseCase("Employer", 1) } returns UseCaseResult.Success(employers)

        viewModel.searchEmployer("Employer", 1)
        advanceUntilIdle()

        val result = viewModel.getEmployerData(10)
        assertEquals("Test Employer", result.Name)
    }
}