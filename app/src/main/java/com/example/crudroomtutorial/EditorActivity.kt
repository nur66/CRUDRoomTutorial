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

        // Fitur Edit
        val intent = intent.extras  // cara menangkap id yang di passing dari MainActivity
        if (intent!=null){
            val id = intent.getInt("id", 0)
            val user = database.userDao().get(id)   // gunakan function yang ada di database userDao

            fullName.setText(user.fullName)
            email.setText(user.email)
            phone.setText(user.phone)
        }


        // Fitur simpan
        btnSave.setOnClickListener {
            if (fullName.text.length > 0 && email.text.length > 0 && phone.text.length > 0) {
                if(intent!=null){
                    // coding edit data
                    database.userDao().update(
                        User(
                            intent.getInt("id", 0),
                            fullName.text.toString(),
                            email.text.toString(),
                            phone.text.toString()
                        )
                    )
                }else{
                    // coding tambah data
                    database.userDao().insertAll(
                        User(
                            null,
                            fullName.text.toString(),
                            email.text.toString(),
                            phone.text.toString()
                        )
                    )
                }
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