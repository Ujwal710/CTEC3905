package com.example.finalyearproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.finalyearproject.Model.DataBaseHelper

class ForgetPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        val db = DataBaseHelper(this)


    }

    fun verify(view: View){
        val email = findViewById<EditText>(R.id.editTextTextFPEmail)
        val db = DataBaseHelper(this)

        val securityQtitle = findViewById<TextView>(R.id.textViewForgotPSecurityQuestiontitle)
        val securityQ = findViewById<TextView>(R.id.textViewForgotPSecurityQuestion)


        val answerbox = findViewById<EditText>(R.id.editTextTextForgotPSecurityQuestionResponse)
        val buttoncontinue = findViewById<Button>(R.id.buttonMovetoResetPassword)

        val result = db.CheckCustomerEmail(email.text.toString())
        if(result==1){
            securityQtitle.visibility = View.VISIBLE
            securityQ.visibility = View.VISIBLE
            answerbox.visibility = View.VISIBLE
            buttoncontinue.visibility = View.VISIBLE

            val securiyid= db.getSecurityQuestionIdbyEmail(email.text.toString())
            val questiontext = db.getSecurityQuestionbyID(securiyid.toString())
            securityQ.text = questiontext.toString()
            val correctanswer = db.checkanswerbyemail(email.text.toString())

            buttoncontinue.setOnClickListener { continubutton() }

        }else{
            Toast.makeText(this, "Please check your email: Wrong email", Toast.LENGTH_SHORT).show()

        }

    }

    private fun continubutton() {
        val db = DataBaseHelper(this)
        val email = findViewById<EditText>(R.id.editTextTextFPEmail)

        val answerbox = findViewById<EditText>(R.id.editTextTextForgotPSecurityQuestionResponse)

        val correctanswer = db.checkanswerbyemail(email.text.toString())

        if(correctanswer.equals(answerbox.text.toString())){
            Toast.makeText(this, "goes to reset passwrd page", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ResetPassword::class.java)
            intent.putExtra("Customer_email", email.text.toString())
            startActivity(intent)
        }else{
            Toast.makeText(this, "does not match", Toast.LENGTH_SHORT).show()

        }
    }


}
