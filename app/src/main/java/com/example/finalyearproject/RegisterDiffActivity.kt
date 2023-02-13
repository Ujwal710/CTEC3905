package com.example.finalyearproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.finalyearproject.Model.Customer
import com.example.finalyearproject.Model.DataBaseHelper
import com.google.android.material.textfield.TextInputEditText

class RegisterDiffActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_diff)
    }

    fun RegisterButton(view: View) {
        val firstname = findViewById<EditText>(R.id.editTextRegisterFirstname).text.toString()
        val surname = findViewById<EditText>(R.id.editTextRegisterSurname).text.toString()
        val email = findViewById<EditText>(R.id.editTextRegisterEmail).text.toString()
        val address = findViewById<EditText>(R.id.editTextRegisterAddress).text.toString()
        val postcode = findViewById<EditText>(R.id.editTextRegisterPostcode).text.toString()
        val number = findViewById<EditText>(R.id.editTextRegisterNumber).text.toString()
        val username = findViewById<TextInputEditText>(R.id.textInputEditUsername).text.toString()
        val password = findViewById<TextInputEditText>(R.id.textInputEditPassword).text.toString()
        val Confirmpassword =
            findViewById<TextInputEditText>(R.id.textInputEditConfirmPassword).text.toString()

        if (firstname.isEmpty() || surname.isEmpty() || email.isEmpty() || address.isEmpty() ||
            postcode.isEmpty() || number.isEmpty() || username.isEmpty() || password.isEmpty() || Confirmpassword.isEmpty()
        ) {
            Toast.makeText(this, "Please fill in all the blanket", Toast.LENGTH_SHORT).show()
        } else {
            val newCustomer = Customer(
                -1,
                firstname,
                surname,
                email,
                address,
                postcode,
                number,
                username,
                password
            )
            val myDataBase = DataBaseHelper(this)
            val result = myDataBase.AddCustomer(newCustomer)
            when (result) {
                1 -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(applicationContext, "Registered", Toast.LENGTH_SHORT).show()
                }
                -1 -> Toast.makeText(this, "Error Creating account", Toast.LENGTH_SHORT).show()
                -2 -> Toast.makeText(this, "Error can not open database", Toast.LENGTH_SHORT).show()
                -3 -> Toast.makeText(this, "Student already exist", Toast.LENGTH_SHORT).show()
            }
        }
    }
}