package com.example.r8

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import kotlin.concurrent.thread

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<View>(android.R.id.content).setOnClickListener { call() }
    }

    private fun call() = thread {
        val call = SERVICE.listRepos("octocat")
        val response = call.execute()
        val body = response.body()!!
        runOnUiThread {
            Toast.makeText(this, "Count: ${body.size}", Toast.LENGTH_LONG).show()
        }
    }
}

interface GitHubService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Call<List<Repo>>
}

@Serializable
data class Repo(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
)

val JSON = Json { ignoreUnknownKeys = true }
val SERVICE = Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .addConverterFactory(JSON.asConverterFactory(MediaType.get("application/json")))
    .build().create<GitHubService>()
