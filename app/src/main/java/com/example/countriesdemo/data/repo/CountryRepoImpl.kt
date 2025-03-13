package com.example.countriesdemo.data.repo

import com.example.countriesdemo.common.Resource
import com.example.countriesdemo.data.mappers.toCountry
import com.example.countriesdemo.data.remote.CountryApi
import com.example.countriesdemo.domain.models.Country
import com.example.countriesdemo.domain.repo.CountryRepo
import retrofit2.HttpException
import java.io.IOException

class CountryRepoImpl(
    private val countryApi: CountryApi
): CountryRepo {
    override suspend fun getCountries(): Resource<List<Country>> {
        return try {
            countryApi.getCountries().map { it.toCountry() }
                .let { Resource.Success(it) }
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "Unexpected Error")
        } catch (e: IOException) {
            Resource.Error("No Internet")
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Error")
        }
    }

}