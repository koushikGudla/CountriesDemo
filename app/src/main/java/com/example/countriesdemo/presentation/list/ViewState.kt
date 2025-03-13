package com.example.countriesdemo.presentation.list

import com.example.countriesdemo.domain.models.Country

data class ViewState(
    val isLoading: Boolean = false,
    val countries: List<Country> = emptyList(),
    val error: String = ""
)
