package com.example.finalproject_pam.ui.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject_pam.R
import com.example.finalproject_pam.data.Hewan
import com.example.finalproject_pam.navigasi.DestinasiNavigasi
import com.example.finalproject_pam.navigasi.HewanTopAppbar
import com.example.finalproject_pam.ui.model.DetailsViewModel
import com.example.finalproject_pam.ui.model.ItemDetailsUiState
import com.example.finalproject_pam.ui.model.PenyediaViewModel
import com.example.finalproject_pam.ui.model.toHewan
import kotlinx.coroutines.launch

object DetailsDestination : DestinasiNavigasi {
    override val route = "item_details"
    override val titleRes = R.string.detail
    const val hewanIdArg = "itemId"
    val routeWithArgs = "$route/{$hewanIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navigateToEditItem: (Int) -> Unit,
    navigateToBuyItem: () -> Unit,
    navigateBack: () -> Unit,
    navigateToLogout: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            HewanTopAppbar(
                title = stringResource(id = DetailsDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack,
                onLogoutClick = navigateToLogout
            )
        },floatingActionButton = {
            FloatingActionButton(
                onClick =  navigateToBuyItem ,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Buying"
                )
            }
        } ,modifier = modifier
    )
    { innerPadding ->
        ItemDetailsBody(
            itemDetailsUiState = uiState.value,
            onDelete = {
                coroutineScope.launch {
                    viewModel.deleteItem()
                    navigateBack()
                }
            },
            onEdit = {
                navigateToEditItem(uiState.value.detailHewan.id)
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
private fun ItemDetailsBody(
    itemDetailsUiState: ItemDetailsUiState,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
        ItemDetails(
            hewan = itemDetailsUiState.detailHewan.toHewan(),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedButton(
            onClick = onEdit, // Menggunakan onEdit
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.edit),
                fontWeight = FontWeight.Bold)
        }

        OutlinedButton(
            onClick = {deleteConfirmationRequired = true},
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text(
                text = stringResource(id = R.string.delete),
                color = Color.Red,
                fontWeight = FontWeight.Bold)
        }

        if (deleteConfirmationRequired){
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    deleteConfirmationRequired = false
                    onDelete() },
                onDeleteCancel = { deleteConfirmationRequired = false },
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)))
        }
    }
}

@Composable
private fun ItemDetails(
    hewan: Hewan, modifier: Modifier = Modifier
){
    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            ItemDetailsRow(
                labelResID = R.string.jenis,
                itemDetail = hewan.JenisHewan,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_medium)
                )
            )
            ItemDetailsRow(
                labelResID = R.string.jk,
                itemDetail = hewan.JenisKelamin,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_medium)
                )
            )
            ItemDetailsRow(
                labelResID = R.string.age,
                itemDetail = hewan.Usia,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_medium)
                )
            )
            ItemDetailsRow(
                labelResID = R.string.price,
                itemDetail = hewan.Harga,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_medium)
                )
            )

        }
    }
}

@Composable
private fun ItemDetailsRow(
    @StringRes labelResID: Int, itemDetail: String, modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
    ) {
        Text(text = stringResource(id = labelResID))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = itemDetail, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun DeleteConfirmationDialog(

    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = {  },
        title = { Text(text = stringResource(id = R.string.attention))},
        modifier = modifier,
        dismissButton = {
                        TextButton(onClick = onDeleteCancel) {
                            Text(text = stringResource(id = R.string.no))
                        }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = stringResource(id = R.string.yes))
            }
        })
}