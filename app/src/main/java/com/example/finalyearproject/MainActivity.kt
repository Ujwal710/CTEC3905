package com.example.finalyearproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.finalyearproject.Model.Admin
import com.example.finalyearproject.Model.Customer
import com.example.finalyearproject.Model.DataBaseHelper
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
     var backcounter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonSigninClicked(view: View) {
        val Message = findViewById<TextView>(R.id.textViewMessage)
        val Username = findViewById<EditText>(R.id.editTextUsername).text.toString()
        val Password = findViewById<EditText>(R.id.editTextPassword).text.toString()

        if (Username.isEmpty() || Password.isEmpty()) {
            Toast.makeText(this, "Please Enter Username and Password", Toast.LENGTH_SHORT).show()

        } else {
            val myDataBase = DataBaseHelper(this)
            val result =
                myDataBase.getCustomer(Username, Password)
            val resultAlt = myDataBase.getAdmin(Username, Password)
            if (result == -1) {
                if (resultAlt == -1) {
                    Message.text = "The Username or Password is incorrect"
                } else if (resultAlt == -2) {
                    Message.text = "Error Cannot Open Database"
                } else {
                    Toast.makeText(this, "Welcome Admin", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, AdminHomePage::class.java)
                    startActivity(intent)
                }
            } else if (result == -2) {
                Message.text = "Error Cannot open Database"
            }else{
                Toast.makeText(this, "Welcome $Username", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Customer_HomePage::class.java)
                intent.putExtra("Customer_UserName", Username)
                startActivity(intent)
            }
        }

    }

    fun buttonregister(view: View) {
        val intent = Intent(this, RegisterDiffActivity::class.java)
        startActivity(intent)
    }

    fun forgetPassword(view: View){
        val intent = Intent(this, ForgetPassword::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
       backcounter += 1
        if (backcounter == 1){
            Toast.makeText(this, "Please press back again to exit", Toast.LENGTH_SHORT).show()
        } else {
            this@MainActivity.finish()
            exitProcess(0)
        }
    }

}