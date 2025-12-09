package com.aachudar.chudarapp

import UserRepository
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.aachudar.chudarapp.data.UserDatabase
import com.aachudar.chudarapp.ui.theme.ChudarAppTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aachudar.chudarapp.data.Problem
import com.aachudar.chudarapp.ui.AlgebraScreen
import com.aachudar.chudarapp.ui.GeometryScreen
import com.aachudar.chudarapp.ui.LoginScreen
import com.aachudar.chudarapp.ui.MainMenuScreen
import com.aachudar.chudarapp.ui.NumberTheoryScreen
import com.aachudar.chudarapp.ui.NumberTheoryQuizScreen
import com.aachudar.chudarapp.ui.PracticeScreen
import com.aachudar.chudarapp.ui.StatisticsScreen
import com.aachudar.chudarapp.ui.UserViewModel
import com.aachudar.chudarapp.ui.UserViewModelFactory
import com.aachudar.chudarapp.data.getPresetTestNumTheory
import com.aachudar.chudarapp.ui.AlgebraGenerateScreen
import com.aachudar.chudarapp.ui.AlgebraQuizScreen
import com.aachudar.chudarapp.ui.AlgebraTestScreen
import com.aachudar.chudarapp.ui.NumberTheoryGenerateScreen
import com.aachudar.chudarapp.ui.NumberTheoryTestScreen
import com.aachudar.chudarapp.ui.StatisticsGenerateScreen
import com.aachudar.chudarapp.ui.StatisticsQuizScreen
import com.aachudar.chudarapp.ui.StatisticsTestScreen
import com.aachudar.data.UserDao

