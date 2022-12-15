package com.example.project2_71200545

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNew = findViewById<Button>(R.id.btnAdd)
        btnNew.setOnClickListener {
            val intent = Intent(this, NewNote::class.java)
            startActivity(intent)
        }

        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("catatan").orderBy("tgl", Query.Direction.DESCENDING).get()
            .addOnSuccessListener {
                var catatan = ArrayList<Catatan>()
                for (c in it){
                    catatan.add(Catatan(c.data?.get("title").toString(), c.data?.get("contain").toString(), c.data.get("tgl").toString(),c.id))
                }
                val rcv = findViewById<RecyclerView>(R.id.rcv)
                rcv.layoutManager = LinearLayoutManager(this)
                rcv.adapter = CatatanAdapter(catatan)
            }
    }
}