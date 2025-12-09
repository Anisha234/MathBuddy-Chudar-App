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
fun AlgebraScreen(
    navController: NavController,
    onSubtopicClick: (String) -> Unit,
    onTestClick: () -> Unit,
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
            text = "Algebra",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Exponents
        Button(
            onClick = { onSubtopicClick("Exponents") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) { Text("Exponents - Squares, Cubes, Roots, Properties") }



        // Algebra core topics
        Button(
            onClick = { onSubtopicClick("Polynomials") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) { Text("Terms and polynomials") }

        Button(
            onClick = { onSubtopicClick("Equations") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) { Text("Solving Systems of Equations") }


        Button(
            onClick = { onSubtopicClick("Algebraic Identities") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) { Text("Algebraic Identities") }

        Button(
            onClick = { onSubtopicClick("Word Problems") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) { Text("Word Problems") }


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
            Text("Take Full Algebra Test")
        }

        Spacer(modifier = Modifier.height(24.dp))

// ✅ New Generate button (Dark Green)
        Button(
            onClick = { navController.navigate("algebraGenerate") },
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
