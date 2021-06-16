package com.venkatesh.kardas.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.venkatesh.kardas.R

class SplashScreenActivity : AppCompatActivity() {

    var sharedPreferences: SharedPreferences? = null
    var sharedValue: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE)

        sharedValue = sharedPreferences!!.getString("email", null)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        runOnUiThread {
            Handler().postDelayed({

                if (sharedValue != null) {

                    val intent = Intent(this, MainActivity::class.java);
                    startActivity(intent);

                } else {

                    val intent = Intent(this, LoginNewActivity::class.java);
                    startActivity(intent);

                }

                finish();
            }, 3000)
        }

    }

}