class MainActivity : ComponentActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = UserDatabase.getDatabase(applicationContext)
        userDao = database.userDao()
        val problemDao = database.problemDao()
        val repository = UserRepository(userDao, problemDao)
        userViewModel = ViewModelProvider(
            this,
            UserViewModelFactory(repository, problemDao)
        )[UserViewModel::class.java]
        // ---------- Test of divisibility ----------
        val problems = listOf(
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Test of divisibility", questionText = "Which of the following numbers is divisible by 9?", choiceA = "234", choiceB = "235", choiceC = "236", choiceD = "237", correctAnswer = "A"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Test of divisibility", questionText = "Which of the following is divisible by 11?", choiceA = "1234", choiceB = "1331", choiceC = "1222", choiceD = "1333", correctAnswer = "B"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Test of divisibility", questionText = "Which of these numbers is divisible by both 2 and 5?", choiceA = "215", choiceB = "225", choiceC = "250", choiceD = "245", correctAnswer = "C"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Test of divisibility", questionText = "Which of the following is divisible by 6?", choiceA = "132", choiceB = "133", choiceC = "135", choiceD = "137", correctAnswer = "A"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Test of divisibility", questionText = "Which number is divisible by 4?", choiceA = "122", choiceB = "124", choiceC = "125", choiceD = "127", correctAnswer = "B"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Test of divisibility", questionText = "Which of the following is divisible by 3?", choiceA = "251", choiceB = "252", choiceC = "254", choiceD = "256", correctAnswer = "B"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Test of divisibility", questionText = "Which of these is divisible by 8?", choiceA = "128", choiceB = "132", choiceC = "136", choiceD = "140", correctAnswer = "A"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Test of divisibility", questionText = "Which of the following is divisible by 25?", choiceA = "145", choiceB = "150", choiceC = "160", choiceD = "170", correctAnswer = "B"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Test of divisibility", questionText = "Which number is divisible by both 2 and 9?", choiceA = "126", choiceB = "132", choiceC = "144", choiceD = "152", correctAnswer = "C"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Test of divisibility", questionText = "Which number is divisible by 7?", choiceA = "134", choiceB = "140", choiceC = "141", choiceD = "142", correctAnswer = "B"),


        // ---------- Order of operations (revised, NMMS-style) ----------
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Order of operations", questionText = "Find the value of 6 + 4 × 2² – 8 ÷ 2?", choiceA = "16", choiceB = "18", choiceC = "14", choiceD = "12", correctAnswer = "B"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Order of operations", questionText = "The value is (5 + 3) × 2 – 3² ÷ 3?", choiceA = "11", choiceB = "15", choiceC = "13", choiceD = "17", correctAnswer = "C"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Order of operations", questionText = "Find the value of 3 × [10 – (4 + 1) × 2] + 9 ÷ 3?", choiceA = "6", choiceB = "23", choiceC = "3", choiceD = "2", correctAnswer = "C"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Order of operations", questionText = "5² – (8 ÷ 4 + 3 × 2) + 6 = ?", choiceA = "23", choiceB = "22", choiceC = "21", choiceD = "19", correctAnswer = "A"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Order of operations", questionText = "Evaluate: (12 – 3²) × 2 + 4", choiceA = "4", choiceB = "10", choiceC = "14", choiceD = "16", correctAnswer = "B"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Order of operations", questionText = "Simplify: (6 + 2)² ÷ 4 – 3", choiceA = "9", choiceB = "13", choiceC = "15", choiceD = "17", correctAnswer = "B"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Order of operations", questionText = "Find: 7 × (2³ – 6 ÷ 3)", choiceA = "28", choiceB = "30", choiceC = "35", choiceD = "42", correctAnswer = "D"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Order of operations", questionText = "The value of 40 ÷ (5 × 2) + 3²?", choiceA = "9", choiceB = "11", choiceC = "13", choiceD = "15", correctAnswer = "C"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Order of operations", questionText = "Evaluate: (50 – 6²) ÷ 2", choiceA = "4", choiceB = "7", choiceC = "8", choiceD = "12", correctAnswer = "B"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Order of operations", questionText = "Find the value of (18 ÷ 3)² – 2 × 5", choiceA = "4", choiceB = "11", choiceC = "16", choiceD = "26", correctAnswer = "D"),

            // -------- Integers - Word Problems (NMMS-style) --------
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Integers - Word Problems",
            questionText = "If 5 more than twice a number is 21, what is the number?",
            choiceA = "7", choiceB = "8", choiceC = "9", choiceD = "10", correctAnswer = "B"
        ),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Integers - Word Problems",
            questionText = "A number when divided by 7 gives remainder 4. If the number is between 25 and 35, what is the number?",
            choiceA = "31", choiceB = "32", choiceC = "33", choiceD = "34", correctAnswer = "C"
        ),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Integers - Word Problems",
            questionText = "The sum of two consecutive odd numbers is 36. What are the numbers?",
            choiceA = "17 and 19", choiceB = "19 and 21", choiceC = "15 and 17", choiceD = "13 and 15", correctAnswer = "A"
        ),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Integers - Word Problems",
            questionText = "If three times a number decreased by 4 is 20, what is the number?",
            choiceA = "6", choiceB = "7", choiceC = "8", choiceD = "9", correctAnswer = "C"
        ),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Integers - Word Problems",
            questionText = "The product of two consecutive integers is 182. What are the integers?",
            choiceA = "12 and 13", choiceB = "13 and 14", choiceC = "14 and 15", choiceD = "15 and 16", correctAnswer = "B"
        ),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Integers - Word Problems",
            questionText = "The sum of two consecutive multiples of 11 is 143. What are the numbers?",
            choiceA = "66 and 77", choiceB = "55 and 66", choiceC = "44 and 55", choiceD = "77 and 88", correctAnswer = "A"
        ),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Integers - Word Problems",
            questionText = "If 9 times a number is added to 5, the result is 77. What is the number?",
            choiceA = "7", choiceB = "8", choiceC = "9", choiceD = "10", correctAnswer = "B"
        ),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Integers - Word Problems",
            questionText = "Find the integer which when subtracted from 50 gives 32.",
            choiceA = "16", choiceB = "18", choiceC = "20", choiceD = "22", correctAnswer = "B"
        ),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Integers - Word Problems",
            questionText = "The difference between two consecutive even numbers is 2. If their sum is 74, what are the numbers?",
            choiceA = "36 and 38", choiceB = "37 and 39", choiceC = "38 and 40", choiceD = "35 and 37", correctAnswer = "A"
        ),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Integers - Word Problems",
            questionText = "If the sum of three consecutive integers is 84, what is the middle number?",
            choiceA = "27", choiceB = "28", choiceC = "29", choiceD = "30", correctAnswer = "B"
            ),

            // -------- Fractions - Operations, Word Problems, and Conversion (NMMS-style) --------
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "Convert 7/3 into a mixed fraction.",
                choiceA = "2 1/3", choiceB = "3 1/3", choiceC = "2 2/3", choiceD = "1 2/3", correctAnswer = "A"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "Convert 4 2/5 into an improper fraction.",
                choiceA = "22/5", choiceB = "21/5", choiceC = "24/5", choiceD = "23/5", correctAnswer = "A"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "Simplify: 3/4 + 5/8",
                choiceA = "13/8", choiceB = "11/8", choiceC = "7/8", choiceD = "1 1/2", correctAnswer = "B"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "Simplify: 7/12 – 1/6",
                choiceA = "3/12", choiceB = "1/2", choiceC = "5/12", choiceD = "2/3", correctAnswer = "C"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "Simplify: 2/3 × 9/10",
                choiceA = "3/5", choiceB = "2/5", choiceC = "4/15", choiceD = "6/5", correctAnswer = "A"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "Simplify: (5/6) ÷ (10/12)",
                choiceA = "12/10", choiceB = "6/5", choiceC = "1", choiceD = "3/4", correctAnswer = "C"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "Add: 1/2 + 2/3 + 3/4",
                choiceA = "23/12", choiceB = "25/12", choiceC = "19/12", choiceD = "21/12", correctAnswer = "A"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "Simplify: 4 1/2 – 2 2/3",
                choiceA = "1 7/6", choiceB = "1 5/6", choiceC = "1 4/6", choiceD = "2 1/6", correctAnswer = "B"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "Multiply: 3/5 × 2/7",
                choiceA = "6/25", choiceB = "5/21", choiceC = "7/15", choiceD = "6/35", correctAnswer = "D"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "Divide: 7/8 ÷ 14/15",
                choiceA = "15/16", choiceB = "14/17", choiceC = "15/28", choiceD = "8/15", correctAnswer = "A"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "Ravi ate 2/5 of a cake and his sister ate 1/4 of the same cake. How much of the cake did they eat together?",
                choiceA = "13/20", choiceB = "11/20", choiceC = "9/20", choiceD = "3/5", correctAnswer = "A"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "A tank is 3/4 full. After using 2/5 of its water, what fraction of the tank is still filled?",
                choiceA = "7/20", choiceB = "9/20", choiceC = "11/20", choiceD = "3/5", correctAnswer = "B"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "A worker completes 3/8 of a task on Monday and 1/4 on Tuesday. What fraction is left?",
                choiceA = "5/8", choiceB = "3/8", choiceC = "7/8", choiceD = "3/4", correctAnswer = "B"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "If a rope is cut into 5 equal parts, what fraction of the rope is each part?",
                choiceA = "1/4", choiceB = "1/5", choiceC = "1/6", choiceD = "1/8", correctAnswer = "B"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "A car uses 3/10 of its fuel on the first day and 2/5 on the second day. What fraction of fuel is used in total?",
                choiceA = "7/10", choiceB = "4/5", choiceC = "9/10", choiceD = "3/4", correctAnswer = "A"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "If 12 pencils are distributed equally among 4 children, what fraction of the pencils does each child get?",
                choiceA = "1/2", choiceB = "1/3", choiceC = "1/4", choiceD = "1/5", correctAnswer = "B"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "A basket has 2/3 kg of apples. If 1/9 kg is eaten, how much is left?",
                choiceA = "5/9 kg", choiceB = "7/9 kg", choiceC = "6/9 kg", choiceD = "4/9 kg", correctAnswer = "A"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "Simplify: 5/12 + 7/18",
                choiceA = "41/36", choiceB = "29/36", choiceC = "37/36", choiceD = "23/36", correctAnswer = "B"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "A man spends 2/7 of his salary on rent and 3/7 on food. What fraction of his salary is left?",
                choiceA = "1/7", choiceB = "2/7", choiceC = "3/7", choiceD = "5/7", correctAnswer = "B"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions - Operations, Word Problems, and Conversion",
                questionText = "Simplify: (2/3 of 9/10) + (3/5 of 5/6)",
                choiceA = "2", choiceB = "29/30", choiceC = "33/30", choiceD = "1 1/30", correctAnswer = "C"
            ),
            // -------- Decimals – Conversion & Basic Operations --------
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Decimals – Conversion & Basic Operations",
                questionText = "Convert 0.75 into a fraction in simplest form.",
                choiceA = "3/5", choiceB = "3/4", choiceC = "2/3", choiceD = "5/8", correctAnswer = "B"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Decimals – Conversion & Basic Operations",
                questionText = "Convert 7/20 into a decimal.",
                choiceA = "0.25", choiceB = "0.3", choiceC = "0.35", choiceD = "0.4", correctAnswer = "C"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Decimals – Conversion & Basic Operations",
                questionText = "Convert 2.4 into a fraction in simplest form.",
                choiceA = "24/10", choiceB = "6/5", choiceC = "12/5", choiceD = "48/20", correctAnswer = "C"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Decimals – Conversion & Basic Operations",
                questionText = "Add: 3.45 + 7.8",
                choiceA = "11.25", choiceB = "10.95", choiceC = "11.15", choiceD = "11.35", correctAnswer = "A"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Decimals – Conversion & Basic Operations",
                questionText = "Subtract: 12.6 – 7.85",
                choiceA = "4.75", choiceB = "5.25", choiceC = "4.85", choiceD = "5.15", correctAnswer = "A"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Decimals – Conversion & Basic Operations",
                questionText = "Multiply: 0.25 × 0.6",
                choiceA = "0.125", choiceB = "0.15", choiceC = "0.12", choiceD = "0.16", correctAnswer = "B"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Decimals – Conversion & Basic Operations",
                questionText = "Divide: 4.8 ÷ 0.6",
                choiceA = "8", choiceB = "7", choiceC = "6", choiceD = "5", correctAnswer = "A"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Decimals – Conversion & Basic Operations",
                questionText = "Express 3/8 as a decimal.",
                choiceA = "0.325", choiceB = "0.35", choiceC = "0.375", choiceD = "0.4", correctAnswer = "C"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Decimals – Conversion & Basic Operations",
                questionText = "Round 56.785 to 2 decimal places.",
                choiceA = "56.7", choiceB = "56.78", choiceC = "56.79", choiceD = "56.8", correctAnswer = "C"
            ),
            Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Decimals – Conversion & Basic Operations",
                questionText = "Simplify: (0.4 × 0.5) + 0.25",
                choiceA = "0.45", choiceB = "0.35", choiceC = "0.4", choiceD = "0.3", correctAnswer = "A"
            ),

        // -------- Rational Numbers – Basic Operations & Comparison --------
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Rational Numbers – Basic Operations & Comparison",
            questionText = "Simplify: 2/3 + 5/6",
            choiceA = "7/9", choiceB = "3/2", choiceC = "7/6", choiceD = "4/3", correctAnswer = "B"
        ),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Rational Numbers – Basic Operations & Comparison",
            questionText = "Simplify: 7/4 – 3/8",
            choiceA = "11/8", choiceB = "13/8", choiceC = "5/8", choiceD = "17/8", correctAnswer = "A"
        ),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Rational Numbers – Basic Operations & Comparison",
            questionText = "Multiply: (-2/3) × (9/4)",
            choiceA = "-3/2", choiceB = "-5/6", choiceC = "-6/7", choiceD = "-8/9", correctAnswer = "A"
        ),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Rational Numbers – Basic Operations & Comparison",
            questionText = "Divide: (5/6) ÷ (10/9)",
            choiceA = "3/4", choiceB = "9/12", choiceC = "5/9", choiceD = "3/5", correctAnswer = "A"
        ),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Rational Numbers – Basic Operations & Comparison",
            questionText = "Simplify: (-3/5) + (7/10)",
            choiceA = "1/2", choiceB = "4/15", choiceC = "2/5", choiceD = "1/10", correctAnswer = "D"
        ),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Rational Numbers – Basic Operations & Comparison",
            questionText = "Simplify: (11/12) – (7/18)",
            choiceA = "13/36", choiceB = "5/18", choiceC = "7/36", choiceD = "19/36", correctAnswer = "D"
        ),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Rational Numbers – Basic Operations & Comparison",
            questionText = "Simplify: (3/7) × (-14/9)",
            choiceA = "-2/3", choiceB = "-4/9", choiceC = "-2/7", choiceD = "-3/2", correctAnswer = "A"
        ),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Rational Numbers – Basic Operations & Comparison",
            questionText = "Which is greater: -2/7 or -3/8?",
            choiceA = "-2/7", choiceB = "-3/8", choiceC = "Both equal", choiceD = "Cannot compare", correctAnswer = "A"
        ),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Rational Numbers – Basic Operations & Comparison",
            questionText = "Arrange in ascending order: 1/2, 3/4, 2/3",
            choiceA = "3/4, 1/2, 2/3", choiceB = "1/2, 2/3, 3/4", choiceC = "2/3, 1/2, 3/4", choiceD = "3/4, 2/3, 1/2", correctAnswer = "B"
        ),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Rational Numbers – Basic Operations & Comparison",
            questionText = "Arrange in descending order: -5/6, -2/3, -3/4",
            choiceA = "-2/3, -3/4, -5/6", choiceB = "-5/6, -2/3, -3/4", choiceC = "-3/4, -5/6, -2/3", choiceD = "-2/3, -5/6, -3/4", correctAnswer = "A"
        ),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Rational Numbers – Basic Operations & Comparison",
            questionText = "Which is smaller: 7/9 or 8/11?",
            choiceA = "7/9", choiceB = "8/11", choiceC = "Equal", choiceD = "Cannot determine", correctAnswer = "B"
        ),
            Problem(
                userId = "global", source = "preset", topic = "Number Theory", subtopic = "LCM and GCD",
                questionText = "Find the LCM of 18 and 24.",
                choiceA = "36", choiceB = "72", choiceC = "48", choiceD = "60",
                correctAnswer = "B"
            ),

            Problem(
                userId = "global", source = "preset", topic = "Number Theory", subtopic = "LCM and GCD",
                questionText = "Find the HCF of 84 and 126.",
                choiceA = "21", choiceB = "14", choiceC = "42", choiceD = "28",
                correctAnswer = "C"
            ),

            Problem(
                userId = "global", source = "preset", topic = "Number Theory", subtopic = "LCM and GCD",
                questionText = "The LCM of 15 and 20 is:",
                choiceA = "30", choiceB = "45", choiceC = "60", choiceD = "75",
                correctAnswer = "C"
            ),

            Problem(
                userId = "global", source = "preset", topic = "Number Theory", subtopic = "LCM and GCD",
                questionText = "The HCF of 96 and 144 is:",
                choiceA = "24", choiceB = "48", choiceC = "36", choiceD = "72",
                correctAnswer = "B"
            ),

            Problem(
                userId = "global", source = "preset", topic = "Number Theory", subtopic = "LCM and GCD",
                questionText = "If HCF of two numbers is 13 and their product is 2197, find their LCM.",
                choiceA = "169", choiceB = "143", choiceC = "121", choiceD = "91",
                correctAnswer = "A"
            ),

            Problem(
                userId = "global", source = "preset", topic = "Number Theory", subtopic = "LCM and GCD",
                questionText = "The LCM of 21, 28 and 36 is:",
                choiceA = "252", choiceB = "504", choiceC = "336", choiceD = "630",
                correctAnswer = "A"
            ),

            Problem(
                userId = "global", source = "preset", topic = "Number Theory", subtopic = "LCM and GCD",
                questionText = "The HCF of 132 and 198 is:",
                choiceA = "22", choiceB = "33", choiceC = "66", choiceD = "11",
                correctAnswer = "C"
            ),

            Problem(
                userId = "global", source = "preset", topic = "Number Theory", subtopic = "LCM and GCD",
                questionText = "If LCM of two numbers is 90 and HCF is 15, one number is 30. Find the other.",
                choiceA = "100", choiceB = "60", choiceC = "45", choiceD = "75",
                correctAnswer = "C"
            ),

                    Problem(
                    userId = "global", source = "preset", topic = "Number Theory", subtopic = "LCM and GCD",
            questionText = "The LCM of 18, 30, and 45 is:",
            choiceA = "180", choiceB = "270", choiceC = "90", choiceD = "360",
            correctAnswer = "C"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Number Theory", subtopic = "LCM and GCD",
            questionText = "The HCF of 108 and 192 is:",
            choiceA = "24", choiceB = "36", choiceC = "48", choiceD = "12",
            correctAnswer = "D"
        ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "What is the smallest number by which 360 must be multiplied to make it a perfect square?",
                choiceA = "2", choiceB = "3", choiceC = "5", choiceD = "10", correctAnswer = "D"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "What is the smallest number by which 53240 should be multiplied to get a perfect cube?",
                choiceA = "25", choiceB = "16", choiceC = "9", choiceD = "4", correctAnswer = "A"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "Which of the following numbers is both a perfect square and a perfect cube?",
                choiceA = "64", choiceB = "36", choiceC = "100", choiceD = "81", correctAnswer = "A"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "The least number that 1500 needs to be multiplied by to make it a perfect square is:",
                choiceA = "15", choiceB = "21", choiceC = "25", choiceD = "36", correctAnswer = "A"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "The cube root of 13824 is:",
                choiceA = "24", choiceB = "22", choiceC = "21", choiceD = "18", correctAnswer = "A"
            ),

            // Laws of Exponents
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "The value of (5³ ÷ 5²) × 5⁴ is:",
                choiceA = "5⁵", choiceB = "5⁶", choiceC = "5⁷", choiceD = "5⁹", correctAnswer = "A"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "The value of 2⁻³ × 2⁵ is:",
                choiceA = "2²", choiceB = "2³", choiceC = "2⁸", choiceD = "2⁻²", correctAnswer = "A"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "If a = -1 and b = 2, then the value of a^b ÷ b^a is:",
                choiceA = "1", choiceB = "2", choiceC = "4", choiceD = "-2", correctAnswer = "B"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "If a = 3, then the value of (a²)³ is:",
                choiceA = "9", choiceB = "27", choiceC = "81", choiceD = "729", correctAnswer = "D"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "Simplify: (7²)³ ÷ 7⁴",
                choiceA = "7²", choiceB = "7³", choiceC = "7⁴", choiceD = "7⁵", correctAnswer = "A"
            ),

            // Unit digit of exponents
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "The unit digit of 7²⁰ is:",
                choiceA = "1", choiceB = "7", choiceC = "9", choiceD = "3", correctAnswer = "A"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "The unit digit of 2³⁰ is:",
                choiceA = "2", choiceB = "4", choiceC = "8", choiceD = "6", correctAnswer = "B"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "The unit digit of 9²⁵ is:",
                choiceA = "1", choiceB = "9", choiceC = "5", choiceD = "7", correctAnswer = "B"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "The unit digit of 3⁵⁰ is:",
                choiceA = "1", choiceB = "3", choiceC = "7", choiceD = "9", correctAnswer = "D"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "The unit digit of 8¹²³ is:",
                choiceA = "2", choiceB = "4", choiceC = "6", choiceD = "8", correctAnswer = "C"
            ),

            // Mixed reasoning
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "If x² = 441, then the value of x is:",
                choiceA = "19", choiceB = "20", choiceC = "21", choiceD = "22", correctAnswer = "C"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "If m³ = 1331, then m is:",
                choiceA = "10", choiceB = "11", choiceC = "12", choiceD = "13", correctAnswer = "B"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "The smallest 4-digit perfect square number is:",
                choiceA = "1024", choiceB = "1000", choiceC = "1156", choiceD = "1225", correctAnswer = "A"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "The smallest 5-digit perfect cube is:",
                choiceA = "10000", choiceB = "10648", choiceC = "12167", choiceD = "12500", correctAnswer = "B"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
                questionText = "Which of the following is equal to (√49) × (∛27)?",
                choiceA = "18", choiceB = "21", choiceC = "24", choiceD = "27", correctAnswer = "B"
            ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Polynomials",
            questionText = "Which of the following is a monomial?",
            choiceA = "5x", choiceB = "x + 3", choiceC = "2x² + 5y", choiceD = "x³ + 2", correctAnswer = "A"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Polynomials",
            questionText = "Which of the following is a binomial?",
            choiceA = "7x²", choiceB = "x³ + 4x", choiceC = "2x + 3", choiceD = "x² + y² + 1", correctAnswer = "C"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Polynomials",
            questionText = "Which of the following is a trinomial?",
            choiceA = "x² + 2", choiceB = "x + y", choiceC = "3x² + 2x + 5", choiceD = "2x³", correctAnswer = "C"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Polynomials",
            questionText = "Which of these is NOT a polynomial?",
            choiceA = "2x² + 3x + 1", choiceB = "x³ – 7", choiceC = "x – 5", choiceD = "1/x + 2", correctAnswer = "D"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Polynomials",
            questionText = "The expression 2x²y – 3xy² + 5 is a:",
            choiceA = "Binomial", choiceB = "Monomial", choiceC = "Trinomial", choiceD = "Polynomial", correctAnswer = "C"
        ),

        // Degree of Polynomials
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Polynomials",
            questionText = "What is the degree of 7x³ + 2x² – 5?",
            choiceA = "1", choiceB = "2", choiceC = "3", choiceD = "5", correctAnswer = "C"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Polynomials",
            questionText = "What is the degree of 9x⁵ – 4x³ + 6?",
            choiceA = "3", choiceB = "5", choiceC = "6", choiceD = "9", correctAnswer = "B"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Polynomials",
            questionText = "The degree of 3xy² + 7x²y + 2 is:",
            choiceA = "2", choiceB = "3", choiceC = "4", choiceD = "5", correctAnswer = "B"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Polynomials",
            questionText = "The degree of the constant polynomial 8 is:",
            choiceA = "0", choiceB = "1", choiceC = "Undefined", choiceD = "8", correctAnswer = "A"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Polynomials",
            questionText = "The degree of polynomial (x²y³ + xy²) is:",
            choiceA = "2", choiceB = "3", choiceC = "4", choiceD = "5", correctAnswer = "D"
        ),

        // More Terms & Types
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Polynomials",
            questionText = "Which of the following is a polynomial in one variable?",
            choiceA = "2x² + 3", choiceB = "xy + 1", choiceC = "3x²y", choiceD = "x² + y²", correctAnswer = "A"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Polynomials",
            questionText = "Which of the following is a polynomial in two variables?",
            choiceA = "x² + 3", choiceB = "y³ + 2", choiceC = "2x² + 3y", choiceD = "5", correctAnswer = "C"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Polynomials",
            questionText = "Which of the following is a quadratic polynomial?",
            choiceA = "x³ + 1", choiceB = "x² + 3x + 2", choiceC = "2x + 1", choiceD = "4", correctAnswer = "B"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Polynomials",
            questionText = "Which of the following is a cubic polynomial?",
            choiceA = "x³ + 3x + 2", choiceB = "x² + 5", choiceC = "2x + 3", choiceD = "7", correctAnswer = "A"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Polynomials",
            questionText = "Which of the following polynomials is linear?",
            choiceA = "5x² + 2", choiceB = "7x + 3", choiceC = "x³ – 2", choiceD = "x² + y²", correctAnswer = "B"
        ),

        // More Degree Questions
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Polynomials",
            questionText = "What is the degree of polynomial 6x⁴y² + 3xy³?",
            choiceA = "4", choiceB = "5", choiceC = "6", choiceD = "7", correctAnswer = "D"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Polynomials",
            questionText = "The degree of 2x⁶ – 7x² + 4 is:",
            choiceA = "2", choiceB = "4", choiceC = "6", choiceD = "7", correctAnswer = "C"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Polynomials",
            questionText = "The degree of 10x³y²z is:",
            choiceA = "3", choiceB = "4", choiceC = "5", choiceD = "6", correctAnswer = "C"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Polynomials",
            questionText = "The degree of 2a²b²c is:",
            choiceA = "3", choiceB = "4", choiceC = "5", choiceD = "6", correctAnswer = "B"
        ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Equations",
                questionText = "Solve for n: 7n + 6 = 27",
                choiceA = "4", choiceB = "3", choiceC = "5", choiceD = "7", correctAnswer = "B"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Equations",
                questionText = "If 5x - 9 = 16, then x = ?",
                choiceA = "3", choiceB = "4", choiceC = "5", choiceD = "6", correctAnswer = "C"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Equations",
                questionText = "Solve for y: 3y + 12 = 0",
                choiceA = "-3", choiceB = "-4", choiceC = "4", choiceD = "3", correctAnswer = "B"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Equations",
                questionText = "If 2x + 3 = 11, what is x?",
                choiceA = "2", choiceB = "3", choiceC = "4", choiceD = "5", correctAnswer = "C"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Equations",
                questionText = "Solve: 10x - 4 = 6x + 12",
                choiceA = "2", choiceB = "3", choiceC = "4", choiceD = "5", correctAnswer = "C"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Equations",
                questionText = "If 8 - 2y = 0, then y = ?",
                choiceA = "2", choiceB = "3", choiceC = "4", choiceD = "5", correctAnswer = "C"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Equations",
                questionText = "Solve for z: 3z/4 = 9",
                choiceA = "10", choiceB = "12", choiceC = "14", choiceD = "15", correctAnswer = "B"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Equations",
                questionText = "If 4x + 5 = 2x + 13, find x",
                choiceA = "3", choiceB = "4", choiceC = "5", choiceD = "6", correctAnswer = "B"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Equations",
                questionText = "Solve for a: 2a - 7 = 9",
                choiceA = "7", choiceB = "8", choiceC = "6", choiceD = "9", correctAnswer = "B"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Equations",
                questionText = "Solve: (x/2) + 5 = 9",
                choiceA = "6", choiceB = "7", choiceC = "8", choiceD = "9", correctAnswer = "C"
            ),

            // Systems of equations
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Equations",
                questionText = "Solve the system: x + y = 10, x - y = 2. What is x?",
                choiceA = "4", choiceB = "5", choiceC = "6", choiceD = "8", correctAnswer = "C"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Equations",
                questionText = "Solve the system: x + y = 10, x - y = 2. What is y?",
                choiceA = "4", choiceB = "5", choiceC = "6", choiceD = "8", correctAnswer = "A"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Equations",
                questionText = "If 2x + 3y = 12 and x - y = 1, what is y?",
                choiceA = "1", choiceB = "2", choiceC = "3", choiceD = "4", correctAnswer = "B"
            ),

            // Substitution type
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Equations",
                questionText = "If y = 3x + 2 and x = 4, then y = ?",
                choiceA = "12", choiceB = "14", choiceC = "10", choiceD = "15", correctAnswer = "B"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Equations",
                questionText = "If z = 2y - 5 and y = 6, what is z?",
                choiceA = "6", choiceB = "7", choiceC = "8", choiceD = "9", correctAnswer = "B"
            ),

            // Trickier equations
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Equations",
                questionText = "Solve: 5(x - 2) = 3(x + 4)",
                choiceA = "7", choiceB = "6", choiceC = "5", choiceD = "11", correctAnswer = "D"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Equations",
                questionText = "If 3x + 2 = 2x + 7, find x",
                choiceA = "3", choiceB = "4", choiceC = "5", choiceD = "6", correctAnswer = "C"
            ),
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Equations",
                questionText = "Solve for p: (p + 4)/3 = 6",
                choiceA = "12", choiceB = "14", choiceC = "16", choiceD = "18", correctAnswer = "B"
            ),
            // --- Recall (kept minimal) ---
            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
                questionText = "Expand (a + b)².",
                choiceA = "a² + b²", choiceB = "a² + 2ab + b²", choiceC = "a² - 2ab + b²", choiceD = "a² + ab + b²",
                correctAnswer = "B"
            ),

                    Problem(
                    userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
            questionText = "Simplify a² - b².",
            choiceA = "(a + b)(a - b)", choiceB = "(a + b)²", choiceC = "(a - b)²", choiceD = "a² + b²",
            correctAnswer = "A"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
            questionText = "Expand (a + b + c)².",
            choiceA = "a² + b² + c² + 2ab + 2bc + 2ca", choiceB = "a² + b² + c² - 2ab - 2bc - 2ca", choiceC = "a² + b² + c²", choiceD = "a²b²c²",
            correctAnswer = "A"
        ),

