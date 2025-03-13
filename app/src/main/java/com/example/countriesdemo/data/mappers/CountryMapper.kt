package com.example.countriesdemo.data.mappers

import com.example.countriesdemo.data.remote.dto.CountryDto
import com.example.countriesdemo.domain.models.Country

fun CountryDto.toCountry() = Country(
    name = name,
    region = region,
    capital = capital,
    code = code
)