package com.aachudar.data

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByUsername(username: String): User?

    @Update
    suspend fun updateUser(user: User)

    @Query("UPDATE users SET completedTests = :tests WHERE username = :username")
    suspend fun updateCompletedTests(username: String, tests: String)

    @Query("SELECT completedTests FROM users WHERE username = :username")
    suspend fun getCompletedTests(username: String): String


}
