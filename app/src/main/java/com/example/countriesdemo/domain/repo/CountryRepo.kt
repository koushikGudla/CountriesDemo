package com.example.countriesdemo.domain.repo

import com.example.countriesdemo.common.Resource
import com.example.countriesdemo.domain.models.Country

interface CountryRepo {
    suspend fun getCountries(): Resource<List<Country>>
}