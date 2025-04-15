package com.smitcoderx.learn.furnspace.presentation

import android.app.Application
import timber.log.Timber

class FurnSpaceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
