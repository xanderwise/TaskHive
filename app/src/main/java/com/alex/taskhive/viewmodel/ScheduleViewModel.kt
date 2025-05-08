package com.alex.taskhive.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class ScheduleViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()

    fun saveSchedule(workerId: String, schedule: Map<String, Boolean>) {
        viewModelScope.launch {
            try {
                firestore.collection("schedules")
                    .document(workerId)
                    .set(schedule)
                    .addOnSuccessListener {
                        // Optionally log success or show UI feedback
                    }
                    .addOnFailureListener {
                        // Optionally log or handle error
                    }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
