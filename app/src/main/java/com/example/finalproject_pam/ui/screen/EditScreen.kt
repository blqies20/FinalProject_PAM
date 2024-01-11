package com.example.finalproject_pam.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject_pam.R
import com.example.finalproject_pam.navigasi.DestinasiNavigasi
import com.example.finalproject_pam.navigasi.HewanTopAppbar
import com.example.finalproject_pam.ui.model.EditViewModel
import com.example.finalproject_pam.ui.model.PenyediaViewModel
import kotlinx.coroutines.launch
import java.security.KeyStore.Entry

object ItemEditDestination : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.edit
    const val itemIdArgs = "itemId"
    val routeWithArgs = "$route/{$itemIdArgs}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    navigateToLogout: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            HewanTopAppbar(
                title = stringResource(id = ItemEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp,
                onLogoutClick = navigateToLogout
            )
        },
        modifier = modifier
    ) {innerPadding ->
        AddBody(
            uiStateHewan = viewModel.hewanUiState,
            onHewanValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateHewan()
                    navigateBack()
                } },
            modifier = Modifier.padding(innerPadding)
        )
    }
}