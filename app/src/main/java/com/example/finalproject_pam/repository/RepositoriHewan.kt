package com.example.finalproject_pam.repository

import com.example.finalproject_pam.data.Hewan
import kotlinx.coroutines.flow.Flow

interface RepositoriHewan {

    fun getAllHewanStream(): Flow<List<Hewan>>

    fun getHewanStream(JenisHewan: String): Flow<Hewan>

    suspend fun insertHewan(hewan: Hewan)

    suspend fun deleteHewan(hewan: Hewan)

    suspend fun updateHewan(hewan: Hewan)
}
