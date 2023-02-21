package com.example.finalyearproject.Model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper


private val DatabaseName = "FYPDatabase.db"
private val ver : Int = 1
class DataBaseHelper (context: Context) : SQLiteOpenHelper(context, DatabaseName, null,ver){

    /**Customer Table**/
    private val CustomerTableName = "Customer"
    private val Column_CustomerId = "CustomerId"
    private val Column_CustomerFirstName = "FirstName"
    private val Column_CustomerSurname = "Surname"
    private val Column_CustomerEmail = "Email"
    private val Column_CustomerAddress = "Address"
    private val Column_CustomerPostCode = "PostCode"
    private val Column_CustomerNumber = "Number"
    private val Column_CustomerUserName = "UserName"
    private val Column_CustomerPassword = "Password"

    /**Admin Table**/
    private val AdminTableName = "Admin"
    private val Column_AdminId = "AdminId"
    private val Column_AdminUserName = "AdminUserName"
    private val Column_AdminPassword = "AdminPassword"

    /**Customer request**/
    private val CustomerQueryTableName = "CustomerQuery"
    private val Column_CustomerQueryId = "CustomerQueryId"
    private val Column_CustomerQueryName = "CustomerQueryName"
    private val Column_CustomerQueryEmail = "CustomerQueryEmail"
    private val Column_CustomerQueryProblem = "CustomerQueryProblem"
    private val Column_CustomerQueryProblemDescription = "CustomerQueryProblemDescription"



    override fun onCreate(db: SQLiteDatabase?) {
        try {
            /*********************--- Customer ---*************************/
            var sqlCreateStatement: String = " CREATE TABLE " + CustomerTableName + " ( " + Column_CustomerId +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_CustomerFirstName + " TEXT NOT NULL, " +
                    Column_CustomerSurname + " TEXT NOT NULL, " + Column_CustomerEmail + " TEXT NOT NULL, " +
                    Column_CustomerAddress + " TEXT NOT NULL, " + Column_CustomerPostCode + " TEXT NOT NULL, " +
                    Column_CustomerNumber + " TEXT NOT NULL, " + Column_CustomerUserName + " TEXT NOT NULL, " +
                    Column_CustomerPassword + " TEXT NOT NULL "
            db?.execSQL(sqlCreateStatement)

            /*********************--- Admin ---*************************/
             sqlCreateStatement = " CREATE TABLE " + AdminTableName + " ( " + Column_AdminId +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_AdminUserName + " TEXT NOT NULL, " +
                            Column_AdminPassword + " TEXT NOT NULL "
            db?.execSQL(sqlCreateStatement)

            /*********************--- CustomerQuery ---*************************/
            sqlCreateStatement = " CREATE TABLE " + CustomerQueryTableName + " ( " + Column_CustomerQueryId +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_CustomerQueryName + " TEXT NOT NULL, " +
                    Column_CustomerQueryEmail + " TEXT NOT NULL, " + Column_CustomerQueryProblem + " TEXT NOT NULL, " +
                    Column_CustomerQueryProblemDescription + " TEXT NOT NULL "
            db?.execSQL(sqlCreateStatement)

        } catch (e: SQLiteException) {}
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    /*********************--- AddCustomer ---*************************/

    fun AddCustomer(customer: Customer): Int {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()
        val usernameexitst = CheckCustomerLoginName(customer)
        if (usernameexitst == 0) {

            cv.put(Column_CustomerFirstName, customer.FirstName)
            cv.put(Column_CustomerSurname, customer.Surname)
            cv.put(Column_CustomerEmail, customer.Email)
            cv.put(Column_CustomerAddress, customer.Address)
            cv.put(Column_CustomerPostCode, customer.PostCode)
            cv.put(Column_CustomerNumber, customer.Number)
            cv.put(Column_CustomerUserName, customer.UserName)
            cv.put(Column_CustomerPassword, customer.Password)


            val success = db.insert(CustomerTableName, null, cv)

            db.close()
            if (success.toInt() == -1) return success.toInt()
            else return 1
        }else{
            return -3 // user already exists
        }
    }

    /*********************--- CheckCustomerLoginName ---*************************/

    private fun CheckCustomerLoginName(customer: Customer): Int {
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }
        val CustomerUsername = customer.UserName.lowercase()

        val sqlStatement = "SELECT * FROM $CustomerTableName WHERE $Column_CustomerUserName = ?"
        val param = arrayOf(CustomerUsername)
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)

        if(cursor.moveToFirst()){
            // The user is found
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return -3 // error the user name is already exist
        }