// --- Medium application ---
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
            questionText = "If x + 1/x = 7, find x² + 1/x².",
            choiceA = "45", choiceB = "47", choiceC = "49", choiceD = "43",
            correctAnswer = "B"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
            questionText = "If x + 1/x = 4, find x³ + 1/x³.",
            choiceA = "52", choiceB = "54", choiceC = "50", choiceD = "56",
            correctAnswer = "A"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
            questionText = "If a + b = 10 and ab = 21, find a² + b².",
            choiceA = "79", choiceB = "58", choiceC = "99", choiceD = "109",
            correctAnswer = "58"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
            questionText = "If a + b = 12 and ab = 20, find a³ + b³.",
            choiceA = "1680", choiceB = "1008", choiceC = "1660", choiceD = "1740",
            correctAnswer = "B"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
            questionText = "If a² + b² = 58 and a + b = 10, find ab.",
            choiceA = "21", choiceB = "24", choiceC = "26", choiceD = "28",
            correctAnswer = "A"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
            questionText = "If a + b = 0, what is a³ + b³?",
            choiceA = "0", choiceB = "ab", choiceC = "3ab", choiceD = "2ab",
            correctAnswer = "A"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
            questionText = "If x + y = 12 and xy = 32, find x² + y².",
            choiceA = "80", choiceB = "128", choiceC = "144", choiceD = "100",
            correctAnswer = "D"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
            questionText = "If x + y = 15 and xy = 50, find x³ + y³.",
            choiceA = "1025", choiceB = "2725", choiceC = "2850", choiceD = "2925",
            correctAnswer = "A"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
            questionText = "If p + q = 8 and pq = 12, find p² + q².",
            choiceA = "40", choiceB = "52", choiceC = "64", choiceD = "72",
            correctAnswer = "A"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
            questionText = "If x + 1/x = 3, find x⁴ + 1/x⁴.",
            choiceA = "79", choiceB = "47", choiceC = "97", choiceD = "83",
            correctAnswer = "B"
        ),


        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
            questionText = "If x + 1/x = 2, find x⁵ + 1/x⁵.",
            choiceA = "10", choiceB = "2", choiceC = "14", choiceD = "16",
            correctAnswer = "B"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
            questionText = "If a + b = 7, ab = 10, find a⁴ + b⁴.",
            choiceA = "401", choiceB = "351", choiceC = "641", choiceD = "441",
            correctAnswer = "C"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
            questionText = "If x + y + z = 0, evaluate (x² + y² + z²).",
            choiceA = "2(xy + yz + zx)", choiceB = "-2(xy + yz + zx)", choiceC = "xyz", choiceD = "0",
            correctAnswer = "B"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
            questionText = "If a + b = 5, ab = 6, find (a² + b²)(a² - b²).",
            choiceA = "55", choiceB = "65", choiceC = "75", choiceD = "85",
            correctAnswer = "B"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
            questionText = "If a² + b² = 25 and ab = 12, find a⁴ + b⁴.",
            choiceA = "553", choiceB = "625", choiceC = "337", choiceD = "649",
            correctAnswer = "C"
        ),


        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
            questionText = "If a + b = 3, a² + b² = 5, find a³ + b³.",
            choiceA = "18", choiceB = "9", choiceC = "20", choiceD = "21",
            correctAnswer = "B"
        ),

            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Word Problems",
                questionText = "A boat goes 20 km downstream in 2 hours and the same distance upstream in 4 hours. Find the speed of the boat in still water.",
                choiceA = "10 km/hr", choiceB = "7.5 km/hr", choiceC = "15 km/hr", choiceD = "8 km/hr",
                correctAnswer = "B"
            ),

                    Problem(
                    userId = "global", source = "preset", topic = "Algebra", subtopic = "Word Problems",
            questionText = "A train covers a distance of 120 km at a speed of 40 km/hr and returns at 60 km/hr. What is the average speed of the train?",
            choiceA = "48 km/hr", choiceB = "50 km/hr", choiceC = "52 km/hr", choiceD = "54 km/hr",
            correctAnswer = "A"
        ),

            Problem(
                userId = "global", source = "preset", topic = "Algebra", subtopic = "Word Problems",
                questionText = "The sum of two numbers is 180. If one number is twice the other, what are the two numbers?",
                choiceA = "60 and 120", choiceB = "70 and 110", choiceC = "80 and 100", choiceD = "90 and 90",
                correctAnswer = "A"
            ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Word Problems",
            questionText = "Two taps can fill a tank in 12 minutes and 18 minutes respectively. If both taps are opened together, in how many minutes will the tank be full?",
            choiceA = "6 minutes", choiceB = "7.2 minutes", choiceC = "8 minutes", choiceD = "10 minutes",
            correctAnswer = "B"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Word Problems",
            questionText = "A steamer goes 30 km downstream in 2 hours and the same distance upstream in 3 hours. What is the speed of the stream?",
            choiceA = "2 km/hr", choiceB = "2.5 km/hr", choiceC = "4 km/hr", choiceD = "5 km/hr",
            correctAnswer = "B"
        ),


        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Word Problems",
            questionText = "The average of 8 numbers is 15. If one number is removed, the average becomes 14. Find the number removed.",
            choiceA = "15", choiceB = "20", choiceC = "22", choiceD = "23",
            correctAnswer = "C"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Word Problems",
            questionText = "The sum of ages of a father and son is 50 years. 5 years ago, the father’s age was 7 times that of his son. Find their present ages.",
            choiceA = "35 and 15", choiceB = "40 and 10", choiceC = "38 and 12", choiceD = "45 and 5",
            correctAnswer = "B"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Word Problems",
            questionText = "John had three apples. Anisha grows 7 more in her backyard for each of the 7 days of the week and donates them to John. How many apples does John have now?",
            choiceA = "42", choiceB = "32", choiceC = "57", choiceD = "52",
            correctAnswer = "D"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Word Problems",
            questionText = "Rs. 840 is divided among A, B, and C such that A’s share is twice B’s and B’s share is thrice C’s. Find A’s share.",
            choiceA = "Rs. 360", choiceB = "Rs. 504", choiceC = "Rs. 480", choiceD = "Rs. 300",
            correctAnswer = "B"
        ),
            Problem(
                userId = "global", source = "preset", topic = "Statistics", subtopic = "Mean",
                questionText = "The mean of 10 numbers is 25. If one number 34 is removed, what is the new mean of the remaining numbers?",
                choiceA = "24.5", choiceB = "24", choiceC = "23", choiceD = "25.5",
                correctAnswer = "B"
            ),

                    Problem(
                    userId = "global", source = "preset", topic = "Statistics", subtopic = "Mean",
            questionText = "The mean of 50 observations was calculated as 80. Later it was found that one value 96 was wrongly taken as 69. Find the correct mean.",
            choiceA = "80.54", choiceB = "81", choiceC = "79.46", choiceD = "82",
            correctAnswer = "A"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Mean",
            questionText = "The mean of 20 numbers is 30. If each number is increased by 5, what will be the new mean?",
            choiceA = "40", choiceB = "30", choiceC = "25", choiceD = "35",
            correctAnswer = "D"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Mean",
            questionText = "The mean marks of 40 students is 60. If the marks of one student is changed from 80 to 40, what will be the new mean?",
            choiceA = "60", choiceB = "59", choiceC = "58", choiceD = "61",
            correctAnswer = "B"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Mean",
            questionText = "The mean of 5 numbers is 18. If four of them are 12, 15, 20, and 25, find the fifth number.",
            choiceA = "26", choiceB = "20", choiceC = "24", choiceD = "18",
            correctAnswer = "D"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Mean",
            questionText = "The mean of 25 observations is 36. If each observation is multiplied by 2, what will be the new mean?",
            choiceA = "72", choiceB = "70", choiceC = "36", choiceD = "74",
            correctAnswer = "A"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Mean",
            questionText = "The mean of 8 observations is 15. If one observation 21 is replaced by 9, what will be the new mean?",
            choiceA = "13.5", choiceB = "14", choiceC = "14.25", choiceD = "15.5",
            correctAnswer = "A"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Mean",
            questionText = "The mean of 6 numbers is 8. If one more number is added, the mean becomes 9. Find the new number.",
            choiceA = "18", choiceB = "12", choiceC = "15", choiceD = "20",
            correctAnswer = "C"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Mean",
            questionText = "The mean of 10 observations is 20. If each observation is decreased by 3, what will be the new mean?",
            choiceA = "19", choiceB = "18", choiceC = "17", choiceD = "16",
            correctAnswer = "C"
        ),

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Mean",
            questionText = "The mean of 12 numbers is 18. If two numbers 20 and 22 are excluded, what will be the mean of the remaining 10 numbers?",
            choiceA = "17.6", choiceB = "17.4", choiceC = "18", choiceD = "17",
            correctAnswer = "B"
        ),

            Problem(
                userId = "global", source = "preset", topic = "Statistics", subtopic = "Median",
                questionText = "Find the median of: 12, 25, 7, 19, 30, 15, 10",
                choiceA = "15", choiceB = "19", choiceC = "17", choiceD = "20",
                correctAnswer = "A"
            ),
