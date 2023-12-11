package com.daniebeler.pixels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniebeler.pixels.models.api.Post
import com.daniebeler.pixels.models.api.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CountryRepository
): ViewModel() {

    private var _countries by mutableStateOf(emptyList<Post>())

    val countries: List<Post>
        get() = _countries

    fun searchCountries(query: String) {
        viewModelScope.launch {
            _countries = repository.searchCountries()
            println("Got Data")
            println(countries)
        }
    }
}