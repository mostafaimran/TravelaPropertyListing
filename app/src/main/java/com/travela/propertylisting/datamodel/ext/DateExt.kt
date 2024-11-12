package com.travela.propertylisting.datamodel.ext

import com.travela.propertylisting.data.Constants.SERVER_DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.getLogDateFormat(): String {
    val sdf = SimpleDateFormat(
        SERVER_DATE_FORMAT,
        Locale.ENGLISH
    )
    return sdf.format(this)
}