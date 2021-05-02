package com.example.otp_auth_demo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import kotlinx.android.synthetic.main.activity_checkattendance.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_requestotp.*
import kotlinx.android.synthetic.main.activity_main.btn_reset as btn_reset1

class CheckAttendance : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkattendance)

        readFireStoreData()

        btn_reset.setOnClickListener {
            val db1 = FirebaseFirestore.getInstance()
            db1.collection("users").document("6188000")
                .delete()

            val db2 = FirebaseFirestore.getInstance()
            db2.collection("users").document("6188001")
                .delete()

            val db3 = FirebaseFirestore.getInstance()
            db3.collection("users").document("6188002")
                .delete()

            val db4 = FirebaseFirestore.getInstance()
            db4.collection("users").document("6188003")
                .delete()
        }
    }
    fun readFireStoreData() {
        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .get()
            .addOnCompleteListener {

                val result: StringBuffer = StringBuffer()

                if(it.isSuccessful) {
                    for(document in it.result!!) {
                        result.append(document.data.getValue("id")).append(" ")
                            .append(document.data.getValue("name")).append(" ").append(document.data.getValue("attendance")).append("\n\n")
                    }
                    textViewResult.setText(result)
                }
            }

    }
}