package com.onesignal.sample.android
import android.app.Application
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel

const val ONESIGNAL_APP_ID = "5e605fcd-de88-4b0a-a5eb-5c18b84d52f3"

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        OneSignal.Debug.logLevel = LogLevel.VERBOSE
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID)
    }
}
