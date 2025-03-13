package com.example.countriesdemo.data.repo

import com.example.countriesdemo.common.Resource
import com.example.countriesdemo.data.remote.CountryApi
import com.example.countriesdemo.domain.models.Country
import com.example.countriesdemo.domain.repo.CountryRepo

class CountryRepoImpl(
    private val countryApi: CountryApi
): CountryRepo {
    override suspend fun getCountries(): Resource<List<Country>> {
        TODO("Not yet implemented")
    }

}