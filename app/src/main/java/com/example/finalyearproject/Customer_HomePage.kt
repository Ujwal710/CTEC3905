package com.example.finalyearproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Customer_HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_home_page)
    }

    fun navHHome(view: View) {
        val intent = Intent(this, Customer_HomePage::class.java)
        startActivity(intent)


    }
    fun navHShop(view: View) {
        val intent = Intent(this, Customer_ShopPage::class.java)
        startActivity(intent)


    }
    fun navHCart(view: View) {
        val intent = Intent(this, Customer_Cart::class.java)
        startActivity(intent)


    }
    fun navHWishlist(view: View) {
        val intent = Intent(this, Customer_Wishlist::class.java)
        startActivity(intent)


    }
    fun navHAccount(view: View) {
        val intent = Intent(this, CustomerContactPage::class.java)
        startActivity(intent)


    }
}