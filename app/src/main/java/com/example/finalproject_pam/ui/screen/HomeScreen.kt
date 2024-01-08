package com.example.finalproject_pam.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart


import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import com.example.finalproject_pam.R
import com.example.finalproject_pam.data.Hewan

@Composable
fun BodyScreen(
    itemHewan: List<Hewan>,
    modifier: Modifier=Modifier,
    onHewanClick: (Int) -> Unit = {}
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemHewan.isEmpty()){
            Text(
                text = stringResource(id = R.string.required),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        }else{
            ListHewan(
                itemHewan = itemHewan,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                onItemClick = {onHewanClick(it.id)}
            )
        }
    }
}

@Composable
fun ListHewan(
    itemHewan : List<Hewan>,
    modifier: Modifier = Modifier,
    onItemClick: (Hewan) -> Unit
){
    LazyColumn(modifier = modifier){
        items(items = itemHewan, key = {it.id}){
                person ->
            DataHewan(
                hewan = person,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)).clickable{onItemClick(person)})
        }
    }
}

@Composable
fun DataHewan(
    hewan: Hewan,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.pets),
                    contentDescription = "jenis",
                    modifier = Modifier.size(10.dp).weight(0.2f)
                )
                Text(
                    text = hewan.JenisHewan,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.gender),
                    contentDescription = "gender",
                    modifier = Modifier.size(10.dp).weight(0.2f)
                )
                Text(
                    text = hewan.JenisKelamin,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "usia",
                    modifier = Modifier.size(10.dp).weight(0.2f)
                )
                Text(
                    text = hewan.Usia,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Harga",
                    modifier = Modifier.size(10.dp).weight(0.2f)
                )
                Text(
                    text = hewan.Harga,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            
        }
    }
}