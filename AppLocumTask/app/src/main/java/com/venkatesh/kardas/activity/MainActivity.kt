package com.venkatesh.kardas.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.venkatesh.kardas.Factory.UserViewModelFactory
import com.venkatesh.kardas.R
import com.venkatesh.kardas.model.User
import com.venkatesh.kardas.viewmodel.UserViewModel
import me.mehadih.retrofitlivedatamvvmrecyclerviewdatabinding.adapter.UserAdapter
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var listUsers: MutableList<User>
    private lateinit var adapter: UserAdapter
    private lateinit var logoutIv: AppCompatImageView

    private val sharedPrefFile = "kotlinsharedpreference"

    var prf: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prf = getSharedPreferences("user_details", MODE_PRIVATE)

        Log.d("resultttt", "" + prf!!.getString("email", null))

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(
                ContextCompat.getColor(
                    this,
                    R.color.purple_700
                )
            ); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(
                ContextCompat.getColor(
                    this,
                    R.color.purple_700
                )
            ); //status bar or the time bar at the top
        }

        val recycler_main: RecyclerView = findViewById(R.id.recycler_main)
        val logoutIv: AppCompatImageView = findViewById(R.id.logoutIv)

        logoutIv.setOnClickListener {

            val editor = prf!!.edit()
            editor.clear()
            editor.commit()

            val intent = Intent(this, LoginNewActivity::class.java)
            // start your next activity
            startActivity(intent)
            finish()
        }

        recycler_main.layoutManager = LinearLayoutManager(this@MainActivity)
        listUsers = mutableListOf<User>()
        adapter = UserAdapter(
            this,
            listUsers
        )
        recycler_main.adapter = adapter

        val userViewModel =
            ViewModelProviders.of(this, UserViewModelFactory(this)).get(UserViewModel::class.java)
        userViewModel.getData().observe(this, object : Observer<ArrayList<User>> {
            override fun onChanged(t: ArrayList<User>?) {
                listUsers.clear()
                t?.let { listUsers.addAll(it) }
                adapter.notifyDataSetChanged()
            }

        })

    }
}