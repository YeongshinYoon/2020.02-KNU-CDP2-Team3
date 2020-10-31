package com.example.a2020_02_cdp2_team3

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CustomFragmentStateAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            1 -> DetailFragment()
            2 -> SearchFragment()
            3 -> GraphFragment()
            4 -> MapFragment()
            else -> MainFragment()
        }
    }
}