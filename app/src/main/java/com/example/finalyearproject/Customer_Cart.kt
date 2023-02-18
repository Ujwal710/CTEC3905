package com.example.finalyearproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Customer_Cart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_cart)
    }

    fun navCTHome(view: View) {
        val intent = Intent(this, Customer_HomePage::class.java)
        startActivity(intent)


    }
    fun navCTShop(view: View) {
        val intent = Intent(this, Customer_ShopPage::class.java)
        startActivity(intent)


    }
    fun navCTCart(view: View) {
        val intent = Intent(this, Customer_Cart::class.java)
        startActivity(intent)


    }
    fun navCTWishlist(view: View) {
        val intent = Intent(this, Customer_Wishlist::class.java)
        startActivity(intent)


    }
    fun navCTAccount(view: View) {
        val intent = Intent(this, CustomerContactPage::class.java)
        startActivity(intent)


    }
}