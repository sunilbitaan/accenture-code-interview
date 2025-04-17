package com.example.accenturecodeinterview.data

import com.example.accenturecodeinterview.domian.EmployerData
import com.example.accenturecodeinterview.domian.EmployerRepository
import com.example.accenturecodeinterview.utils.UseCaseResult

/**
 * Create By Sunil
 */
class EmployerRepositoryImpl(private val api: EmployerApiInterface) : EmployerRepository {

    override suspend fun getEmployerData(
        query: String,
        offset: Int
    ): UseCaseResult<List<EmployerData>> {
        return try {
            val result = api.getEmployerData(query, offset).await()
            UseCaseResult.Success(result)

        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }
}