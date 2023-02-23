package com.example.finalyearproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalyearproject.Model.Customer
import com.example.finalyearproject.Model.CustomerQuery
import com.example.finalyearproject.Model.DataBaseHelper

class AdminCustomerRequestAdapter(dataBaseHelper: DataBaseHelper) : RecyclerView.Adapter <AdminCustomerRequestAdapter.CustomerRequestViewHolder>(){

    val db = dataBaseHelper
    private var CustomerRequestList: ArrayList<CustomerQuery> = ArrayList()
    private var onClickView: ((CustomerQuery) -> Unit)? = null
    private var onClickDelete: ((CustomerQuery) -> Unit)? = null

    fun addItems(items: ArrayList<CustomerQuery>) {
        this.CustomerRequestList = items
    }

    fun setOnClickItem(callback: (CustomerQuery) -> Unit) {
        this.onClickView = callback
    }

    fun setOnClickDelete(callback: (CustomerQuery) -> Unit) {
        this.onClickDelete = callback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int )= CustomerRequestViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.layout_customerrequest, parent, false),db
    )

    override fun onBindViewHolder(holder: CustomerRequestViewHolder, position: Int) {
        val CR = CustomerRequestList[position]
        holder.bindView(CR)
        holder.ViewDetails.setOnClickListener { onClickView?.invoke(CR) }
        holder.DeleteDetails.setOnClickListener { onClickDelete?.invoke(CR) }
    }

    override fun getItemCount(): Int {
        return CustomerRequestList.size
    }

    class CustomerRequestViewHolder(var view: View, var db: DataBaseHelper) : RecyclerView.ViewHolder(view) {
        private var Name = view.findViewById<TextView>(R.id.textViewCR_Name)
        private var Email = view.findViewById<TextView>(R.id.textViewCR_Email)
        private var Problem = view.findViewById<TextView>(R.id.textViewCR_Problem)
        var ViewDetails = view.findViewById<ImageView>(R.id.imageViewCR_ViewDetails)
        var DeleteDetails = view.findViewById<ImageView>(R.id.imageViewCR_DeleteDetails)

        fun bindView(customerRequest: CustomerQuery) {
            Name.text = customerRequest.CustomerQueryName
            Email.text = customerRequest.CustomerQueryEmail
            Problem.text = customerRequest.CustomerQueryProblem

        }

    }


}