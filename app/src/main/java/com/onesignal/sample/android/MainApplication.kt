package com.onesignal.sample.android
import android.app.Application
import com.onesignal.IUserJwtInvalidatedListener
import com.onesignal.OneSignal
import com.onesignal.UserJwtInvalidatedEvent
import com.onesignal.debug.LogLevel

const val ONESIGNAL_APP_ID = "4228cf8a-47dd-44d2-b646-9a8263db2898"

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
