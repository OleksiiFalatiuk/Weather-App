package com.example.weatherapp.utils

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class ActivityExt {

    companion object{
        inline fun <reified T> Activity.toActivity() where T : AppCompatActivity {
            startActivity(Intent(this, T::class.java))
        }
        fun getDateMonthLong(timestamp: Long) :String {
            val timestampDate = Date(timestamp * 1000L)
            val dateFormat = SimpleDateFormat("MMM", Locale.ENGLISH)
            val dateFormatSecond = SimpleDateFormat("d", Locale.ENGLISH)
            val date = dateFormat.format(timestampDate).toString()
            val dateSecond = dateFormatSecond.format(timestampDate).toString()
            return "$dateSecond $date"
        }

        fun getDateMonthLongSize(timestamp: Long) :String {
            val timestampDate = Date(timestamp * 1000L)
            val dateFormat = SimpleDateFormat("MMMM", Locale.ENGLISH)
            val fullFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val date = dateFormat.format(timestampDate).toString()
            val dateFull = fullFormat.format(timestampDate).toString()
            return "$date\n$dateFull"
        }
    }

}