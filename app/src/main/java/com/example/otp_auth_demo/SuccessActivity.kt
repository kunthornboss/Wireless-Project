package com.example.otp_auth_demo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_requestotp.*
import com.google.firebase.firestore.CollectionReference;
import com.google.android.gms.tasks.OnSuccessListener;
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_main.*

class SuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        var et_student_id = findViewById(R.id.et_student_id) as EditText
        var et_name = findViewById(R.id.et_name) as EditText
        var btn_reset = findViewById(R.id.btn_reset) as Button
        var btn_submit = findViewById(R.id.btn_submit) as Button

        btn_reset.setOnClickListener {
            et_user_name.setText("")
            et_password.setText("")
        }

        btn_submit.setOnClickListener {
            val student_id = et_student_id.text.toString();
            val fullname = et_name.text.toString();
            saveFireStore(student_id, fullname)
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }

    fun saveFireStore(id: String, name: String) {
        val db = FirebaseFirestore.getInstance()
        val student = hashMapOf(
            "id" to id,
            "name" to name,
            "attendance" to "attended"
        )

        db.collection("users").document(id)
            .set(student)
            .addOnSuccessListener {
                Toast.makeText(this@SuccessActivity, "Attendance checked", Toast.LENGTH_SHORT ).show()
            }
            .addOnFailureListener{
                Toast.makeText(this@SuccessActivity, "Error", Toast.LENGTH_SHORT ).show()
            }
    }
}