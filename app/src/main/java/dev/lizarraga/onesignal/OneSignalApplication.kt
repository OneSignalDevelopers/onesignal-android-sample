package dev.lizarraga.onesignal

import android.app.Application
import com.onesignal.OneSignal

class OneSignalApplication: Application() {

    private val ONESIGNAL_APP_ID = "YOUR_APP_ID_HERE"

    override fun onCreate() {
        super.onCreate()

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }
}