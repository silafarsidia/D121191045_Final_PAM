package com.sila.savingsmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class RegistrationActivity : AppCompatActivity() {

    private lateinit var mFirstName: EditText
    private lateinit var mLastName: EditText
    private lateinit var mEmail: EditText
    private lateinit var mPass: EditText
    private lateinit var btnReg: Button
    private lateinit var mSignin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        registration()
    }

    private fun registration() {
        mFirstName = findViewById(R.id.firstname_reg)
        mLastName = findViewById(R.id.lastname_reg)
        mEmail = findViewById(R.id.email_reg)
        mPass = findViewById(R.id.password_reg)
        btnReg = findViewById(R.id.btn_reg)
        mSignin = findViewById(R.id.signin_here)

        btnReg.setOnClickListener(View.OnClickListener {
            val firstName = mFirstName.getText().toString().trim { it <= ' ' }
            val lastName = mLastName.getText().toString().trim { it <= ' ' }
            val email = mEmail.getText().toString().trim { it <= ' ' }
            val pass = mPass.getText().toString().trim { it <= ' ' }

            if(TextUtils.isEmpty(firstName)){
                mFirstName.setError("First Name is Required...")
                return@OnClickListener
            }

            if(TextUtils.isEmpty(lastName)){
                mLastName.setError("Last Name is Required...")
                return@OnClickListener
            }

            if(TextUtils.isEmpty(email)){
                mEmail.setError("Email is Required...")
                return@OnClickListener
            }

            if(TextUtils.isEmpty(pass)){
                mPass.setError("Password is Required...")
                return@OnClickListener
            }

        })

        mSignin.setOnClickListener(View.OnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        })
    }

}