package com.fernandoherrera.hackernewstestapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HNMobileTestApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}