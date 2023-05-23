package com.example.weatherapp.utils

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class ActivityExt {

    companion object{
        inline fun <reified T> Activity.toActivity() where T : AppCompatActivity {
            startActivity(Intent(this, T::class.java))
        }
    }

}