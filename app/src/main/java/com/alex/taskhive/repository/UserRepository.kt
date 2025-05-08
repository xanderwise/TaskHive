package com.alex.taskhive.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.alex.taskhive.data.UserDao
import com.alex.taskhive.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository(
    private val userDao: UserDao
) {

    private val firestore = FirebaseFirestore.getInstance()

    suspend fun registerUser(name: String, emailOrPhone: String, password: String) {
        val adminCount = userDao.getAdminCount()
        val role = if (adminCount < 2) "admin" else "worker"

        val newUser = User(
            name = name,
            emailOrPhone = emailOrPhone,
            password = password,
            role = role
        )

        // Save to Room (local database)
        userDao.insertUser(newUser)

        // Save also to Firestore (online database)
        firestore.collection("users").add(newUser).await()
    }

    suspend fun getUserByEmailOrPhone(emailOrPhone: String): User? {
        return userDao.getUserByEmailOrPhone(emailOrPhone)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getAllUsers(): List<User> {
        val snapshot = firestore.collection("users").get().await()
        return snapshot.documents.mapNotNull { it.toObject(User::class.java) }
    }
}
