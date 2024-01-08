package com.personal.unihub.ui.timetable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

@Preview
@Composable
fun CalenderLayoutPreview() {
    CalendarLayout(LocalDate.now())
}

@Composable
fun CalendarLayout(currentDate: LocalDate) {
    Column {
        MonthView(currentDate)
        WeekView(currentDate)
        DayView(currentDate)
    }
}

@Composable
fun MonthView(currentDate: LocalDate) {
    val daysInMonth: Int = currentDate.month.length(currentDate.isLeapYear)
    val firstDayOfMonth: LocalDate = currentDate.withDayOfMonth(1)
    val daysOfWeek: Array<DayOfWeek> = DayOfWeek.entries.toTypedArray()

    Column {
        Row {
            daysOfWeek.forEach { dayOfWeek: DayOfWeek ->
                Text(text = dayOfWeek.name.take(3), modifier = Modifier.weight(1f))
            }
        }
        for (i: Int in 0 until daysInMonth step 7) {
            Row {
                for (j: Int in i until minOf(i + 7, daysInMonth)) {
                    val date: LocalDate = firstDayOfMonth.plusDays(j.toLong())
                    Text(text = date.dayOfMonth.toString(), modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun WeekView(currentDate: LocalDate) {
    val firstDayOfWeek: LocalDate = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
    val daysOfWeek: Array<DayOfWeek> = DayOfWeek.entries.toTypedArray()

    Row {
        daysOfWeek.forEach {
            val date: LocalDate = firstDayOfWeek.plusDays(it.ordinal.toLong())
            Text(text = date.dayOfMonth.toString(), modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun DayView(currentDate: LocalDate) {
    Text(text = "Day: ${currentDate.dayOfMonth}")
}
