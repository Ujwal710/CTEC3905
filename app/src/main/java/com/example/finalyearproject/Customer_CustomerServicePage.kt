package com.example.finalyearproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.finalyearproject.Model.Customer
import com.example.finalyearproject.Model.CustomerQuery
import com.example.finalyearproject.Model.DataBaseHelper

class Customer_CustomerServicePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_customer_service_page)

        val username = intent.getStringExtra("Customer_UserName").toString()
        val db = DataBaseHelper(this)
        val customer = db.GetCustomerDetails(username)


        val FullName = findViewById<TextView>(R.id.textViewCSFullName)
        val Email = findViewById<TextView>(R.id.textViewCSEmail)

        FullName.text = customer.FirstName + " " + customer.Surname
        Email.text = customer.Email
    }






    fun navCSHome(view: View) {
        val username = intent.getStringExtra("Customer_UserName").toString()
        val intent = Intent(this, Customer_HomePage::class.java)
        intent.putExtra("Customer_UserName", username)
        startActivity(intent)

    }
    fun navCSShop(view: View) {
        val username = intent.getStringExtra("Customer_UserName").toString()
        val intent = Intent(this, Customer_ShopPage::class.java)
        intent.putExtra("Customer_UserName", username)
        startActivity(intent)


    }
    fun navCSCart(view: View) {
        val username = intent.getStringExtra("Customer_UserName").toString()
        val intent = Intent(this, Customer_Cart::class.java)
        intent.putExtra("Customer_UserName", username)
        startActivity(intent)

    }
    fun navCSWishlist(view: View) {
        val username = intent.getStringExtra("Customer_UserName").toString()
        val intent = Intent(this, Customer_Wishlist::class.java)
        intent.putExtra("Customer_UserName", username)
        startActivity(intent)

    }
    fun navCSAccount(view: View) {
        val username = intent.getStringExtra("Customer_UserName").toString()
        val intent = Intent(this, CustomerContactPage::class.java)
        intent.putExtra("Customer_UserName", username)
        startActivity(intent)
    }

    fun CustomerQueryButton(view: View){
        val fullname = findViewById<TextView>(R.id.textViewCSFullName).text.toString()
        val email = findViewById<TextView>(R.id.textViewCSEmail).text.toString()
        val problem = findViewById<EditText>(R.id.editTextTextCSProblem).text.toString()
        val problemdescription = findViewById<EditText>(R.id.editTextCSProblemDescription).text.toString()
        val pattern = Regex("[^a-zA-z]")

        if(fullname.isEmpty() || email.isEmpty() || problem.isEmpty() || problemdescription.isEmpty()){
            Toast.makeText(this, "Please fill in all the blanket", Toast.LENGTH_SHORT).show()

            //need to add the validation for the name and email or change it text and auto fill the name and email.
        } else {
            val newCustomerQurey = CustomerQuery(
                -1,
                fullname ,
                email,
                problem,
                problemdescription
            )
            val myDataBase = DataBaseHelper(this)
            val result = myDataBase.AddCustomerQuerys(newCustomerQurey)
            when (result) {
                1 -> {
                    val username = intent.getStringExtra("Customer_UserName").toString()
                    val intent = Intent(this, CustomerContactPage::class.java)
                    intent.putExtra("Customer_UserName", username)
                    startActivity(intent)
                    Toast.makeText(applicationContext, "Successfull Sent", Toast.LENGTH_SHORT).show()
                }
                -1 -> Toast.makeText(this, "Error can't send the query", Toast.LENGTH_SHORT).show()
                -2 -> Toast.makeText(this, "Error can not open database", Toast.LENGTH_SHORT).show()
            }
        }
    }
}