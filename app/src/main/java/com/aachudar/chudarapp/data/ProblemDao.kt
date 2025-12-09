package com.aachudar.chudarapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProblemDao {
    @Query("SELECT * FROM problems WHERE topic = :topic")
    suspend fun getProblemsByTopic(topic: String): List<Problem>

    @Insert
    suspend fun insertAll(problems: List<Problem>)
    @Insert
    suspend fun insertProblem(problem: Problem)

    @Query("SELECT * FROM problems")
    suspend fun getAllProblems(): List<Problem>

    @Query("SELECT * FROM problems WHERE topic = :topic AND subtopic = :subtopic")
    suspend fun getProblemsBySubtopic(topic: String, subtopic: String): List<Problem>

    @Query("""
    SELECT * FROM problems
    WHERE 
        (userId = :userId AND source = 'ai') OR 
        (userId = 'global' AND source = 'preset')
    ORDER BY id
""")
    suspend fun getProblemsForUser(userId: String): List<Problem>


}
