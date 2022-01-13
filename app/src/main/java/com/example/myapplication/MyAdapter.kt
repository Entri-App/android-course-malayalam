package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private var data: ArrayList<UserData>) :
    RecyclerView.Adapter<MyAdapter.ContactVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ContactVH(view)
    }

    override fun onBindViewHolder(holder: ContactVH, position: Int) {
        val dataItem = data[position]
        val txtName = holder.itemView.findViewById<TextView>(R.id.txt_name)
        val txtPhone = holder.itemView.findViewById<TextView>(R.id.txt_phone)
        val imageView = holder.itemView.findViewById<ImageView>(R.id.imageView)

        txtName.text = dataItem.name
        txtPhone.text = dataItem.phone
        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, dataItem.avatar))

    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class ContactVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}