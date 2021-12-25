package com.example.myapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.fragments.CallsFragment
import com.example.myapplication.fragments.ChatFragment
import com.example.myapplication.fragments.StatusFragment

class MyAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0){
            return ChatFragment()
        }
        if (position == 1) {
            return StatusFragment()
        }
        return CallsFragment()
    }
}