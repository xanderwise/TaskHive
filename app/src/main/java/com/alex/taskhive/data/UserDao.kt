package com.alex.taskhive.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alex.taskhive.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT COUNT(*) FROM users WHERE role = 'admin'")
    suspend fun getAdminCount(): Int

    @Query("SELECT * FROM users WHERE emailOrPhone = :emailOrPhone LIMIT 1")
    suspend fun getUserByEmailOrPhone(emailOrPhone: String): User?
}
