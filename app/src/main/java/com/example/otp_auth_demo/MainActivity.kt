package com.example.otp_auth_demo

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get reference to all views
        var et_user_name = findViewById(R.id.et_user_name) as EditText
        var et_password = findViewById(R.id.et_password) as EditText
        var btn_reset = findViewById(R.id.btn_reset) as Button
        var btn_submit = findViewById(R.id.btn_submit) as Button

        btn_reset.setOnClickListener {
            // clearing user_name and password edit text views on reset button click
            et_user_name.setText("")
            et_password.setText("")
        }

        // set on-click listener
        btn_submit.setOnClickListener {
            val user_name = et_user_name.text.toString();
            val password = et_password.text.toString();
            val keyusername = "tester";
            val keypassword = "111";
            Toast.makeText(this@MainActivity, user_name, Toast.LENGTH_LONG).show()

            if(user_name == keyusername && password == keypassword) {
                val i = Intent(this, CheckAttendance::class.java)
                startActivity(i)
            } else {
                Toast.makeText(this,"Incorrect username or password", Toast.LENGTH_LONG).show()
            }
        }
        btn_login.setOnClickListener {
            val i = Intent(this, RequestOTP::class.java)
            startActivity(i)
        }
    }
}