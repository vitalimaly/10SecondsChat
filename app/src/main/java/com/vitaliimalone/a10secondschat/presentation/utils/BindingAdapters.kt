package com.vitaliimalone.a10secondschat.presentation.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("time")
fun convertTime(textView: TextView, timeMillis: Long) {
    val simpleDateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val text = simpleDateFormat.format(Date(timeMillis))
    textView.text = text
}
