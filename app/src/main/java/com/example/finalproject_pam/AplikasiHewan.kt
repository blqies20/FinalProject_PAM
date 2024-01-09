package com.example.finalproject_pam

import android.app.Application
import com.example.finalproject_pam.repository.ContainerApp
import com.example.finalproject_pam.repository.ContainerDataApp

class AplikasiHewan : Application() {

    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}