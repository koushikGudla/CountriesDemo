package com.example.countriesdemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.countriesdemo.databinding.ActivityMainBinding
import com.example.countriesdemo.presentation.list.CountriesAdapter
import com.example.countriesdemo.presentation.list.CountryListViewModel
import com.example.countriesdemo.presentation.viewModelFactory
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            ViewCompat.setOnApplyWindowInsetsListener(main) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            val countriesAdapter by lazy { CountriesAdapter() }
            val viewModel: CountryListViewModel by viewModels {
                viewModelFactory { CountryListViewModel(CountryApp.appModule.countryRepo) }
            }

            countriesList.adapter = countriesAdapter

            viewModel.state.flowWithLifecycle(lifecycle)
                .onEach { viewState ->
                    loadingIndicator.isVisible = viewState.isLoading
                    countriesList.isVisible = viewState.countries.isNotEmpty()
                    countriesAdapter.submitList(viewState.countries)
                    errorText.isVisible = viewState.error.isNotBlank()
                    errorText.text = viewState.error
                }.launchIn(lifecycleScope)

        }.also { setContentView(it.root) }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}