package com.alex.taskhive.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.taskhive.model.Shift
import com.alex.taskhive.repository.ShiftRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val repository = ShiftRepository()
    private val _shift = MutableStateFlow<Shift?>(null)
    val shift: StateFlow<Shift?> = _shift

    init {
        loadUserShift()
    }

    private fun loadUserShift() {
        viewModelScope.launch {
            _shift.value = repository.getUserShift()
        }
    }
}
