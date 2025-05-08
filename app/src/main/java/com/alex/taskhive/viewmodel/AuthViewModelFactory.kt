package com.alex.taskhive.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alex.taskhive.repository.UserRepository
import com.alex.taskhive.viewmodel.AuthViewModel

class AuthViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.Factory {

    // This method creates the ViewModel, injecting the repository
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
