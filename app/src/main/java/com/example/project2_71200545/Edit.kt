package com.example.project2_71200545

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class Edit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val title = findViewById<EditText>(R.id.title)
        val contain = findViewById<EditText>(R.id.contain)

        val firebase = FirebaseFirestore.getInstance()
        val key = intent.getStringExtra("key").toString()
        firebase.collection("catatan").document(key).get().addOnSuccessListener {
            title.setText(it.data?.get("title").toString())
            contain.setText(it.data?.get("contain").toString())
        }

        val done = findViewById<Button>(R.id.done)
        done.setOnClickListener {
            firebase.collection("catatan").document(key).update("title", title.text.toString())
            firebase.collection("catatan").document(key).update("contain", contain.text.toString())
            Toast.makeText(this, "Data berhasil di simpan", Toast.LENGTH_SHORT).show()
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }


}