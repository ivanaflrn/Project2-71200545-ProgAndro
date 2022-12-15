package com.example.project2_71200545

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class NewNote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        val firestore = FirebaseFirestore.getInstance()
        val title = findViewById<EditText>(R.id.title)
        val contain = findViewById<EditText>(R.id.contain)
        val submit = findViewById<Button>(R.id.done)
        submit.setOnClickListener {
            var title = title.text.toString()
            var contain = contain.text.toString()
            var tgl = SimpleDateFormat("dd/MM/yyy hh:mm:ss")
            var date = tgl.format(Date())

            val c = Catatan(title, contain, date)
            firestore.collection("catatan").add(c).addOnSuccessListener {
                Toast.makeText(this, "Berhasil di input", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }.addOnFailureListener {
                Toast.makeText(this, "gagal di simpan", Toast.LENGTH_SHORT).show()
            }
        }

    }
}