// Sorted: 7,10,12,15,19,25,30 → Median = 15.

                    Problem(
                    userId = "global", source = "preset", topic = "Statistics", subtopic = "Median",
            questionText = "The median of the data: 45, 32, 50, 40, 38, 42 is:",
            choiceA = "40", choiceB = "41", choiceC = "42", choiceD = "43",
            correctAnswer = "B"
        ),
// Sorted: 32,38,40,42,45,50 → Median = (40+42)/2 = 41.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Median",
            questionText = "The median of 7 observations is 20. If the numbers are 12, 15, 18, x, 22, 25, 28, find x.",
            choiceA = "18", choiceB = "19", choiceC = "20", choiceD = "21",
            correctAnswer = "C"
        ),
// Middle (4th) term = x = 20.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Median",
            questionText = "The median of the numbers 8, 14, x, 20, 26, 30, 34 is 23. Find x.",
            choiceA = "20", choiceB = "22", choiceC = "23", choiceD = "24",
            correctAnswer = "C"
        ),
// Middle (4th) term = x = 23.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Median",
            questionText = "Find the median of: 5, 8, 12, 15, 20, 24, 30, 35",
            choiceA = "16.5", choiceB = "17.5", choiceC = "18", choiceD = "19",
            correctAnswer = "B"
        ),
