package com.alex.taskhive.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val emailOrPhone: String,
    val password: String,
    val role: String = "worker" // Default role is worker
)
