package com.example.finalproject_pam.ui.model

import android.text.Spannable.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalproject_pam.AplikasiHewan

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(aplikasiHewan().container.repositoriHewan)
        }

        initializer {
            AddViewModel(aplikasiHewan().container.repositoriHewan)
        }

        initializer {
            DetailsViewModel(
                createSavedStateHandle(),
                aplikasiHewan().container.repositoriHewan
            )
        }

        initializer {
            EditViewModel(
                createSavedStateHandle(),
                aplikasiHewan().container.repositoriHewan
            )
        }
    }
}

fun CreationExtras.aplikasiHewan():AplikasiHewan =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiHewan)