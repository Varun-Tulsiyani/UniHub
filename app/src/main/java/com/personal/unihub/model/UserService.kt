package com.personal.unihub.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.UUID

interface UserService {
    @GET("/users/{userId}")
    fun getUser(@Path("userId") userId: UUID): Call<User>
}

fun getUserInfo(userId: UUID) {
    val userService = RetrofitInstance.retrofit.create(UserService::class.java)
    val call: Call<User> = userService.getUser(userId)

    call.enqueue(object : Callback<User> {
        override fun onResponse(call: Call<User>, response: Response<User>) {
            if(response.isSuccessful) {
                val user: User? = response.body()
                println("User: $user")
            } else {
                println("Error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            println("Error: ${t.message}")
        }
    })
}