package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.ListItemBinding

class CustomAdapter(private var data: ArrayList<UserData>) : BaseAdapter() {

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val binding: ListItemBinding
        binding = if (view == null) {
            //TODO : inflate new layout for this row
            ListItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        } else {
            //TODO : Already have a view, populate it
            ListItemBinding.bind(view)
        }
        val contact = data[position]
        binding.txtName.text = contact.name
        binding.txtPhone.text = contact.phone
        binding.imageView.setImageDrawable(
            ContextCompat.getDrawable(
                binding.imageView.context,
                contact.avatar
            )
        )
        return binding.root
    }

}