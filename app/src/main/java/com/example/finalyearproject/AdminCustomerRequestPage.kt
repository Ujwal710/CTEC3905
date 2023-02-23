package com.example.finalyearproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalyearproject.Model.DataBaseHelper

class AdminCustomerRequestPage : AppCompatActivity() {

    private lateinit var Database: DataBaseHelper
    private lateinit var recycler: RecyclerView
    private var adapter: AdminCustomerRequestAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_customer_request_page)

        view()
        RecyclerView()
        getArrayofCustomerRequest()
        adapter?.setOnClickItem {

        }

        adapter?.setOnClickDelete {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Warning!!!")
            builder.setMessage("Are you sure you would like to delete this customer Query?")
            builder.setPositiveButton("Yes"){_,_ ->
                getDatabase().deleteCustomerrequest(it.CustomerQueryId)
                Toast.makeText(this, "Query deleted Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AdminCustomerRequestPage::class.java)
                startActivity(intent)
            }
            builder.setNegativeButton("No"){ _,_ ->}
            val dialog = builder.create()
            dialog.show()
        }


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

    private fun view() {
        recycler = findViewById(R.id.Recycler_CustomerQuery)
    }

    private fun RecyclerView() {
        recycler.layoutManager = LinearLayoutManager(this)
        adapter = AdminCustomerRequestAdapter(getDatabase())
        recycler.adapter = adapter

    }

    private fun getDatabase(): DataBaseHelper {
        Database = DataBaseHelper(this)
        return Database
    }

    private fun getArrayofCustomerRequest() {
        val CustomerRequestList = Database.getArrayofCustomerRequestsfromdatabase()
        adapter?.addItems(CustomerRequestList)
    }

}