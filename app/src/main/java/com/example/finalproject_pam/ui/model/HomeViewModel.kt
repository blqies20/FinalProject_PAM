package com.example.finalproject_pam.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject_pam.data.Hewan
import com.example.finalproject_pam.repository.RepositoriHewan
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel (private val repositoriHewan: RepositoriHewan): ViewModel(){

    private val _searchResults = MutableStateFlow<List<Hewan>>(emptyList())
    val searchResults: StateFlow<List<Hewan>> = _searchResults

    fun searchHewan(searchKeyword: String) {
        viewModelScope.launch {
            val results = repositoriHewan.searchHewan(searchKeyword) ?: emptyList()
            _searchResults.value = results
        }
    }

    companion object{
        private  const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUiState: StateFlow<HomeUiState> = repositoriHewan.getAllHewanStream()
        .filterNotNull()
        .map { HomeUiState(listHewan = it.toList()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )
    data class HomeUiState(
        val listHewan: List<Hewan> = listOf()
    )
}