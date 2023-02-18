package com.example.finalyearproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Customer_ShopPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_shop_page)
    }

    fun navSHome(view: View) {
        val intent = Intent(this, Customer_HomePage::class.java)
        startActivity(intent)


    }
    fun navSShop(view: View) {
        val intent = Intent(this, Customer_ShopPage::class.java)
        startActivity(intent)


    }
    fun navSCart(view: View) {
        val intent = Intent(this, Customer_Cart::class.java)
        startActivity(intent)


    }
    fun navSWishlist(view: View) {
        val intent = Intent(this, Customer_Wishlist::class.java)
        startActivity(intent)


    }
    fun navSAccount(view: View) {
        val intent = Intent(this, CustomerContactPage::class.java)
        startActivity(intent)


    }
}