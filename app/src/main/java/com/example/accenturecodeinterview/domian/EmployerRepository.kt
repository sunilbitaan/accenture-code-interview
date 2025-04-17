package com.example.accenturecodeinterview.domian

import com.example.accenturecodeinterview.utils.UseCaseResult

/**
 * Create By Sunil
 */
interface EmployerRepository {
    suspend fun getEmployerData(query: String, offset: Int): UseCaseResult<List<EmployerData>>
}