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
import com.aachudar.data.UserDao

@Composable
fun GeometryScreen(
    viewModel: UserViewModel,
    userDao: UserDao,
    onBack: () -> Unit
) {
    val user by viewModel.currentUser.collectAsState()
    val currentProblem = viewModel.currentProblem.value

    var selectedChoice by remember { mutableStateOf<String?>(null) }
    var showResult by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        user?.let {
            viewModel.getNextUnseenProblem("Geometry", it)
            isLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Geometry",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3F51B5),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Current Score: ${user?.score ?: 0}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF009688),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        when {
            isLoading -> {
                CircularProgressIndicator()
            }

            currentProblem != null -> {
                Text(
                    text = currentProblem.questionText,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                listOf(
                    "A" to currentProblem.choiceA,
                    "B" to currentProblem.choiceB,
                    "C" to currentProblem.choiceC,
                    "D" to currentProblem.choiceD
                ).forEach { (label, text) ->
                    Button(
                        onClick = {
                            if (!showResult) {
                                selectedChoice = label
                                showResult = true
                                if (user != null) {
                                    viewModel.submitAnswer(currentProblem, label, user!!, userDao)
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text, fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }

                if (showResult && selectedChoice != null) {
                    Spacer(modifier = Modifier.height(16.dp))

                    val isCorrect = selectedChoice == currentProblem.correctAnswer
                    Text(
                        text = if (isCorrect) "✅ Correct!" else "❌ Incorrect!",
                        fontSize = 18.sp,
                        color = if (isCorrect) Color(0xFF4CAF50) else Color.Red
                    )
                    if (!isCorrect) {
                        Spacer(modifier = Modifier.height(8.dp))

                        val correctText = when (currentProblem.correctAnswer) {
                            "A" -> currentProblem.choiceA
                            "B" -> currentProblem.choiceB
                            "C" -> currentProblem.choiceC
                            "D" -> currentProblem.choiceD
                            else -> "Unknown"
                        }

                        Text(
                            text = "Correct Answer: ${currentProblem.correctAnswer}: $correctText",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            selectedChoice = null
                            showResult = false
                            user?.let { viewModel.getNextUnseenProblem("Geometry", it) }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text("Next Question")
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            else -> {
                Text(
                    text = "🎉 You've completed all questions!",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // ✅ NEW BUTTON: Generate New Questions
                Button(
                    onClick = { viewModel.generateNewQuestionsFromOpenAI("Geometry") },
                    enabled = !viewModel.isLoading.value,   // ✅ Disable when loading
                    modifier = Modifier.padding(16.dp)
                ) {
                    if (viewModel.isLoading.value) {
                        Text("Generating...")
                    } else {
                        Text("Generate New Questions")
                    }
                }

            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Back", fontSize = 16.sp)
        }
    }
}


