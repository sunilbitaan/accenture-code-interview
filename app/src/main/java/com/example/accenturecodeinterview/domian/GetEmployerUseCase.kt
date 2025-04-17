package com.example.accenturecodeinterview.domian

import com.example.accenturecodeinterview.utils.UseCaseResult

/**
 * Create By Sunil
 */
class GetEmployerUseCase(private val repository: EmployerRepository) {
    suspend operator fun invoke(filter: String, maxRows: Int): UseCaseResult<List<EmployerData>> {
        return repository.getEmployerData(filter, maxRows)
    }
}