// Sorted already. Median = (15+20)/2 = 17.5.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Median",
            questionText = "The median of the data: 60, 75, 55, 80, 70, 65, 85, 90 is:",
            choiceA = "70", choiceB = "72.5", choiceC = "75", choiceD = "73",
            correctAnswer = "B"
        ),
// Sorted: 55,60,65,70,75,80,85,90 → Median = (70+75)/2 = 72.5.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Median",
            questionText = "The median of the numbers 11, 18, 14, 25, 21, 16, x is 18. Find x.",
            choiceA = "17", choiceB = "18", choiceC = "19", choiceD = "20",
            correctAnswer = "B"
        ),
// Sorted with x. To make 4th term = 18 → x = 18.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Median",
            questionText = "Find the median of: 100, 85, 95, 70, 90, 80",
            choiceA = "85", choiceB = "87.5", choiceC = "90", choiceD = "92.5",
            correctAnswer = "B"
        ),
// Sorted: 70,80,85,90,95,100 → Median = (85+90)/2 = 87.5.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Median",
            questionText = "The median of the data: 40, 35, 50, 30, 45, x, 55 is 42. Find x.",
            choiceA = "41", choiceB = "42", choiceC = "43", choiceD = "44",
            correctAnswer = "B"
        ),
// Sorted 7 terms → median = 4th term. To get 42 → x = 42.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Median",
            questionText = "The median of 10 numbers is 26. If the 5th and 6th terms are x and x+4, find x.",
            choiceA = "24", choiceB = "25", choiceC = "26", choiceD = "27",
            correctAnswer = "A"
        ),


            Problem(
                userId = "global", source = "preset", topic = "Statistics", subtopic = "Range",
                questionText = "The range of 12, 18, 25, 40, 55 is:",
                choiceA = "40", choiceB = "43", choiceC = "45", choiceD = "42",
                correctAnswer = "B"
            ),
