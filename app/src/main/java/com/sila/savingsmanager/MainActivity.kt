package com.sila.savingsmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var mEmail: EditText
    private lateinit var mPass: EditText
    private lateinit var btnLogin: Button
    private lateinit var mForgetPassword: TextView
    private lateinit var mSignupHere: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginDetails()
    }

    private fun loginDetails(){
        mEmail=findViewById(R.id.email_login)
        mPass=findViewById(R.id.password_login)
        btnLogin=findViewById(R.id.btn_login)
        mForgetPassword=findViewById(R.id.forget_password)
        mSignupHere=findViewById(R.id.signup_reg)

        btnLogin.setOnClickListener(View.OnClickListener {
            val email = mEmail.getText().toString().trim { it <= ' ' }
            val pass = mPass.getText().toString().trim { it <= ' ' }

            if(TextUtils.isEmpty(email)){
                mEmail.setError("Email is required...")
                return@OnClickListener
            }

            if(TextUtils.isEmpty(pass)){
                mPass.setError("Password is required...")
                return@OnClickListener
            }
        })

        mForgetPassword.setOnClickListener(View.OnClickListener {
            startActivity(Intent(applicationContext, ResetActivity::class.java))
        })

        mSignupHere.setOnClickListener(View.OnClickListener {
            startActivity(Intent(applicationContext, RegistrationActivity::class.java))
        })
    }
}