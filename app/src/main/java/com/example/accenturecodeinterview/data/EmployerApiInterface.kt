package com.example.accenturecodeinterview.data

import com.example.accenturecodeinterview.domian.EmployerData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Create By Sunil
 */
interface EmployerApiInterface {
    @GET("employers")
    fun getEmployerData(
        @Query("filter") filter: String,
        @Query("maxRows") query: Int
    ): Deferred<List<EmployerData>>
}