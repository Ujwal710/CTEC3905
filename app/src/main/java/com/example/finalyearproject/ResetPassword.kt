package com.example.finalyearproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.finalyearproject.Model.DataBaseHelper

class ResetPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
    }

    fun resetButton(view: View){
        val db = DataBaseHelper(this)
        val newpassword = findViewById<EditText>(R.id.editTextNewPassword).text.toString()
        val confirmnewpassword = findViewById<EditText>(R.id.editTextConfirmNewPassword).text.toString()
        val email = intent.getStringExtra("Customer_email").toString()
        val customerid = db.getCustomeridbyemail(email).toString()

        if(newpassword.equals(confirmnewpassword)){
            db.ResetPassword(newpassword,customerid)
            Toast.makeText(this, "Password has been reset", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this, "password does not match", Toast.LENGTH_SHORT).show()
        }

    }
}