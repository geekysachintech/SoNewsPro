package com.mrmobo.sonews.util

import android.annotation.SuppressLint
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("ConstantLocale")
val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
@SuppressLint("ConstantLocale")
val appDateFormat = SimpleDateFormat("MMMM dd, YYYY", Locale.getDefault())
fun TextView.showFormattedDate(timestamp: String){
    val date = isoDateFormat.parse(timestamp)
    text = appDateFormat.format(date)
}