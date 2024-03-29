package com.example.finalproject_pam.repository

import com.example.finalproject_pam.data.Hewan
import com.example.finalproject_pam.data.HewanDao
import kotlinx.coroutines.flow.Flow

class OfflineRepositoriHewan (private val hewanDao: HewanDao): RepositoriHewan {
    override fun getAllHewanStream(): Flow<List<Hewan>> {
        return hewanDao.getAllHewan()
    }

    override fun getHewanStream(id: Int): Flow<Hewan> {
        return hewanDao.getHewan(id)
    }

    override suspend fun insertHewan(hewan: Hewan) {
        return hewanDao.insert(hewan)
    }

    override suspend fun deleteHewan(hewan: Hewan) {
        return hewanDao.delete(hewan)
    }

    override suspend fun updateHewan(hewan: Hewan) {
        return hewanDao.update(hewan)
    }

    override suspend fun searchHewan(searchKeyword: String): List<Hewan>? {
        return hewanDao.searchHewan(searchKeyword)
    }

}
