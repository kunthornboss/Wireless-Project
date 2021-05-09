package com.example.otp_auth_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_o_t_p_validation.*

class OTPValidation : AppCompatActivity() {
    private var mVerificationId: String? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_o_t_p_validation)

        val mobileNumber = intent.getStringExtra("mobileNumber")

        mAuth = FirebaseAuth.getInstance()

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "code$mobileNumber",
                60,
                java.util.concurrent.TimeUnit.SECONDS,
                this,
                callbacks)

        verifyButton.setOnClickListener {
            if (!otpTextField.text.isNullOrEmpty()) {
                verifyVerificationCode(otpTextField.text.toString())
            }
        }
    }

    private val callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                    val code = phoneAuthCredential.smsCode
                    if (code != null) {
                        verifyVerificationCode(code)
                    }
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    if (e is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this@OTPValidation, e.message, Toast.LENGTH_LONG).show()
                    } else if (e is FirebaseTooManyRequestsException) {
                        Toast.makeText(this@OTPValidation, e.message, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onCodeSent(
                        s: String,
                        forceResendingToken: PhoneAuthProvider.ForceResendingToken
                ) {
                    super.onCodeSent(s, forceResendingToken)
                    mVerificationId = s
                }
            }

    private fun verifyVerificationCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(mVerificationId!!, code)

        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth!!.signInWithCredential(credential)
                .addOnCompleteListener(
                        this,
                        OnCompleteListener<AuthResult?> { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this,"OTP Verified", Toast.LENGTH_LONG).show()
                                val i = Intent(this, SuccessActivity::class.java)
                                startActivity(i)
                            } else {
                                Toast.makeText(this,"Invalid OTP", Toast.LENGTH_LONG).show()
                            }
                        })
    }
}