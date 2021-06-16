package com.venkatesh.kardas.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.venkatesh.kardas.Utility.Utility.hideProgressBar
import com.venkatesh.kardas.Utility.Utility.showProgressBar
import com.venkatesh.kardas.api.RetroServer
import com.venkatesh.kardas.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * Created By - Mehadi
 * Created On - 2/6/2020 : 1:14 PM
 * Email - hi@mehadih.me
 * Website - www.mehadih.me
 */
object UserRepository {

    fun getMutableLiveData(context: Context): MutableLiveData<ArrayList<User>> {

        val mutableLiveData = MutableLiveData<ArrayList<User>>()

        context.showProgressBar()

        Log.e("errorrrrr","dfddffdfddf")

        RetroServer.apiService.getUsers().enqueue(object : Callback<MutableList<User>> {
            override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
                hideProgressBar()
                Log.e("errorrrrr", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<MutableList<User>>,
                response: Response<MutableList<User>>
            ) {
                hideProgressBar()
                val usersResponse = response.body()
                Log.d("ressponse", "" + response.body());
                usersResponse?.let { mutableLiveData.value = it as ArrayList<User> }
            }

        })

        return mutableLiveData
    }
}