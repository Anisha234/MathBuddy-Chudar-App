package com.aachudar.chudarapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "problems")
data class Problem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var userId: String = "global",         // "global" for preset, or actual user ID for AI
    var topic: String,                     // e.g., "Number Theory"
    val questionText: String,
    val choiceA: String,
    val choiceB: String,
    val choiceC: String,
    val choiceD: String,
    var correctAnswer: String,
    var subtopic: String, // store as "A", "B", "C", or "D"
    var source: String = "preset"         // "preset" or "ai"
)
