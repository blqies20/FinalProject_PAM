package com.example.finalproject_pam.repository

import android.content.Context
import com.example.finalproject_pam.data.DatabaseHewan


interface ContainerApp {
    val repositoriHewan : RepositoriHewan
}

class ContainerDataApp(private val context: Context): ContainerApp{
    override val repositoriHewan: RepositoriHewan by lazy {
        OfflineRepositoriHewan(DatabaseHewan.getDatabase(context).hewanDao())
    }
}