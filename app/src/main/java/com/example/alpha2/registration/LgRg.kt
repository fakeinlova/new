package com.example.alpha2.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.alpha2.MainSceen
import com.example.alpha2.R
import com.example.alpha2.model.User
import com.example.alpha2.setFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_regregreg.*
import kotlinx.android.synthetic.main.fragment_signupup.*

class LgRg : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lg_rg)
                val intent = intent
        if (intent!=null){
            userName = intent.getStringExtra("userName").toString()
        }
        userDatabaseReference = database.reference.child("users")

        val emailEditText: EditText = findViewById(R.id.editTextTextEmailAddress)
        val pasEditText: EditText = findViewById(R.id.editTextTextPassword)
        var toggleLoginSignUpTextView: Button = findViewById(R.id.button3)

        auth = Firebase.auth
        val loginSignUpButton: Button = findViewById(R.id.button2)

        loginSignUpButton.setOnClickListener {
            loginSignUpUser(emailEditText.text.toString().trim(), pasEditText.text.toString().trim())}
        if (auth.currentUser != null){
            startActivity(Intent(this, MainSceen::class.java))
        }
    }
    private lateinit var auth: FirebaseAuth
    private val TAG = "LogAndReg"
    private var loginModeActive = false

    val database = FirebaseDatabase.getInstance()
    var userDatabaseReference: DatabaseReference? = null
    var userName : String = "username"


    val loginFragment by lazy { Signupup() }
    val registrationFr by lazy { Regregreg() }
    private fun loginSignUpUser(email: String, password: String) {
        val emailEditText : EditText = findViewById(R.id.editTextTextEmailAddress)
        val nameEditText : EditText = findViewById(R.id.name)
        val passwordEditText : EditText = findViewById(R.id.editTextTextPassword)

        if (loginModeActive) {
            if(passwordEditText.text.toString().trim().length <7) {
                Toast.makeText(this, "Password must be at least 7 charactrrs", Toast.LENGTH_LONG).show()
            } else if(emailEditText.text.toString().trim().equals("")) {
                Toast.makeText(this, "Please input your email", Toast.LENGTH_LONG).show()
            }else{
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            val intent = Intent(this, MainSceen::class.java)
                            intent.putExtra("userName", nameEditText.text.toString().trim())
                            startActivity(intent )
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                            // updateUI(null)
                        }
                    }

            }
        } else {
            if (!passwordEditText.text.toString().equals(passwordEditText.text.toString())) {
                Toast.makeText(this, "Password dont mach", Toast.LENGTH_LONG).show()
            } else if(passwordEditText.text.toString().trim().length <8) {
                Toast.makeText(this, "Password must be at least 8 charactrrs", Toast.LENGTH_LONG).show()
            } else if(emailEditText.text.toString().trim().equals("")) {
                Toast.makeText(this, "Please input your email", Toast.LENGTH_LONG).show()
            }else{

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                            createUser(user)
                            val intent = Intent(this, MainSceen::class.java)
                            intent.putExtra("userName", nameEditText.text.toString().trim())
                            startActivity(intent)


                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                this, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }


//        fun toggleLoginMade(view: View) {
//            val loginSignUpButton: Button = findViewById(R.id.button2)
//            val toggleLoginSignUpTextView: TextView = findViewById(R.id.button3)
//
//            if (loginModeActive) {
//                loginModeActive = false
//                loginSignUpButton.text = "Sign up"
//                toggleLoginSignUpTextView.text = "Or , log in"
//                passwordEditText.visibility = View.VISIBLE
//            } else {
//                loginModeActive = true
//                loginSignUpButton.text = "Log in"
//                toggleLoginSignUpTextView.text = "Or , Sign up"
//                passwordEditText.visibility = View.GONE
//            }
//        }
    }


     fun createUser(fireuser: FirebaseUser?) {
        val nameEditText : EditText = findViewById(R.id.name)
        val user: User =
            User()
        user.id = fireuser!!.uid
        user.email = fireuser.email.toString()
        user.name = nameEditText.text.toString().trim()

        userDatabaseReference!!.push().setValue(user)

    }

    fun vhod(view: View){
            setFragment(this, R.id.frameframe, loginFragment)
    }

    fun zareg(view: View){
        val fio = editTextTextEmailAddress2.text.toString()
        val emailsec = editTextTextEmailAddresss.text.toString()
        val pass = editTextTextPasswordd.text.toString()
        val passsec = editTextTextPassword2.text.toString()
        if ( fio.isEmpty() || emailsec.isEmpty() || pass.isEmpty() || passsec.isEmpty()){
            Toast.makeText(this, "the field mast not be empty", Toast.LENGTH_SHORT).show()
        }else {
            startActivity(Intent(this, MainSceen::class.java))
        }
    }

    fun registrationfun(view: View){
        setFragment(this, R.id.frameframe ,  registrationFr)
    }

    fun login(view: View){
        val email = editTextTextEmailAddress.text.toString()
        val password = editTextTextPassword.text.toString()
        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "the field mast not be empty", Toast.LENGTH_SHORT).show()
        }else {
            startActivity(Intent(this, MainSceen::class.java))
        }
    }
}
