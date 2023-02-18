package com.example.finalyearproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.finalyearproject.R

class CustomerContactPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_contact_page)
    }

    fun navACCHome(view: View) {
        val intent = Intent(this, Customer_HomePage::class.java)
        startActivity(intent)


    }
    fun navACCShop(view: View) {
        val intent = Intent(this, Customer_ShopPage::class.java)
        startActivity(intent)


    }
    fun navACCCart(view: View) {
        val intent = Intent(this, Customer_Cart::class.java)
        startActivity(intent)


    }
    fun navACCWishlist(view: View) {
        val intent = Intent(this, Customer_Wishlist::class.java)
        startActivity(intent)


    }
    fun navACCAccount(view: View) {
        val intent = Intent(this, CustomerContactPage::class.java)
        startActivity(intent)


    }
}