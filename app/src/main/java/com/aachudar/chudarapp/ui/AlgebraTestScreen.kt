package com.aachudar.chudarapp.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aachudar.chudarapp.data.algebraSolutions
import com.aachudar.chudarapp.data.getPresetTestAlgebra
import com.aachudar.chudarapp.data.numTheorySolutions
import com.aachudar.chudarapp.utils.generateSolutionsPdfAlgebra
import com.aachudar.chudarapp.utils.generateSolutionsPdfNumberTheory
import kotlinx.coroutines.delay

@Composable
fun AlgebraTestScreen(
    userViewModel: UserViewModel,   // <-- add this
    onBack: () -> Unit
) {
    // Fetch problems from your test bank
    val problems = remember { getPresetTestAlgebra(1) }

    var currentIndex by remember { mutableStateOf(0) }
    var selectedChoice by remember { mutableStateOf<String?>(null) }
    var showResult by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(0) }
    var testEnded by remember { mutableStateOf(false) }

    // Timer state (20 minutes = 1200 seconds)
    val totalTime = 20 * 60
    var timeLeft by remember { mutableStateOf(totalTime) }

    // Countdown effect
    LaunchedEffect(Unit) {
        while (timeLeft > 0 && !testEnded) {
            delay(1000L)
            timeLeft--
        }
        if (!testEnded) {
            testEnded = true // auto end test
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Text(
            text = "Algebra Test",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Timer + Progress stacked vertically
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Time Left: ${timeLeft / 60}:${(timeLeft % 60).toString().padStart(2, '0')}",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Q ${currentIndex + 1} of ${problems.size}",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (testEnded) {
            Text(
                text = if (timeLeft == 0) "Time's up! Your score: $score / ${problems.size}"
                else "Test Complete! Your score: $score / ${problems.size}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            if (currentIndex == problems.size - 1) {
                testEnded = true

                // ✅ Update passed tests if score >= 12
                if (score >= 12) {
                    // Launch a coroutine to update user in DB
                    LaunchedEffect(Unit) {
                        userViewModel.incrementTestsPassed()
                    }

                }
            }
            val context = LocalContext.current
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                val file = generateSolutionsPdfAlgebra(context, problems, algebraSolutions)
                Toast.makeText(context, "Saved to ${file.absolutePath}", Toast.LENGTH_LONG).show()
            }) {
                Text("Download Solutions PDF")
            }


        } else if (currentIndex < problems.size) {
            val problem = problems[currentIndex]

            // Question
            Text(
                text = problem.questionText,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Answer options
            listOf(
                "A" to problem.choiceA,
                "B" to problem.choiceB,
                "C" to problem.choiceC,
                "D" to problem.choiceD
            ).forEach { (label, text) ->
                Button(
                    onClick = {
                        selectedChoice = label
                        showResult = true
                        if (label == problem.correctAnswer) score++
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    enabled = !showResult // disable after answering
                ) {
                    Text(text)
                }
            }

            // Result feedback + Next
            if (showResult) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    if (selectedChoice == problem.correctAnswer) "Correct!"
                    else "Wrong. Correct answer: ${problem.correctAnswer}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        if (currentIndex == problems.size - 1) {
                            testEnded = true
                        } else {
                            currentIndex++
                            selectedChoice = null
                            showResult = false
                        }
                    }
                ) {
                    Text("Next Question")
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Back button at bottom (disabled until test ends)
        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(),
            enabled = testEnded
        ) {
            Text("Back to Menu")
        }
    }
}
