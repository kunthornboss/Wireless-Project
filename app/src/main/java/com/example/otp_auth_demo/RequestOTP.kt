package com.example.otp_auth_demo

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_requestotp.*

class RequestOTP : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requestotp)

        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            CountryData.countryNames
        )

        button.setOnClickListener {
            val code =
                CountryData.countryAreaCodes[spinner.selectedItemPosition]
            if (mobileNumber.text.count() == 10) {
                val i = Intent(this, OTPValidation::class.java).apply {
                    putExtra("mobileNumber", code+mobileNumber.text.toString())
                }
                startActivity(i)
            } else {
                Toast.makeText(this, "Enter 10 digits mobile number",Toast.LENGTH_LONG).show()
            }
        }
    }
}