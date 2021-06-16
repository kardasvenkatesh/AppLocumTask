package com.venkatesh.kardas.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.venkatesh.kardas.Utility.Utility.isInternetAvailable
import com.venkatesh.kardas.model.User
import com.venkatesh.kardas.repository.UserRepository

/**
 * Created By - Mehadi
 * Created On - 2/6/2020 : 1:18 PM
 * Email - hi@mehadih.me
 * Website - www.mehadih.me
 */
class UserViewModel(private val context: Context) : ViewModel() {

    private var listData = MutableLiveData<ArrayList<User>>()

    init {
        val userRepository: UserRepository by lazy {
            UserRepository
        }
        if (context.isInternetAvailable()) {
            listData = userRepository.getMutableLiveData(context)
        }
    }

    fun getData(): MutableLiveData<ArrayList<User>> {
        return listData
    }
}