// 55 - 12 = 43.

                    Problem(
                    userId = "global", source = "preset", topic = "Statistics", subtopic = "Range",
            questionText = "The range of 7, 15, 20, 30, 42, x where x = 50 is:",
            choiceA = "43", choiceB = "45", choiceC = "50", choiceD = "42",
            correctAnswer = "A"
        ),
// 50 - 7 = 43.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Range",
            questionText = "If the maximum of a dataset is 120 and minimum is 35, find the range.",
            choiceA = "80", choiceB = "85", choiceC = "90", choiceD = "95",
            correctAnswer = "B"
        ),
// 120 - 35 = 85.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Range",
            questionText = "For the data 20, 25, 30, 35, 40, 50, x, if the range is 40, find x.",
            choiceA = "55", choiceB = "60", choiceC = "65", choiceD = "70",
            correctAnswer = "B"
        ),
// Minimum=20, range=40 → max=60 → x=60.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Range",
            questionText = "The marks of 6 students are 45, 52, 60, 68, 70, x. If the range is 35, find x.",
            choiceA = "75", choiceB = "78", choiceC = "80", choiceD = "82",
            correctAnswer = "C"
        ),
// Minimum=45, max=80 → range=35 → x=80.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Range",
            questionText = "The range of the numbers 2x+1, 3x, 5x-2 when x=5 is:",
            choiceA = "10", choiceB = "12", choiceC = "14", choiceD = "15",
            correctAnswer = "B"
        ),
