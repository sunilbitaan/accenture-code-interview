package com.example.accenturecodeinterview.presentation

import com.example.accenturecodeinterview.domian.EmployerData

/**
 * Create By Sunil
 */
data class EmployerUiState(
    val employers: List<EmployerData> = emptyList(),
    val error: String? = ""
)
