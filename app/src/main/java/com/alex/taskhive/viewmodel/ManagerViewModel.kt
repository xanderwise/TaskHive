package com.alex.taskhive.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.taskhive.model.User
import com.alex.taskhive.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ManagerViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _workers = MutableStateFlow<List<User>>(emptyList())
    val workers: StateFlow<List<User>> = _workers

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadRegisteredWorkers() {
        viewModelScope.launch {
            val allUsers = userRepository.getAllUsers()
            val workersList = allUsers.filter { it.role == "worker" }
            _workers.value = workersList
        }
    }
}
