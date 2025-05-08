package com.alex.taskhive.model

import java.time.LocalDate

data class Schedule(
    val workerId: Long,
    val workerName: String,
    val workDays: List<LocalDate>, // List of dates the worker is scheduled to work
    val offDays: List<LocalDate>   // List of dates the worker is off
)
