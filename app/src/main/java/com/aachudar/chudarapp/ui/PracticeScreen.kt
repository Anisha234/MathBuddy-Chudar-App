package com.aachudar.chudarapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

@Composable
fun PracticeScreen(
    viewModel: UserViewModel,
    onAlgebraClick: () -> Unit,
    onGeometryClick: () -> Unit,
    onNumberTheoryClick: () -> Unit,
    onStatisticsClick: () -> Unit,
    onBack: () -> Unit
) {
    val user by viewModel.currentUser.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Practice Topics",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3F51B5),
            textAlign = TextAlign.Center
        )

        // Username + Score
        Text(
            text = "Hi ${user?.username ?: "Guest"}!\nYour score is ${user?.score ?: 0} ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF009688),
            textAlign = TextAlign.Center
        )

        // ✅ Passed tests with stars
        if ((user?.numTestsPassed ?: 0) > 0) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Passed tests: ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                repeat(user?.numTestsPassed ?: 0) {
                    Text("⭐", fontSize = 22.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Four Topic Buttons
        Button(
            onClick = onAlgebraClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text("Algebra", fontSize = 18.sp)
        }
/*
        Button(
            onClick = onGeometryClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text("Geometry", fontSize = 18.sp)
        }
*/
        Button(
            onClick = onNumberTheoryClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text("Number Theory", fontSize = 18.sp)
        }

        Button(
            onClick = onStatisticsClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text("Statistics", fontSize = 18.sp)
        }

        // ✅ Removed the yellow "Test Mode!" button

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Back", fontSize = 16.sp, textAlign = TextAlign.Center)
        }
    }
}
