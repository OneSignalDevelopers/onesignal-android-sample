package com.onesignal.sample.android
import android.app.Application
import com.onesignal.IUserJwtInvalidatedListener
import com.onesignal.OneSignal
import com.onesignal.UserJwtInvalidatedEvent
import com.onesignal.debug.LogLevel

const val ONESIGNAL_APP_ID = "305964ee-f979-4054-83e7-4c2096f2b0b4"

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        OneSignal.Debug.logLevel = LogLevel.VERBOSE
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID)
        OneSignal.addUserJwtInvalidatedListener(JWTInvalidatedListener())
    }
}

class JWTInvalidatedListener: IUserJwtInvalidatedListener {
    override fun onUserJwtInvalidated(event: UserJwtInvalidatedEvent) {
       println("JWT Invalidated $event")
    }
}
