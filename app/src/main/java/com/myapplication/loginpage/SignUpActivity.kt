package com.myapplication.loginpage

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.myapplication.loginpage.databinding.ActivitySignUpBinding

class SignUpActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        firebaseAuth = FirebaseAuth.getInstance()
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passET.text.toString()
            val confirmpass = binding.confirmPassEt.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmpass.isNotEmpty()) {
                if (password == confirmpass){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener{
                        if (it.isSuccessful){
                            val intent = Intent(this, SignInActivity::class.java)
                            startActivity(intent)

                        }else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT)
                                .show()

                        }
                    }
                }else{
                    Toast.makeText(this, "password is not matching", Toast.LENGTH_SHORT)
                        .show()
                }
                }else{
                     Toast.makeText(this, "Empty Fields are not allowed", Toast.LENGTH_SHORT)
                         .show()
            }
        }
    }
}