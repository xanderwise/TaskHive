package com.alex.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alex.taskhive.data.UserDao
import com.alex.taskhive.model.User


// Annotate the class to let Room know it's a Room Database
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    // This is where you define the DAO (Data Access Object) that Room will use
    abstract fun userDao(): UserDao

    companion object {
        // Volatile ensures that the instance is immediately visible across threads
        @Volatile
        private var INSTANCE: UserDatabase? = null

        // This method ensures that only one instance of the database is created (Singleton pattern)
        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                // If the instance is null, create the database
                val instance = Room.databaseBuilder(
                    context.applicationContext, // Use applicationContext to prevent memory leaks
                    UserDatabase::class.java,    // Reference to the UserDatabase class
                    "user_database"             // Name of the database file
                ).build()

                // Cache the instance for future use
                INSTANCE = instance
                instance
            }
        }
    }
}

