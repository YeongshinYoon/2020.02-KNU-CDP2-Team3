package com.example.a2020_02_cdp2_team3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val tabTextList = arrayListOf("메인화면", "상세현황", "검색", "그래프", "지도")
    //private val tabIconList = arrayListOf()
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        viewPager2 = findViewById(R.id.viewPager2)
        tabLayout = findViewById(R.id.tabLayout)

        viewPager2.adapter = CustomFragmentStateAdapter(this)
        TabLayoutMediator(tabLayout, viewPager2) {
            tab, position ->
            //tab.setIcon()
            tab.text = tabTextList[position]
        }.attach()
    }
}