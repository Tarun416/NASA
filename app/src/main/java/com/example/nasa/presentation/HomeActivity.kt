package com.example.nasa.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nasa.R
import com.example.nasa.presentation.list.ListFragment

class HomeActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction()
            .replace(R.id.content, ListFragment.newInstance())
            .commit()
    }

}