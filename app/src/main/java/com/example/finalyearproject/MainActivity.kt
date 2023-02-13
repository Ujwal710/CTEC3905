package com.example.finalyearproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.finalyearproject.Model.Customer
import com.example.finalyearproject.Model.DataBaseHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonSigninClicked(view: View) {

        val Username = findViewById<EditText>(R.id.editTextUsername).toString()
        val Password = findViewById<EditText>(R.id.editTextPassword).toString()

        if (Username.isEmpty() || Password.isEmpty()) {
            Toast.makeText(this, "Please Enter Username and Password", Toast.LENGTH_SHORT).show()

        } else {
            val myDataBase = DataBaseHelper(this)
            val result =
                myDataBase.getCustomer(Customer(-1, "", "", "", "", "", "", Username, Password))
        }

    }

    fun buttonregister(view: View) {
        val intent = Intent(this, RegisterDiffActivity::class.java)
        startActivity(intent)
    }
}