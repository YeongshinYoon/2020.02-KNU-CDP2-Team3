package com.example.a2020_02_cdp2_team3



import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button


class detailMain : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_detail)
        val b1 = findViewById<View>(R.id.age) as Button
        val b2 = findViewById<View>(R.id.sex) as Button
        var b3 = findViewById<View>(R.id.area) as Button
        b1.setOnClickListener {
            val intent = Intent(this, ageAct::class.java)
            startActivity(intent)
        }
        b2.setOnClickListener {
            val intent = Intent(this, sexAct::class.java)
            startActivity(intent)
        }
        b3.setOnClickListener {
            val intent = Intent(this, areaAct::class.java)
            startActivity(intent)
        }
    }
}