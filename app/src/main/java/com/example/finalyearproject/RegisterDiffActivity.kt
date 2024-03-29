package com.example.finalyearproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.finalyearproject.Model.Customer
import com.example.finalyearproject.Model.DataBaseHelper
import com.example.finalyearproject.Model.SecurityQuestions
import com.google.android.material.textfield.TextInputEditText

class RegisterDiffActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_diff)

        val db = DataBaseHelper(this)

        var dropdownSecurityQuestion = findViewById<Spinner>(R.id.spinnerSecurityQuestions)
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, db.getSecurityQuestionsList())
        dropdownSecurityQuestion.adapter = arrayAdapter
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
        val Confirmpassword = findViewById<TextInputEditText>(R.id.textInputEditConfirmPassword).text.toString()
        val securityQuestions = findViewById<Spinner>(R.id.spinnerSecurityQuestions).selectedItem.toString()
        val answer = findViewById<EditText>(R.id.editTextAnswer).text.toString()
        val pattern = Regex("[^a-zA-z]")
        val patternEmail = Regex("^([\\w\\d-._]+)@([\\w\\d-]+)(\\.[\\w\\d]+)*(\\.[a-z]{2,})$")
        val numberV = Regex("^((\\+44)|(0))( ?)(\\d{3})( ?\\d{3})( ?\\d{4})$")

        if (firstname.isEmpty() || surname.isEmpty() || email.isEmpty() || address.isEmpty() ||
            postcode.isEmpty() || number.isEmpty() || username.isEmpty() || password.isEmpty() || Confirmpassword.isEmpty() || securityQuestions.isEmpty()||
                    answer.isEmpty()
        ) {
            Toast.makeText(this, "Please fill in all the blanket", Toast.LENGTH_SHORT).show()

        } else if(password != Confirmpassword){
            Toast.makeText(this, "The password and confirm password doesn't match", Toast.LENGTH_SHORT).show()

        } else if(pattern.containsMatchIn(firstname) || pattern.containsMatchIn(surname) || patternEmail.containsMatchIn(email) || numberV.containsMatchIn(number)){

            Toast.makeText(this, "Please make sure the details are correct", Toast.LENGTH_SHORT).show()
        }
        
        else {
            val newCustomer = Customer(
                -1,
                firstname,
                surname,
                email,
                address,
                postcode,
                number,
                username,
                password,
                -1,
                answer
            )
            val myDataBase = DataBaseHelper(this)
            val result = myDataBase.AddCustomer(newCustomer,securityQuestions)
            when (result) {
                1 -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(applicationContext, "Registered", Toast.LENGTH_SHORT).show()
                }
                -1 -> Toast.makeText(this, "Error Creating account", Toast.LENGTH_SHORT).show()
                -2 -> Toast.makeText(this, "Error can not open database", Toast.LENGTH_SHORT).show()
                -3 -> Toast.makeText(this, "This username is taken", Toast.LENGTH_SHORT).show()
            }
        }
    }
}