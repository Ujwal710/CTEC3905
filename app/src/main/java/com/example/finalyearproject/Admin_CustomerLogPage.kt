package com.example.finalyearproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalyearproject.Model.Customer
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
//        adapter?.setOnClickEdit {
//            val layout = layoutInflater.inflate(R.layout.layouteditingcustomerlogs, null)
//            val builder = AlertDialog.Builder(this)
//            val db = DataBaseHelper(this)
//
//
//        }
        adapter?.setOnClickEdit {
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.layouteditingcustomerlogs, null)
            builder.setView(dialogView)
            val dialog = builder.create()
            dialog.window?.setBackgroundDrawableResource(R.color.matt_black)

            dialog.show()

            val Save = dialogView.findViewById<Button>(R.id.buttonESave)
            val Cancel = dialogView.findViewById<Button>(R.id.buttonECancel)

            val db = DataBaseHelper(this)
            val ECLFN = dialogView.findViewById<EditText>(R.id.editTextEditCustomerLogName)
            val ECLS = dialogView.findViewById<EditText>(R.id.editTextEditCustomerLogSurname)
            val ECLE = dialogView.findViewById<EditText>(R.id.editTextEditCustomerLogEmail)
            val ECLA = dialogView.findViewById<EditText>(R.id.editTextEditCustomerLogAddress)
            val ECLPC = dialogView.findViewById<EditText>(R.id.editTextEditCustomerLogPostCode)
            val ECLN = dialogView.findViewById<EditText>(R.id.editTextEditCustomerLogNumber)


            ECLFN.setText(it.FirstName)
            ECLS.setText(it.Surname)
            ECLE.setText(it.Email)
            ECLA.setText(it.Address)
            ECLPC.setText(it.PostCode)
            ECLN.setText(it.Number)

            val customerid = db.getCustomeridbyusername(it.UserName).toInt()


            Save.setOnClickListener {
                val ChangeECLFN = ECLFN.text.toString()
                val ChangeECLS = ECLS.text.toString()
                val ChangeECLE = ECLE.text.toString()
                val ChangeECLA = ECLA.text.toString()
                val ChangeECLPC = ECLPC.text.toString()
                val ChangeECLN = ECLN.text.toString()


                val customer = Customer(
                    customerid,
                    ChangeECLFN,
                    ChangeECLS,
                    ChangeECLE,
                    ChangeECLA,
                    ChangeECLPC,
                    ChangeECLN,
                   "",
                    "",
                    -1,
                    ""
                )




                db.updateCustomerLog(customer)

                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, Admin_CustomerLogPage::class.java)
                startActivity(intent)
                finish()
            }

            Cancel.setOnClickListener {
                dialog.dismiss()
            }
        }



        adapter?.setOnClickView {
            val layout = layoutInflater.inflate(R.layout.layoutpreview, null)
            val builder = AlertDialog.Builder(this)


            val db = DataBaseHelper(this)
            val title = layout.findViewById<TextView>(R.id.dialog_title)
            val cancel = layout.findViewById<Button>(R.id.buttondialogcancel)

            val CFN = layout.findViewById<TextView>(R.id.textViewCFN)
            val CS = layout.findViewById<TextView>(R.id.textViewCS)
            val CE = layout.findViewById<TextView>(R.id.textViewCE)
            val CA = layout.findViewById<TextView>(R.id.textViewCA)
            val CPC = layout.findViewById<TextView>(R.id.textViewCPC)
            val CN = layout.findViewById<TextView>(R.id.textViewCN)
            val CUN = layout.findViewById<TextView>(R.id.textViewCUN)
            val CP = layout.findViewById<TextView>(R.id.textViewCP)
            val CSA = layout.findViewById<TextView>(R.id.textViewCSA)
            val CSQ = layout.findViewById<TextView>(R.id.textViewCSQ)

            val CSAinString = db.getSecurityQuestionbyID(it.RSecurityQuestionId.toString())

            CFN.setText(it.FirstName)
            CS.setText(it.Surname)
            CE.setText(it.Email)
            CA.setText(it.Address)
            CPC.setText(it.PostCode)
            CN.setText(it.Number)
            CUN.setText(it.UserName)
            CP.setText(it.Password)
            CSQ.setText(CSAinString)
            CSA.setText(it.Answer)

            builder.setView(layout)

            //builder.setNegativeButton("Cancel") { _, _ ->}
            val dialog = builder.create()
            cancel.setOnClickListener { dialog.dismiss() }
            dialog.window?.setBackgroundDrawableResource(R.color.matt_black)

            dialog.show()

        }

        adapter?.setOnClickDelete {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Warning!!!")
            builder.setMessage("Are you sure you would like to delete this customerLog?")
            builder.setPositiveButton("Yes"){_,_ ->
                getDatabase().deleteCustomerLog(it.CustomerId)
                Toast.makeText(this, "Customer deleted Successfully", Toast.LENGTH_SHORT).show()
               val intent = Intent(this, Admin_CustomerLogPage::class.java)
               startActivity(intent)
            }
            builder.setNegativeButton("No"){ _,_ ->}
            val dialog = builder.create()
            dialog.show()
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

