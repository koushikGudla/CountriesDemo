package com.example.countriesdemo.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesdemo.common.Resource
import com.example.countriesdemo.domain.repo.CountryRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountryListViewModel(
    private val countryRepo: CountryRepo
): ViewModel() {

    private val _state = MutableStateFlow(ViewState())
    val state = _state
        .onStart { fetchCountriesList() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            ViewState()
        )

    private fun fetchCountriesList() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            when (val result = countryRepo.getCountries()) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message ?: "Error"
                        )
                    }
                }
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            countries = result.data ?: emptyList()
                        )
                    }
                }
            }
        }
    }
}