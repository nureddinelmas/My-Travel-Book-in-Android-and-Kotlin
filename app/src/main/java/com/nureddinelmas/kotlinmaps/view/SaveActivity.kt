package com.nureddinelmas.kotlinmaps.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.nureddinelmas.kotlinmaps.R

class SaveActivity : AppCompatActivity() {
    lateinit var textView: TextView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)
        textView = findViewById(R.id.textView)

        val intent = intent
         val  lastLatitude = intent.getDoubleExtra("lastLatitude", 0.0)
        val lastLongtitude = intent.getDoubleExtra("lastLongtitude",0.0)

        textView.text = ("$lastLatitude + \"   \" + $lastLongtitude")
    }
}