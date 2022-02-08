package com.example.joke_lesson_8.presentation.viewPager

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.joke_lesson_8.presentation.fragments.JokesFragment
import com.example.joke_lesson_8.presentation.fragments.QuotesFragment

class PagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if(position == 0){
            JokesFragment()
        } else QuotesFragment()
    }
}