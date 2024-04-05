package com.android.library_exe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.library_exe.databinding.ActivityRegisterBinding
import com.google.firebase.firestore.FirebaseFirestore


class RegisterActivity  : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        db = FirebaseFirestore.getInstance()

        binding.registerBtn.setOnClickListener {
            val username = binding.username.text.toString()
            val email = binding.emailEt.text.toString()
            val password = binding.passEt.text.toString()
            val branch = binding.branchEt.text.toString()
            val year = binding.emailEditText.text.toString()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || branch.isEmpty() || year.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = mapOf(
                "username" to username,
                "email" to email,
                "password" to password,
                "branch" to branch,
                "year" to year
            )

            db.collection("users")
                .add(user)
                .addOnSuccessListener {
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                }
        }
    }

}