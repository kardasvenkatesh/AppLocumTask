package com.venkatesh.kardas.api

import com.venkatesh.kardas.model.User
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("photos")
    fun getUsers(): Call<MutableList<User>>
}