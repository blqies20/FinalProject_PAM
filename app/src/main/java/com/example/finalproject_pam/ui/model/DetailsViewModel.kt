package com.example.finalproject_pam.ui.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject_pam.repository.RepositoriHewan
import com.example.finalproject_pam.ui.screen.DetailsDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailsViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoriHewan: RepositoriHewan
) : ViewModel() {

    private val hewanId: Int = checkNotNull(savedStateHandle[DetailsDestination.hewanIdArg])
    val uiState: StateFlow<ItemDetailsUiState> =
        repositoriHewan.getHewanStream(hewanId)
            .filterNotNull()
            .map { ItemDetailsUiState(detailHewan = it.toDetailHewan()) }
            .stateIn(
            scope = viewModelScope,started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ItemDetailsUiState()
        )

    suspend fun deleteItem(){
        repositoriHewan.deleteHewan(uiState.value.detailHewan.toHewan())
    }

    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ItemDetailsUiState(
    val OutOfStock: Boolean = true,
    val detailHewan: DetailHewan = DetailHewan()
)