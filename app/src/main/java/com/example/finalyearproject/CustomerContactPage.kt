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

    fun navHome(view: View) {
        val intent = Intent(this, RegisterDiffActivity::class.java)
        startActivity(intent)


    }
    fun navShop(view: View) {
        val intent = Intent(this, RegisterDiffActivity::class.java)
        startActivity(intent)


    }
    fun navCart(view: View) {
        val intent = Intent(this, RegisterDiffActivity::class.java)
        startActivity(intent)


    }
    fun navWishlist(view: View) {
        val intent = Intent(this, RegisterDiffActivity::class.java)
        startActivity(intent)


    }
    fun navAccount(view: View) {
        val intent = Intent(this, CustomerContactPage::class.java)
        startActivity(intent)


    }
}