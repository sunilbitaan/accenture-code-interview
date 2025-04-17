package com.example.accenturecodeinterview.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.accenturecodeinterview.domian.EmployerData
import com.example.accenturecodeinterview.domian.GetEmployerUseCase
import com.example.accenturecodeinterview.presentation.EmployerUiState
import com.example.accenturecodeinterview.utils.UseCaseResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Create By Sunil
 */
class EmployerConfigViewModel(private val getEmployerUseCase: GetEmployerUseCase) : ViewModel() {
    private val employerListUIState = MutableStateFlow(EmployerUiState())
    val uiState: StateFlow<EmployerUiState> = employerListUIState

    fun searchEmployer(query: String, limit: Int) {
        viewModelScope.launch {
            employerListUIState.value = employerListUIState.value.copy(error = null)
            when (val result = getEmployerUseCase(query, limit)) {
                is UseCaseResult.Success -> {
                    employerListUIState.value = EmployerUiState(
                        employers = result.data,
                    )
                }

                is UseCaseResult.Error -> {
                    employerListUIState.value = EmployerUiState(
                        error = result.exception.message ?: "Unknown error",
                    )
                }
            }
        }
    }
    fun getEmployerData(employerId: Int): EmployerData {
        return employerListUIState.value.employers.find { it.EmployerID == employerId }
            ?: throw IllegalStateException("Employer with ID $employerId not found")
    }
}