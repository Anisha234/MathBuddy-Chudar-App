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
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.aachudar.chudarapp.R
import com.aachudar.chudarapp.ui.UserViewModel


@Composable
fun MainMenuScreen(
    viewModel: UserViewModel,
    onPracticeClick: () -> Unit
) {
    val user by viewModel.currentUser.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top)
    ) {
        // Clean Two-Line App Title
        Text(
            text = "Welcome to",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3F51B5),
            textAlign = TextAlign.Center
        )

        Text(
            text = "MathBuddy!",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3F51B5),
            textAlign = TextAlign.Center
        )

        // Username + Score
        Text(
            text = "Hi ${user?.username ?: "Guest"}!\n  Your score is ${user?.score ?: 0} ",
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

        Image(
            painter = painterResource(id = R.drawable.mathbuddylogo),  // ✅ use your actual image name
            contentDescription = "MathBuddy Image",
            modifier = Modifier
                .size(300.dp)  // You can adjust the size!
        )

        // Clean Practice Button
        Button(
            onClick = onPracticeClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(
                text = "Practice Questions",
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
