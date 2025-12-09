package com.aachudar.chudarapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aachudar.chudarapp.data.Problem

@Composable
fun TestScreen(
    testProblems: List<Problem>,
    testLabel: String,
    userViewModel: UserViewModel,
    onTestComplete: (score: Int) -> Unit,
    onBack: () -> Unit
) {
    var currentIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var showFeedback by remember { mutableStateOf(false) }
    var testFinished by remember { mutableStateOf(false) }

    val incorrectByTopic = remember { mutableStateMapOf<String, Int>() }
    val currentProblem = testProblems.getOrNull(currentIndex)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("📝 Test Mode", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(12.dp))

        Text("Score: $score / 20", fontSize = 20.sp, color = Color(0xFF009688))

        Spacer(modifier = Modifier.height(24.dp))

        if (testFinished || currentProblem == null) {
            Text("🎉 Test Complete!", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)

            Spacer(modifier = Modifier.height(16.dp))

            Text("Final Score: $score / 20", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(20.dp))

            if (incorrectByTopic.isNotEmpty()) {
                Text("❌ Mistakes by Topic:", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(10.dp))
                incorrectByTopic.forEach { (topic, count) ->
                    Text("- $topic: $count incorrect", fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            // ✅ Save test completion to DB once
            val currentUser by userViewModel.currentUser.collectAsState()


            if (currentUser != null && !currentUser!!.completedTests.contains(testLabel)) {
                LaunchedEffect(Unit) {
                    val updated = currentUser!!.completedTests
                        .split(",")
                        .filter { it.isNotBlank() }
                        .toMutableSet()
                    updated.add(testLabel)
                    val updatedCsv = updated.joinToString(",")
                    userViewModel.updateCompletedTests(currentUser!!.username, updatedCsv)
                }
            }

            Button(onClick = onBack) {
                Text("Return to Practice")
            }
        } else {
            Text(currentProblem.questionText, fontSize = 20.sp, textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(16.dp))

            listOf(
                "A" to currentProblem.choiceA,
                "B" to currentProblem.choiceB,
                "C" to currentProblem.choiceC,
                "D" to currentProblem.choiceD
            ).forEach { (label, choiceText) ->
                Button(
                    onClick = {
                        if (!showFeedback) {
                            selectedAnswer = label
                            showFeedback = true
                            if (label == currentProblem.correctAnswer) {
                                score++
                            } else {
                                incorrectByTopic[currentProblem.topic] =
                                    (incorrectByTopic[currentProblem.topic] ?: 0) + 1
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (showFeedback && label == selectedAnswer) {
                            if (label == currentProblem.correctAnswer) Color(0xFFB2FF59) else Color(0xFFFF8A80)
                        } else MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(choiceText)
                }
            }

            if (showFeedback) {
                Spacer(modifier = Modifier.height(12.dp))
                val correct = selectedAnswer == currentProblem.correctAnswer
                Text(
                    text = if (correct) "✅ Correct!" else "❌ Incorrect. Correct Answer: ${currentProblem.correctAnswer}",
                    fontSize = 18.sp,
                    color = if (correct) Color.Green else Color.Red
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (!testFinished) {
                    Button(
                        onClick = {
                            if (currentIndex < testProblems.lastIndex) {
                                currentIndex++
                                selectedAnswer = null
                                showFeedback = false
                            } else {
                                testFinished = true
                            }
                        }
                    ) {
                        Text("Next")
                    }
                } else {
                    Button(
                        onClick = { onBack() }
                    ) {
                        Text("Back to Practice")
                    }
                }

            }
        }
    }
}
