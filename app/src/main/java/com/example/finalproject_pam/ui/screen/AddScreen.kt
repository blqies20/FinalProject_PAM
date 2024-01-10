package com.example.finalproject_pam.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject_pam.R
import com.example.finalproject_pam.navigasi.DestinasiNavigasi
import com.example.finalproject_pam.navigasi.HewanTopAppbar
import com.example.finalproject_pam.ui.model.AddViewModel
import com.example.finalproject_pam.ui.model.DetailHewan
import com.example.finalproject_pam.ui.model.PenyediaViewModel
import com.example.finalproject_pam.ui.model.UIStateHewan
import kotlinx.coroutines.launch

object DestinasiAdd: DestinasiNavigasi {
    override val route = "item_Add"
    override val titleRes = R.string.add_hewan
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HewanTopAppbar(
                title = stringResource(DestinasiAdd.titleRes),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior
            )
        }
    ) {innerPadding ->
        AddBody(
            uiStateHewan = viewModel.uiStateHewan,
            onHewanValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveHewan()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()).fillMaxWidth()
        )
    }
}
@Composable
fun AddBody(
    uiStateHewan: UIStateHewan,
    onHewanValueChange: (DetailHewan) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        AddForm(
            detailHewan = uiStateHewan.detailHewan,
            onValueChange = onHewanValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = uiStateHewan.isAddValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "submit")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddForm(
    detailHewan: DetailHewan,
    modifier: Modifier = Modifier,
    onValueChange: (DetailHewan) -> Unit = {},
    enabled: Boolean = true
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = detailHewan.JnisHewan,
            onValueChange = {onValueChange(detailHewan.copy(JnisHewan = it)) },
            label = { Text(text = "Jenis Hewan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailHewan.gender,
            onValueChange = { onValueChange(detailHewan.copy(gender = it)) },
            label = { Text(text = "Jenis Kelamin") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailHewan.age,
            onValueChange = { onValueChange(detailHewan.copy(age = it)) },
            label = { Text(text = "Usia") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailHewan.price,
            onValueChange = { onValueChange(detailHewan.copy(price = it)) },
            label = { Text(text = "Harga") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        if (enabled){
            Text(
                text = "Harus di isi",
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }
        Divider(
            thickness = dimensionResource(id = R.dimen.smal),
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_medium))
        )
    }
}