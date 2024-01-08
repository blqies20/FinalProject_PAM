package com.example.finalproject_pam.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface HewanDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(hewan: Hewan)

    @Update
    suspend fun update(hewan: Hewan)

    @Delete
    suspend fun delete(hewan: Hewan)

    @Query("SELECT * from tblHewan WHERE JenisHewan = :JenisHewan")
    fun getHewan(JenisHewan: String): Flow<Hewan>

    @Query("SELECT * from tblHewan ORDER BY JenisHewan ASC")
    fun getAllHewan(): Flow<List<Hewan>>
}

@Dao
interface RegisterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(register: Register)

    @Update
    suspend fun update(register: Register)

    @Delete
    suspend fun delete(register: Register)

    @Query("SELECT * from tblRegister WHERE NamaPanjang = NamaPanjang")
    fun getRegister(id: Int): Flow<Register>

    @Query("SELECT * from tblRegister ORDER BY NamaPanjang ASC")
    fun getAllRegister(): Flow<List<Register>>
}
