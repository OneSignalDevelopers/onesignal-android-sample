package com.onesignal.sample.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onesignal.OneSignal
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.enable_push).setOnClickListener(
            {
                OneSignal.User.pushSubscription.optIn()
            }
        )
        findViewById<Button>(R.id.disable_push).setOnClickListener(
            {
                OneSignal.User.pushSubscription.optOut()
            }
        )
        findViewById<Button>(R.id.prompt_push).setOnClickListener(
            {
                OneSignal.InAppMessages.addTrigger("show_push_permission_prompt", "1")
            }
        )
        findViewById<Button>(R.id.present_iam).setOnClickListener(
            {
                OneSignal.InAppMessages.addTrigger("show_sample_iam", "1")
            }
        )

    }
}