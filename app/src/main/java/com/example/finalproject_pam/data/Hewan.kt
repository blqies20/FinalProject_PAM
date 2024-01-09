package com.example.finalproject_pam.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tblHewan")
data class Hewan(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val JenisHewan : String,
    val JenisKelamin : String,
    val Usia : String,
    val Harga : String,
)
