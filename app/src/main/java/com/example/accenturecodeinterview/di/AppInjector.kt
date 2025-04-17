package com.example.accenturecodeinterview.di

import com.example.accenturecodeinterview.BuildConfig
import com.example.accenturecodeinterview.data.EmployerApiInterface
import com.example.accenturecodeinterview.data.EmployerRepositoryImpl
import com.example.accenturecodeinterview.domian.EmployerRepository
import com.example.accenturecodeinterview.domian.GetEmployerUseCase
import com.example.accenturecodeinterview.presentation.viewmodel.EmployerConfigViewModel
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Create By Sunil
 */

val appModules = module {
    // The Retrofit service using our custom HTTP client instance as a singleton
    single {
        createWebService<EmployerApiInterface>(
            okHttpClient = createHttpClient(),
            factory = RxJava2CallAdapterFactory.create(),
            baseUrl = BuildConfig.SERVER_REMOTE_URL
        )
    }

    // Repository
    single<EmployerRepository> {
        EmployerRepositoryImpl(get()) // get() provides EmployerApiInterface
    }

    // UseCase
    factory {
        GetEmployerUseCase(get()) // get() provides EmployerRepository
    }

    // Specific viewModel pattern to tell Koin how to build EmployerConfigViewModel
    viewModel { EmployerConfigViewModel(get()) }
}

/* Returns a custom OkHttpClient instance with interceptor. Used for building Retrofit service */
fun createHttpClient(): OkHttpClient {

    /**
     * Creates client for retrofit, here you can configure different settings of retrofit manager
     * like Logging, Cache size, Decoding factories, Convertor factories etc.
     */
    val interceptor = HttpLoggingInterceptor()
    interceptor.level =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    val client = OkHttpClient.Builder()
    client.readTimeout(5 * 60, TimeUnit.SECONDS)
    client.addInterceptor(interceptor)
    return client.build()
}

/* function to build our Retrofit service */
inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    factory: CallAdapter.Factory, baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(factory)
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}