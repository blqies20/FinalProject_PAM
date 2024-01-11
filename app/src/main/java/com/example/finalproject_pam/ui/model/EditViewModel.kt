package com.example.finalproject_pam.ui.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject_pam.repository.RepositoriHewan
import com.example.finalproject_pam.ui.screen.ItemEditDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriHewan: RepositoriHewan
) : ViewModel() {

    var hewanUiState by mutableStateOf(UIStateHewan())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArgs])

    init {
        viewModelScope.launch {
            hewanUiState = repositoriHewan.getHewanStream(itemId).filterNotNull().first().toUiStateHewan(true)
        }
    }

    suspend fun updateHewan() {
        if (validasiInput(hewanUiState.detailHewan)) {
            repositoriHewan.updateHewan(hewanUiState.detailHewan.toHewan())
        }
        else {
            println("Data tidak valid")
        }
    }

    fun updateUiState(detailHewan: DetailHewan) {
        hewanUiState =
            UIStateHewan(detailHewan = detailHewan, isAddValid = validasiInput(detailHewan))
    }

    private fun validasiInput(uiState: DetailHewan = hewanUiState.detailHewan): Boolean {
        return with(uiState) {
            JenisHewan.isNotBlank() && gender.isNotBlank() && age.isNotBlank() && price.isNotBlank()
        }
    }
}