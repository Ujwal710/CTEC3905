package com.example.finalyearproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Customer_Wishlist : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_wishlist)
    }

    fun navWLHome(view: View) {
        val username = intent.getStringExtra("Customer_UserName").toString()
        val intent = Intent(this, Customer_HomePage::class.java)
        intent.putExtra("Customer_UserName", username)
        startActivity(intent)

    }
    fun navWLShop(view: View) {
        val username = intent.getStringExtra("Customer_UserName").toString()
        val intent = Intent(this, Customer_ShopPage::class.java)
        intent.putExtra("Customer_UserName", username)
        startActivity(intent)


    }
    fun navWLCart(view: View) {
        val username = intent.getStringExtra("Customer_UserName").toString()
        val intent = Intent(this, Customer_Cart::class.java)
        intent.putExtra("Customer_UserName", username)
        startActivity(intent)


    }
    fun navWLWishlist(view: View) {
        val username = intent.getStringExtra("Customer_UserName").toString()
        val intent = Intent(this, Customer_Wishlist::class.java)
        intent.putExtra("Customer_UserName", username)
        startActivity(intent)


    }
    fun navWLAccount(view: View) {
        val username = intent.getStringExtra("Customer_UserName").toString()
        val intent = Intent(this, CustomerContactPage::class.java)
        intent.putExtra("Customer_UserName", username)
        startActivity(intent)


    }
}