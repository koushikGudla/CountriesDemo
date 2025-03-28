package com.example.countriesdemo.data.remote

import com.example.countriesdemo.data.remote.dto.CountryDto
import retrofit2.http.GET

interface CountryApi {
    @GET("countries.json")
    suspend fun getCountries(): List<CountryDto>
}