// Values: 11, 15, 23 → range = 23 - 11 = 12.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Range",
            questionText = "For numbers 10, 15, 20, 25, y, if the range is 20, find y.",
            choiceA = "30", choiceB = "32", choiceC = "35", choiceD = "40",
            correctAnswer = "A"
        ),
// Minimum=10, range=20 → maximum=30 → y=30.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Range",
            questionText = "For 8, 12, 15, 20, z, if the range is 17, find z.",
            choiceA = "22", choiceB = "23", choiceC = "25", choiceD = "26",
            correctAnswer = "C"
        ),
// Minimum=8, range=17 → maximum=25 → z=25.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Range",
            questionText = "The maximum of a dataset is 90. If the range is 54 and minimum is m, find m.",
            choiceA = "34", choiceB = "35", choiceC = "36", choiceD = "37",
            correctAnswer = "C"
        ),
// 90 - m = 54 → m=36.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Range",
            questionText = "The range of 3a, 4a+2, 6a when a=4 is:",
            choiceA = "10", choiceB = "12", choiceC = "14", choiceD = "16",
            correctAnswer = "B"
        ),
// Values: 12, 18, 24 → range=12.

// ---------------- MODE QUESTIONS ----------------

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Mode",
            questionText = "Find the mode of: 2, 4, 6, 6, 8, 10, 6, 12",
            choiceA = "4", choiceB = "6", choiceC = "8", choiceD = "10",
            correctAnswer = "B"
        ),
