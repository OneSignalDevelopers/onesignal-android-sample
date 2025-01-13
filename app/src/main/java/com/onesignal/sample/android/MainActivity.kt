package com.onesignal.sample.android

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.onesignal.OneSignal
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.login).setOnClickListener {
            authenticate()
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


    private fun authenticate() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("http://10.0.2.2:3000/auth")
            .post("".toRequestBody())
            .build()

        println("Auth request built")
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: java.io.IOException) {
                // Handle failure
                print("An error occurred")
                print(e)
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val responseBody = response.body
                    if (responseBody == null) {
                        println("Error: Response body is null")
                        return
                    }

                    val jsonData: String = responseBody.string()
                    println("Auth server response $jsonData")

                    val jsonObject = JSONObject(jsonData)

                    // Safeguard against missing keys
                    if (!jsonObject.has("onesignal_verification_token")) {
                        println("Error: Missing 'onesignal_verification_token' in response")
                        return
                    }

                    val token = jsonObject.getString("onesignal_verification_token")
                    println("JWT $token")
                    
                    val userObject = jsonObject.optJSONObject("user")
                    if (userObject == null || !userObject.has("external_id")) {
                        println("Error: Missing 'external_id' in 'user' object")
                        return
                    }
                    val eid = userObject.getString("external_id")

                    OneSignal.login(eid, token)
                } catch (e: Exception) {
                    println("Error parsing response: ${e.message}")
                    e.printStackTrace()
                }
            }
        })
    }
}