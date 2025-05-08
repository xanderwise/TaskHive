package com.alex.taskhive.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.taskhive.model.User
import com.alex.taskhive.repository.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val userRepository: UserRepository) : ViewModel() {

    var registrationSuccess by mutableStateOf(false)
        private set

    var loginSuccess by mutableStateOf(false)
        private set

    var loggedInUser by mutableStateOf<User?>(null)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        internal set

    fun registerUser(name: String, emailOrPhone: String, password: String, role: String) {
        viewModelScope.launch {
            try {
                userRepository.registerUser(name, emailOrPhone, password)
                registrationSuccess = true
                errorMessage = null
            } catch (e: Exception) {
                registrationSuccess = false
                errorMessage = e.message
            }
        }
    }

    fun loginUser(emailOrPhone: String, password: String) {
        viewModelScope.launch {
            try {
                val user = userRepository.getUserByEmailOrPhone(emailOrPhone)
                if (user != null && user.password == password) {
                    loggedInUser = user
                    loginSuccess = true
                    errorMessage = null
                } else {
                    loginSuccess = false
                    errorMessage = "Invalid credentials"
                }
            } catch (e: Exception) {
                loginSuccess = false
                errorMessage = e.message
            }
        }
    }

    fun resetAuthState() {
        registrationSuccess = false
        loginSuccess = false
        errorMessage = null
    }
}
