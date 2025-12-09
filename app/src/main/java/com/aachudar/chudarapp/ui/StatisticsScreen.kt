package com.aachudar.chudarapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun StatisticsScreen(
    onSubtopicClick: (String) -> Unit,
    onTestClick: () -> Unit,
    navController: NavController,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Statistics",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Exponents
        Button(
            onClick = { onSubtopicClick("Mean") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) { Text("Mean") }



        // Algebra core topics
        Button(
            onClick = { onSubtopicClick("Median") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) { Text("Median") }

        Button(
            onClick = { onSubtopicClick("Mode") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) { Text("Mode") }


        Button(
            onClick = { onSubtopicClick("Range") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) { Text("Range") }



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
            Text("Take Full Statistics Test")
        }

        Spacer(modifier = Modifier.height(24.dp))

// ✅ New Generate button (Dark Green)
        Button(
            onClick = { navController.navigate("statisticsGenerate") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF006400) // Dark Green
            )
        ) {
            Text("Generate New Questions")
        }


        // Back button
        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text("Back")
        }
    }
}
