package com.aachudar.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val username: String,
    val score: Int = 0,
    val completedProblemIds: List<Int> = emptyList(),
    val completedTests: String = "",
    val numTestsPassed: Int = 0,
)
