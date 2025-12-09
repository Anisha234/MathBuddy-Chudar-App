package com.aachudar.chudarapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun NumberTheoryQuizScreen(
    viewModel: UserViewModel,
    subtopic: String,
    onBack: () -> Unit
) {
    val problems by viewModel.problems.collectAsState(emptyList())
    var currentIndex by remember { mutableStateOf(0) }
    var selectedChoice by remember { mutableStateOf<String?>(null) }
    var showResult by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(0) }   // ✅ Track module score

    // Load problems for this subtopic
    LaunchedEffect(subtopic) {
        viewModel.loadProblemsBySubtopic("Number Theory", subtopic)
        currentIndex = 0
        selectedChoice = null
        showResult = false
        score = 0
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (problems.isNotEmpty() && currentIndex < problems.size) {
            val currentProblem = problems[currentIndex]

            // Progress indicator
            Text(
                text = "Question ${currentIndex + 1} of ${problems.size}",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Question text
            Text(
                text = currentProblem.questionText,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Choices
            listOf(
                "A" to currentProblem.choiceA,
                "B" to currentProblem.choiceB,
                "C" to currentProblem.choiceC,
                "D" to currentProblem.choiceD
            ).forEach { (label, text) ->
                Button(
                    onClick = {
                        selectedChoice = label
                        showResult = true
                        if (label == currentProblem.correctAnswer) {
                            score++ // ✅ increase score
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    enabled = !showResult  // prevent changing after answer
                ) {
                    Text(text)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Feedback + Next
            if (showResult) {
                Text(
                    if (selectedChoice == currentProblem.correctAnswer) "✅ Correct!"
                    else "❌ Wrong. Correct answer: ${currentProblem.correctAnswer}"
                )
                Button(
                    onClick = {
                        currentIndex++
                        showResult = false
                        selectedChoice = null
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Next Question")
                }
            }
        } else if (problems.isNotEmpty()) {
            // ✅ Quiz complete screen
            Text("Done! 🎉", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Your Score: $score out of ${problems.size}")

            // ✅ Add score to practice score (not tests)
            LaunchedEffect(Unit) {
                viewModel.addPracticeScore(score)
            }

            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onBack) {
                Text("Back")
            }
        }
    }
}
