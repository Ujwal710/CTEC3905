package com.example.finalyearproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.finalyearproject.Model.DataBaseHelper
import com.example.finalyearproject.R

class CustomerContactPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_contact_page)

        val username = intent.getStringExtra("Customer_UserName").toString()
        val db = DataBaseHelper(this)
        val customer = db.GetCustomerDetails(username)

        val firstName = findViewById<TextView>(R.id.textView_First_Name)
        val surname = findViewById<TextView>(R.id.textView_Surname)
        val Email = findViewById<TextView>(R.id.textView_Email)
        val Address = findViewById<TextView>(R.id.textView_Address)
        val Postcode = findViewById<TextView>(R.id.textView_PostCode)
        val Number = findViewById<TextView>(R.id.textView_Number)

        firstName.text = customer.FirstName
        surname.text = customer.Surname
        Email.text = customer.Email
        Address.text = customer.Address
        Postcode.text = customer.PostCode
        Number.text = customer.Number
    }


    /***************************** Navigation bar *************************************/
    fun navACCHome(view: View) {
        val username = intent.getStringExtra("Customer_UserName").toString()
        val intent = Intent(this, Customer_HomePage::class.java)
        intent.putExtra("Customer_UserName", username)
        startActivity(intent)


    }
    fun navACCShop(view: View) {
        val username = intent.getStringExtra("Customer_UserName").toString()
        val intent = Intent(this, Customer_ShopPage::class.java)
        intent.putExtra("Customer_UserName", username)
        startActivity(intent)


    }
    fun navACCCart(view: View) {
        val username = intent.getStringExtra("Customer_UserName").toString()
        val intent = Intent(this, Customer_Cart::class.java)
        intent.putExtra("Customer_UserName", username)
        startActivity(intent)


    }
    fun navACCWishlist(view: View) {
        val username = intent.getStringExtra("Customer_UserName").toString()
        val intent = Intent(this, Customer_Wishlist::class.java)
        intent.putExtra("Customer_UserName", username)
        startActivity(intent)


    }
    fun navACCAccount(view: View) {
        val username = intent.getStringExtra("Customer_UserName").toString()
        val intent = Intent(this, CustomerContactPage::class.java)
        intent.putExtra("Customer_UserName", username)
        startActivity(intent)
    }
    /***************************** Other features *************************************/
    fun CustomerServiceButton(view: View){
        val username = intent.getStringExtra("Customer_UserName").toString()
        val intent = Intent(this, Customer_CustomerServicePage::class.java)
        intent.putExtra("Customer_UserName", username)
        startActivity(intent)
    }

    fun logoutbutton(view: View){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logging out!")
        builder.setMessage("Are you sure you would like to logout!")

        builder.setPositiveButton("Yes") { _, _ ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        builder.setNegativeButton("No"){_,_ ->}
        val dialog = builder.create()
        dialog.show()
    }

}