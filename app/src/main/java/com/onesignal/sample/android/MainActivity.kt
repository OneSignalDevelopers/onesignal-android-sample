package com.onesignal.sample.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onesignal.OneSignal
import android.widget.Button
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody

class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.login).setOnClickListener {
            run()

        }

        findViewById<Button>(R.id.logout).setOnClickListener {
            OneSignal.logout()
        }

        findViewById<Button>(R.id.enable_push).setOnClickListener {
            OneSignal.User.pushSubscription.optIn()
        }

        findViewById<Button>(R.id.disable_push).setOnClickListener {
            OneSignal.User.pushSubscription.optOut()
        }

        findViewById<Button>(R.id.prompt_push).setOnClickListener {
            OneSignal.InAppMessages.addTrigger("show_push_permission_prompt", "1")
        }

        findViewById<Button>(R.id.present_iam).setOnClickListener {
            OneSignal.InAppMessages.addTrigger("show_sample_iam", "1")
        }
    }


    fun run() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("http://localhost:3000/auth")
            .post("".toRequestBody())
            .build()

        println("Logging in")
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: java.io.IOException) {
                // Handle failure
                print("An error occurred")
                print(e)
            }

            override fun onResponse(call: Call, response: Response) {
                // Handle success
                val result = response.body?.string() ?: ""
                // Process the response data

                OneSignal.login("will")
                println("Auth server response")
                println(result)
            }
        })
    }
}