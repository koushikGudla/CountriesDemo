package com.example.countriesdemo.data.remote.dto

data class CountryDto(
    val capital: String,
    val code: String,
    val currency: Currency,
    val flag: String,
    val language: Language,
    val name: String,
    val region: String
)