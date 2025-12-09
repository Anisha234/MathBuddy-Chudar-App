package com.aachudar.chudarapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aachudar.chudarapp.data.Problem

@Composable
fun NumberTheoryScreen(
    onSubtopicClick: (String) -> Unit,
    onTestClick: () -> Unit,
    onBack: () -> Unit,
    navController: NavController,
    userViewModel: UserViewModel   // ✅ pass the ViewModel here
) {
    val isLoading by userViewModel.isLoading

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Number Theory",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Subtopic buttons
        Button(
            onClick = { onSubtopicClick("Test of divisibility") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text("Test of Divisibility")
        }

        Button(
            onClick = { onSubtopicClick("Order of operations") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text("Order of Operations")
        }

        Button(
            onClick = { onSubtopicClick("Integers - Word Problems") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text("Word Problems")
        }

        Button(
            onClick = { onSubtopicClick("Fractions - Operations, Word Problems, and Conversion") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text("Fractions - Operations, Word Problems, and Conversion")
        }

        Button(
            onClick = { onSubtopicClick("Decimals – Conversion & Basic Operations") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text("Decimals – Conversion & Basic Operations")
        }

        Button(
            onClick = { onSubtopicClick("Rational Numbers – Basic Operations & Comparison") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text("Rational Numbers – Basic Operations & Comparison")
        }

        Button(
            onClick = { onSubtopicClick("LCM and GCD") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text("GCD and LCM")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ✅ New test button (Dark Purple)
        Button(
            onClick = onTestClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4B0082) // Dark Purple (Indigo)
            )
        ) {
            Text("Take Full Number Theory Test")
        }

        Spacer(modifier = Modifier.height(24.dp))

// ✅ New Generate button (Dark Green)
        Button(
            onClick = { navController.navigate("numberTheoryGenerate") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF006400) // Dark Green
            )
        ) {
            Text("Generate New Questions")
        }



        Spacer(modifier = Modifier.height(24.dp))

        // Back button
        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text("Back")
        }
    }
}
