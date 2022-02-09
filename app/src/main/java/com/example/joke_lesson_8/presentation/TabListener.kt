package com.example.joke_lesson_8.presentation

import android.widget.TableLayout
import com.google.android.material.tabs.TabLayout

class TabListener(private val tabChosen: (Boolean) ->Unit): TabLayout.OnTabSelectedListener {
    override fun onTabSelected(tab: TabLayout.Tab?) {
        return tabChosen.invoke(tab?.position == 0 )
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) = Unit

    override fun onTabReselected(tab: TabLayout.Tab?) = Unit
}