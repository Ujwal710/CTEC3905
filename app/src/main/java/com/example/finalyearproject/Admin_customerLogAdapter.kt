package com.example.finalyearproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalyearproject.Model.Customer
import com.example.finalyearproject.Model.DataBaseHelper

class Admin_customerLogAdapter (dataBaseHelper: DataBaseHelper) : RecyclerView.Adapter<Admin_customerLogAdapter.CustomerLogViewHolder>(){

    val db = dataBaseHelper
    private var CustomerLogList: ArrayList<Customer> = ArrayList()
    private var onClickView: ((Customer) -> Unit)? = null
    private var onClickEdit: ((Customer) -> Unit)? = null
    private var onClickDelete: ((Customer) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int )= CustomerLogViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.layout_customerlog, parent, false),db
    )

    fun addItems(items: ArrayList<Customer>) {
        this.CustomerLogList = items
    }

    fun setOnClickItem(callback: (Customer) -> Unit) {
        this.onClickView = callback
        this.onClickEdit = callback
        this.onClickDelete = callback
    }


    override fun onBindViewHolder(holder: Admin_customerLogAdapter.CustomerLogViewHolder, position: Int) {
        val CL = CustomerLogList[position]
        holder.bindView(CL)
        holder.ViewDetails.setOnClickListener { onClickView?.invoke(CL) }
        holder.EditDetails.setOnClickListener { onClickEdit?.invoke(CL) }
        holder.DeleteDetails.setOnClickListener { onClickDelete?.invoke(CL) }
    }

    override fun getItemCount(): Int {
        return CustomerLogList.size
    }

    class CustomerLogViewHolder(var view: View, var db: DataBaseHelper) : RecyclerView.ViewHolder(view) {
        private var Name = view.findViewById<TextView>(R.id.textViewCL_Name)
        private var Email = view.findViewById<TextView>(R.id.textViewCL_Email)
        private var Username = view.findViewById<TextView>(R.id.textViewCL_Username)
        var ViewDetails = view.findViewById<ImageView>(R.id.imageViewCL_ViewDetails)
        var EditDetails = view.findViewById<ImageView>(R.id.imageViewCL_EditDetails)
        var DeleteDetails = view.findViewById<ImageView>(R.id.imageViewCL_DeleteDetails)

        fun bindView(customerLogs: Customer) {
            Name.text = customerLogs.FirstName.toString() + " " + customerLogs.Surname
            Email.text =  customerLogs.Email
            Username.text = customerLogs.UserName



        }

    }
}