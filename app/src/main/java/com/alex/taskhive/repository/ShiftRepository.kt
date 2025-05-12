package com.alex.taskhive.repository

import com.alex.taskhive.model.Shift

class ShiftRepository {
    fun getUserShift(): Shift {
        // In a real app, fetch this from Firebase, Room, etc.
        return Shift(day = "Today", time = "10:00 AM - 6:00 PM")
    }
}