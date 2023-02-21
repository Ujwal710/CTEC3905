package com.example.finalyearproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalyearproject.Model.DataBaseHelper

class Admin_CustomerLogPage : AppCompatActivity() {

    private lateinit var Database: DataBaseHelper
    private lateinit var recycler: RecyclerView
    private var adapter: Admin_customerLogAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_customer_log_page)


        view()
        RecyclerView()
        getArrayofCustomerLog()
        adapter?.setOnClickItem {

        }
    }















    /***************************** Navigation bar *************************************/
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

/***************************** Recycler code ***********************************/
    private fun view() {
        recycler = findViewById(R.id.recycler_CustomerLog)
    }

    private fun RecyclerView() {
        recycler.layoutManager = LinearLayoutManager(this)
        adapter = Admin_customerLogAdapter(getDatabase())
        recycler.adapter = adapter

    }

    private fun getDatabase(): DataBaseHelper {
        Database = DataBaseHelper(this)
        return Database
    }
    private fun getArrayofCustomerLog() {
        val CustomerLogList = Database.getArrayofCustomerLogfromdatabase()
        adapter?.addItems(CustomerLogList)
    }
}