package com.example.realtimedatabasefirebase.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.realtimedatabasefirebase.R
import com.example.realtimedatabasefirebase.data.Note

class Adapter(val data : List<Note>) : RecyclerView.Adapter<Adapter.myHolder>() {
    class myHolder(row : View): ViewHolder(row) {
      var noteTitle = row.findViewById<TextView>(R.id.tv_title)
      var noteDesc = row.findViewById<TextView>(R.id.tv_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return  myHolder(row)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: myHolder, position: Int) {
        holder.noteTitle.text = data[position].noteTitle
        holder.noteDesc.text = data[position].noteDesc

    }
}