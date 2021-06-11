package com.sila.savingsmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.firebase.client.Firebase
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {

    private lateinit var mFirstName: EditText
    private lateinit var mLastName: EditText
    private lateinit var mEmail: EditText
    private lateinit var mPass: EditText
    private lateinit var btnReg: Button
    private lateinit var mSignin: TextView

    //Firebase...
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        mAuth = FirebaseAuth.getInstance()

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

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Invalid...")
                    return@OnClickListener
                }
            }

            if(TextUtils.isEmpty(pass) || pass.length<6){
                mPass.setError("Password is Invalid..")
                mPass.requestFocus()
                return@OnClickListener
            }

            registerUser(email, pass)
        })

        mSignin.setOnClickListener(View.OnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        })
    }

    private fun registerUser(email: String, pass: String) {
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this){
            if(it.isSuccessful){
                Intent(this@RegistrationActivity, HomeActivity::class.java).also{
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                }
            } else {
                Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}