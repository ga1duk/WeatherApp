package ru.mypackage.myweatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.mypackage.myweatherapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}