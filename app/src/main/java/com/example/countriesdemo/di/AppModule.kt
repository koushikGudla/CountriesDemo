package com.example.countriesdemo.di

import android.content.Context
import com.example.countriesdemo.common.Constants
import com.example.countriesdemo.data.remote.CountryApi
import com.example.countriesdemo.data.repo.CountryRepoImpl
import com.example.countriesdemo.domain.repo.CountryRepo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

interface AppModule {
    val countryApi: CountryApi
    val countryRepo: CountryRepo
}

class AppModuleImpl(
    private val appContext: Context
): AppModule {
    override val countryApi: CountryApi by lazy {
        Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    override val countryRepo: CountryRepo by lazy {
        CountryRepoImpl(countryApi)
    }

}