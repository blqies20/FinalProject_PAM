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

@Entity(tableName = "tblRegister")
data class Register(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val NamaPanjang : String,
    val Email : String,
    val Password : String,

)