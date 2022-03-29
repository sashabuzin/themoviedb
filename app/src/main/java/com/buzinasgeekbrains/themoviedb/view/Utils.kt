package com.buzinasgeekbrains.themoviedb.view

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(): String =
    SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(this)
