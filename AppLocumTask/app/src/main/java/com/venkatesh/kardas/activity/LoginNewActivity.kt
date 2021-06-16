package com.venkatesh.kardas.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.TransitionManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.venkatesh.kardas.R


class LoginNewActivity : AppCompatActivity() {

    lateinit var emailEt: TextInputEditText
    lateinit var passwordEt: TextInputEditText
    lateinit var layoutEmail: TextInputLayout
    lateinit var layoutPassword: TextInputLayout
    lateinit var rootLayout: ConstraintLayout
    lateinit var submitBtn: MaterialButton

    lateinit var email: String
    lateinit var password: String

    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEt = findViewById(R.id.etEmail)
        passwordEt = findViewById(R.id.etPassword)
        submitBtn = findViewById(R.id.btnSignin)

        layoutEmail = findViewById(R.id.layoutEmail)
        layoutPassword = findViewById(R.id.layoutPassword)
        rootLayout = findViewById(R.id.rootLayout)

        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE)
        if (sharedPreferences!!.contains("email") && sharedPreferences!!.contains("password")) {
            startActivity(intent)
        }

        // Button click listener
        submitBtn.setOnClickListener {
            if (isValidForm()) {
                Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
                editor.putString("email", email)
                editor.putString("password", password)
                Log.d("emaiaaaaaa", email);
                editor.commit()
                startActivity(intent)
                finish()


            }
        }
    }

    // Custom method to validate form inputted data
    private fun isValidForm(): Boolean {
        var isValid = true

        email = emailEt.text.toString().trim()
        password = passwordEt.text.toString().trim()

        TransitionManager.beginDelayedTransition(rootLayout)
        if (!email.isValidEmail()) {
            layoutEmail.isErrorEnabled = true
            layoutEmail.error = "input your email"
            isValid = false
        } else if (!email.equals("hello@yopmail.com")) {
            layoutEmail.isErrorEnabled = true
            layoutEmail.error = "Enter Valid Email"
            isValid = false
        } else {
            layoutEmail.isErrorEnabled = false
        }

        if (password.isNullOrEmpty()) {
            layoutPassword.isErrorEnabled = true
            layoutPassword.error = "Input password"
            isValid = false
        } else if (!password.equals("Password@123")) {
            layoutPassword.isErrorEnabled = true
            layoutPassword.error = "Enter Valid Password"
            isValid = false
        } else {
            layoutPassword.isErrorEnabled = false
        }

        return isValid
    }

    fun String.isValidEmail(): Boolean = !this.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(
        this
    ).matches()

}
