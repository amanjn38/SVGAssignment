package com.example.svgassignment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltAndroidApp
class SVGApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}