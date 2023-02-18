package com.example.finalyearproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AdminCustomerRequestPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_customer_request_page)
    }

    fun HomeButton(view: View) {
        val intent = Intent(this, AdminHomePage::class.java)
        startActivity(intent)
    }

    fun CustomerLogs(view: View) {
        val intent = Intent(this, Admin_CustomerLogPage::class.java)
        startActivity(intent)
    }

    fun OrderList(view: View) {
        val intent = Intent(this, AdminOrderListPage::class.java)
        startActivity(intent)
    }

    fun CustomerRequest(view: View) {
        val intent = Intent(this, AdminCustomerRequestPage::class.java)
        startActivity(intent)
    }

    fun ProductLogs(view: View) {
        val intent = Intent(this, AdminProductLogsPage::class.java)
        startActivity(intent)
    }
}