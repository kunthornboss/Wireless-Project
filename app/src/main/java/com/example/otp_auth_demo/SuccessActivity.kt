package com.example.otp_auth_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

import android.widget.ArrayAdapter
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.*

class SuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        var et_student_id = findViewById(R.id.et_student_id) as EditText
        var et_name = findViewById(R.id.et_name) as EditText
        var btn_reset = findViewById(R.id.btn_reset) as Button
        var btn_submit = findViewById(R.id.btn_submit) as Button

        btn_reset.setOnClickListener {
            // clearing user_name and password edit text views on reset button click
            et_user_name.setText("")
            et_password.setText("")
        }

        // set on-click listener
        btn_submit.setOnClickListener {
            val student_id = et_student_id.text.toString();
            val fullname = et_name.text.toString();

        }
    }
}