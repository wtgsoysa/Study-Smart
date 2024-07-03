package com.example.studysmart.util

import androidx.compose.ui.graphics.Color
import com.example.studysmart.ui.theme.Green
import com.example.studysmart.ui.theme.Orange
import com.example.studysmart.ui.theme.Red
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

enum class Priority(val title: String, val color: Color, val value: Int) {
    Low(title = "Low", color = Green, value = 0),
    Medium(title = "Medium", color = Orange, value = 1),
    High(title = "High", color = Red, value = 2);

    companion object {
        fun fromInt(value: Int) = Priority.values().first { it.value == value }
    }
}

fun Long?.changeMillisToDate(): String {
    val date: LocalDate = this?.let{
        Instant
            .ofEpochMilli(it)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }?: LocalDate.now()
    return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

}
