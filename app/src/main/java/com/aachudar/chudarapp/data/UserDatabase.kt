package com.aachudar.chudarapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aachudar.data.User
import com.aachudar.data.UserDao
import com.example.chudarapp.data.Converters

@Database(entities = [User::class, Problem::class], version = 3)
@TypeConverters(Converters::class)  // Only if you have custom converters
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun problemDao(): ProblemDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "math_practice_db"
                )
                    .fallbackToDestructiveMigration()  // ✅ Add this to reset db if schema changes
                    .build()
                INSTANCE = instance
                instance
            }
        }
}}
