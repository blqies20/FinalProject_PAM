package com.example.finalproject_pam.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Hewan::class], version = 1, exportSchema = false)
abstract class DatabaseHewan : RoomDatabase() {
    abstract fun hewanDao() : HewanDao

    companion object{
        @Volatile
        private var Instance: DatabaseHewan? = null

        fun getDatabase(context: Context): DatabaseHewan {
            return (Instance?: synchronized(this){
                Room.databaseBuilder(context, DatabaseHewan::class.java, "hewan_database").build().also { Instance=it }
            })
        }
    }
}