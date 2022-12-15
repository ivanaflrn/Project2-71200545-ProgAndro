package com.example.project2_71200545

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class CatatanAdapter ( val row : ArrayList<Catatan>): RecyclerView.Adapter<CatatanAdapter.CatatanHolder>(){
    class CatatanHolder(val tamp: View): RecyclerView.ViewHolder(tamp){
        fun psgView(note: Catatan){
            tamp.findViewById<TextView>(R.id.title).setText(note.title)
            tamp.findViewById<Button>(R.id.del).setOnClickListener { res ->
                val fb = FirebaseFirestore.getInstance()
                fb.collection("catatan").document(note.key.toString()).delete().addOnSuccessListener {
                    Toast.makeText(res.context, "deleted successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(res.context, MainActivity::class.java)
                    tamp.context.startActivity(intent)
                }
            }
            tamp.setOnClickListener {
                val intent = Intent(it.context, Edit::class.java)
                intent.putExtra("key", note.key)
                tamp.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatatanHolder {
        val tamp = LayoutInflater.from(parent.context).inflate(R.layout.row_note, parent, false)
        return CatatanHolder(tamp)
    }

    override fun onBindViewHolder(holder: CatatanHolder, position: Int) {
        holder.psgView(row[position])
    }

    override fun getItemCount(): Int {
        return row.size
    }
}