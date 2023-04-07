package com.example.crudroomtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.crudroomtutorial.data.AppDatabase
import com.example.crudroomtutorial.data.entity.User

//import com.example.crudroomtutorial.R     //Jika setelah Build > Rebuild Project masih ada error pada saat findViewById

class EditorActivity : AppCompatActivity() {
    private lateinit var fullName : EditText
    private lateinit var email : EditText
    private lateinit var phone : EditText
    private lateinit var btnSave : Button
    private lateinit var database : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        fullName = findViewById(R.id.full_name)
        email = findViewById(R.id.email)
        phone = findViewById(R.id.phone)
        btnSave = findViewById(R.id.btn_save)

        database = AppDatabase.getInstance(applicationContext)

        btnSave.setOnClickListener {
            if (fullName.text.length > 0 && email.text.length > 0 && phone.text.length > 0) {
                database.userDao().insertAll(
                    User(
                        null,
                        fullName.text.toString(),
                        email.text.toString(),
                        phone.text.toString()
                    )
                )
                finish()  // agar setelah selesai dia akan balik ke MainActivity
            } else {
                Toast.makeText(
                    applicationContext,
                    "Silahkan isi semua data dengan valid",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}