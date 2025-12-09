package com.aachudar.chudarapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aachudar.chudarapp.data.Problem
import com.aachudar.chudarapp.ui.UserViewModel

@Composable
fun AlgebraGenerateScreen(
    userViewModel: UserViewModel,
    onBack: () -> Unit
) {
    val isLoading by userViewModel.isLoading
    val generatedProblems by userViewModel.lastGeneratedProblems.collectAsState()

    // Track current question index and selected choice
    var currentIndex by remember { mutableStateOf(0) }
    var selectedChoice by remember { mutableStateOf<String?>(null) }
    var showFeedback by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            "Generate Algebra Problems",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Button to generate new problems
        Button(
            onClick = {
                currentIndex = 0
                selectedChoice = null
                showFeedback = false
                userViewModel.generateNewQuestionsFromOpenAI("Algebra")
            },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text("Generate Problems")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ✅ Hide old problems while loading
        if (!isLoading && generatedProblems.isNotEmpty() && currentIndex < generatedProblems.size) {
            val problem = generatedProblems[currentIndex]

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text("Q${currentIndex + 1}: ${problem.questionText}")
                    Spacer(modifier = Modifier.height(8.dp))

                    // Answer choices with radio buttons
                    val choices = listOf(
                        "A" to problem.choiceA,
                        "B" to problem.choiceB,
                        "C" to problem.choiceC,
                        "D" to problem.choiceD
                    )

                    choices.forEach { (label, text) ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            RadioButton(
                                selected = selectedChoice == label,
                                onClick = {
                                    selectedChoice = label
                                    showFeedback = true
                                    isCorrect = (label == problem.correctAnswer)
                                }
                            )
                            Text("$label) $text")
                        }
                    }

                    // ✅ Feedback after selecting
                    if (showFeedback && selectedChoice != null) {
                        Text(
                            if (isCorrect) "✅ Correct!"
                            else "❌ Wrong! Correct answer is ${problem.correctAnswer}",
                            color = if (isCorrect) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Next or Finish button
                    if (showFeedback) {
                        Button(
                            onClick = {
                                if (currentIndex < generatedProblems.lastIndex) {
                                    // Move to next question
                                    currentIndex++
                                    selectedChoice = null
                                    showFeedback = false
                                } else {
                                    // Finished all → stay at end
                                    currentIndex = generatedProblems.size
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                if (currentIndex < generatedProblems.lastIndex)
                                    "Next"
                                else
                                    "Finish"
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}