        cursor.close()
        db.close()
        return 0
    }

    /*********************--- getCustomer ---*************************/

    fun getCustomer (Username : String, Password : String) : Int {
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }
        val CustomerUsername = Username.lowercase()
        val CustomerPassword = Password
        val sqlStatement = "SELECT * FROM $CustomerTableName WHERE $Column_CustomerUserName = ? AND $Column_CustomerPassword = ?"
        val param = arrayOf(CustomerUsername,CustomerPassword)
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)
        if(cursor.moveToFirst()){
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return n
        }
        cursor.close()
        db.close()
        return -1
    }
    /*********************---Get CustomerDetails---***********************/

    fun GetCustomerDetails(customer: String): Customer{

        val db: SQLiteDatabase = this.readableDatabase
        val CustomerUsername = customer
        val sqlStatement = "SELECT * FROM $CustomerTableName WHERE $Column_CustomerUserName = ?"
        val param = arrayOf(CustomerUsername)
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)
        if (cursor.moveToFirst()) {

            var CustomerId = cursor.getInt(0)
            var CustomerfirstName = cursor.getString(1)
            var CustomerSurname = cursor.getString(2)
            var CustomerEmail = cursor.getString(3)
            var CustomerAddress = cursor.getString(4)
            var CustomerPostCode = cursor.getString(5)
            var CustomerNumber = cursor.getString(6)
            var CustomerUsername = cursor.getString(7)
            var CustomerPassword = cursor.getString(8)
            var customer = Customer(
                CustomerId,
                CustomerfirstName,
                CustomerSurname,
                CustomerEmail,
                CustomerAddress,
                CustomerPostCode,
                CustomerNumber,
                CustomerUsername,
                CustomerPassword
            )

            return customer
        } else {
            val customer = Customer(0, "", "", "", "", "", "", "", "")
            return customer
        }
    }

    /*********************--- Admin login ---*************************/

    fun getAdmin (Admin_Username: String, Admin_Password: String): Int{
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }
        val AdminUserName = Admin_Username
        val AdminPassword = Admin_Password
        val sqlStatement = "SELECT * FROM $AdminTableName WHERE $Column_AdminUserName = ? AND $Column_AdminPassword = ?"
        val param = arrayOf(AdminUserName,AdminPassword)
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)
        if(cursor.moveToFirst()){
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return n
        }
        cursor.close()
        db.close()
        return -1
    }

    /*********************--- CustomerLogAdapter ---*************************/

    fun getArrayofCustomerLogfromdatabase():ArrayList<Customer>{
        val customerList = ArrayList<Customer>()
        val database: SQLiteDatabase = this.readableDatabase
        val sql_statement = "SELECT * FROM $CustomerTableName"
        val cursor: Cursor = database.rawQuery(sql_statement, null)
        if (cursor.moveToNext()){
            do {
                val id : Int = cursor.getInt(0)
                val firstname: String = cursor.getString(1)
                val surname : String = cursor.getString(2)
                val email : String = cursor.getString(3)
                val address : String = cursor.getString(4)
                val postcode : String = cursor.getString(5)
                val number : String = cursor.getString(6)
                val username : String = cursor.getString(7)
                val password : String = cursor.getString(8)
                val custorlogdisplay = Customer(id, firstname, surname, email, address, postcode, number,username, password)
                customerList.add(custorlogdisplay)
            } while (cursor.moveToNext())
            cursor.close()
            database.close()
        }
        return customerList
    }

    /*********************--- CustomerQuery ---*************************/
    fun AddCustomerQuerys(customerQuery: CustomerQuery): Int{
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()


        cv.put(Column_CustomerQueryName, customerQuery.CustomerQueryName)
        cv.put(Column_CustomerQueryEmail, customerQuery.CustomerQueryEmail)
        cv.put(Column_CustomerQueryProblem, customerQuery.CustomerQueryProblem)
        cv.put(Column_CustomerQueryProblemDescription, customerQuery.CustomerQueryProblemDescription)

        val success = db.insert(CustomerQueryTableName, null, cv)

        db.close()
        if (success.toInt() == -1) return success.toInt()
        else return 1
    }

    /*********************--- CustomerQueryAdapter ---*************************/
    fun getArrayofCustomerRequestsfromdatabase(): ArrayList<CustomerQuery>{
        val customerrequestList = ArrayList<CustomerQuery>()
        val database: SQLiteDatabase = this.readableDatabase
        val sql_statement = "SELECT * FROM $CustomerQueryTableName"
        val cursor: Cursor = database.rawQuery(sql_statement, null)
        if (cursor.moveToNext()){
            do {
                val id : Int = cursor.getInt(0)
                val name: String = cursor.getString(1)
                val email : String = cursor.getString(2)
                val problem : String = cursor.getString(3)
                val problemdescription : String = cursor.getString(4)
                val custumerRequestdisplay = CustomerQuery(id, name, email, problem, problemdescription)
                customerrequestList.add(custumerRequestdisplay)
            } while (cursor.moveToNext())
            cursor.close()
            database.close()
        }
        return customerrequestList

    }
}