// 6 occurs most.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Mode",
            questionText = "Find the mode of: 3, 5, 7, 7, 9, 11, 11, 11",
            choiceA = "7", choiceB = "9", choiceC = "11", choiceD = "3",
            correctAnswer = "C"
        ),
// 11 occurs 3 times.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Mode",
            questionText = "Find the mode of: 15, 20, 25, 25, 30, 30, 30, 35",
            choiceA = "25", choiceB = "30", choiceC = "35", choiceD = "20",
            correctAnswer = "B"
        ),
// 30 occurs 3 times.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Mode",
            questionText = "For the dataset 12, 18, 12, 20, 25, 18, 18, 30, find the mode.",
            choiceA = "12", choiceB = "18", choiceC = "20", choiceD = "25",
            correctAnswer = "B"
        ),
// 18 occurs 3 times.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Mode",
            questionText = "The mode of: 40, 42, 42, 44, 46, 44, 44 is:",
            choiceA = "42", choiceB = "44", choiceC = "46", choiceD = "40",
            correctAnswer = "B"
        ),
// 44 occurs most.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Mode",
            questionText = "If in a dataset x appears 5 times, y appears 3 times, z appears 4 times, find the mode.",
            choiceA = "x", choiceB = "y", choiceC = "z", choiceD = "None",
            correctAnswer = "A"
        ),
// x occurs most.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Mode",
            questionText = "Find the mode of: 100, 110, 120, 120, 130, 140, 120, 150",
            choiceA = "110", choiceB = "120", choiceC = "130", choiceD = "140",
            correctAnswer = "B"
        ),
// 120 occurs 3 times.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Mode",
            questionText = "The mode of: 8, 10, 10, 12, 14, 16, 10, 18 is:",
            choiceA = "8", choiceB = "10", choiceC = "12", choiceD = "16",
            correctAnswer = "B"
        ),
// 10 occurs most.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Mode",
            questionText = "The mode of the following data: 20, 25, 25, 30, 30, 35 is:",
            choiceA = "25 and 30", choiceB = "30 and 35", choiceC = "25 and 35", choiceD = "All equal",
            correctAnswer = "A"
        ),
// 25 and 30 both appear twice → bimodal.

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Mode",
            questionText = "If the numbers are: x, x, x, 12, 15, 18, 20, the mode is:",
            choiceA = "12", choiceB = "15", choiceC = "18", choiceD = "x",
            correctAnswer = "D"
        )

        )


        setContent {
            ChudarAppTheme {
                val navController = rememberNavController()


                LaunchedEffect(Unit) {
                    val existing = database.problemDao().getAllProblems()
                    if (existing.isEmpty()) {
                        database.problemDao().insertAll(problems)
                    }
                }

                NavigationGraph(
                    navController = navController,
                    userViewModel = userViewModel,
                    userDao = userDao
                )
            }
        }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    userViewModel: UserViewModel,
    userDao: UserDao
) {
    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(
                viewModel = userViewModel,
                onLoginSuccess = { navController.navigate("mainmenu") }
            )
        }

        composable(route = "algebra") {
            AlgebraScreen(
                navController = navController,
                onSubtopicClick = { subtopic ->
                    navController.navigate("algebraQuiz/$subtopic")
                },
                onTestClick = {
                    navController.navigate("algebraTest")
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable("algebraTest") {
            AlgebraTestScreen(
                userViewModel = userViewModel,
                onBack = { navController.popBackStack() }
            )
        }


        composable("algebraQuiz/{subtopic}") { backStackEntry ->
            val subtopic = backStackEntry.arguments?.getString("subtopic") ?: ""
            AlgebraQuizScreen(
                viewModel = userViewModel,
                subtopic = subtopic,
                onBack = { navController.popBackStack() }
            )
        }
        composable("algebraGenerate") {
            AlgebraGenerateScreen(
                userViewModel = userViewModel,
                onBack = { navController.popBackStack()
                },

                )
        }

        composable("mainmenu") {
            MainMenuScreen(
                viewModel = userViewModel,
                onPracticeClick = { navController.navigate("practice") }
            )
        }

        composable("practice") {
            PracticeScreen(
                viewModel = userViewModel,
                onAlgebraClick = { navController.navigate("algebra") },
                onGeometryClick = { navController.navigate("geometry") },
                onNumberTheoryClick = { navController.navigate("numbertheory") },
                onStatisticsClick = { navController.navigate("statistics") },
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = "numberTheory") {
            NumberTheoryScreen(
                navController = navController,
                onSubtopicClick = { subtopic ->
                    navController.navigate("numberTheoryQuiz/$subtopic")
                },
                onTestClick = {
                    navController.navigate("numberTheoryTest")
                },
                userViewModel = userViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable("numberTheoryTest") {
            NumberTheoryTestScreen(
                userViewModel = userViewModel,
                onBack = { navController.popBackStack() }
            )
        }


        composable("numberTheoryQuiz/{subtopic}") { backStackEntry ->
            val subtopic = backStackEntry.arguments?.getString("subtopic") ?: ""
            NumberTheoryQuizScreen(
                viewModel = userViewModel,
                subtopic = subtopic,
                onBack = { navController.popBackStack() }
            )
        }
        composable("numberTheoryGenerate") {
            NumberTheoryGenerateScreen(
                userViewModel = userViewModel,
                onBack = { navController.popBackStack()
                         },

            )
        }



        composable("geometry") {
            GeometryScreen(
                viewModel = userViewModel,
                userDao = userDao,
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = "statistics") {
            StatisticsScreen(
                onSubtopicClick = { subtopic ->
                    navController.navigate("statisticsQuiz/$subtopic")
                },
                onTestClick = {
                    navController.navigate("statisticsTest")
                },
                navController = navController,
                onBack = { navController.popBackStack() }
            )
        }
        composable("statisticsTest") {
            StatisticsTestScreen(
                userViewModel = userViewModel,
                onBack = { navController.popBackStack() }
            )
        }


        composable("statisticsQuiz/{subtopic}") { backStackEntry ->
            val subtopic = backStackEntry.arguments?.getString("subtopic") ?: ""
            StatisticsQuizScreen(
                viewModel = userViewModel,
                subtopic = subtopic,
                onBack = { navController.popBackStack() }
            )
        }

        composable("statisticsGenerate") {
            StatisticsGenerateScreen(
                userViewModel = userViewModel,
                onBack = { navController.popBackStack()
                },

                )
        }



    }
}
