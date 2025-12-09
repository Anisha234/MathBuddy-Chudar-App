package com.aachudar.chudarapp.data

fun getPresetTestAlgebra(index: Int): List<Problem> {

    // ✅ NEW: Number Theory full test (20 questions across all subtopics)
    val testAlgebra = listOf(
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Systems of Equations",
            questionText = "If 3x + 2y = 18 and 2x + y = 11, find x and y.",
            choiceA = "x = 4, y = 3", choiceB = "x = 5, y = 2", choiceC = "x = 3, y = 6", choiceD = "x = 2, y = 5",
            correctAnswer = "A"
        )
        ,
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Systems of Equations",
            questionText = "If x + y = 12 and x - y = 4, what is x² - y²?",
            choiceA = "32", choiceB = "48", choiceC = "64", choiceD = "80",
            correctAnswer = "B"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Word Problems",
            questionText = "A father is three times as old as his son. In 10 years, the sum of their ages will be 80. What are their ages now?",
            choiceA = "50 and 20", choiceB = "45 and 15", choiceC = "60 and 20", choiceD = "40 and 10",
            correctAnswer = "B"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Factorization",
            questionText = "Factorize completely: x³ + 3x²y + 3xy² + y³.",
            choiceA = "(x + y)(x² + 2xy + y²)", choiceB = "(x + y)³", choiceC = "(x + y)(x² + y²)", choiceD = "Prime",
            correctAnswer = "B"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Squares & Cubes",
            questionText = "Find the smallest number by which 180 must be multiplied to make it a perfect square.",
            choiceA = "2", choiceB = "3", choiceC = "5", choiceD = "6",
            correctAnswer = "C"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Systems of Equations",
            questionText = "If 5x + 2y = 20 and 3x + 4y = 18, what is the value of x and y?",
            choiceA = "x = 2, y = 3", choiceB = "x = 4, y = 0", choiceC = "x = 2, y = 5", choiceD = "x = 1, y = 5",
            correctAnswer = "A"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
            questionText = "If 2ⁿ - 2ⁿ⁻² = 3, then n = ?",
            choiceA = "1", choiceB = "2", choiceC = "3", choiceD = "4",
            correctAnswer = "B"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Word Problems",
            questionText = "Two taps can fill a tank in 15 and 20 minutes. A waste pipe empties it in 30 minutes. If all three are opened together, how long will it take to fill the tank?",
            choiceA = "10 minutes", choiceB = "12 minutes", choiceC = "15 minutes", choiceD = "18 minutes",
            correctAnswer = "B"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Squares & Cubes",
            questionText = "What is the units digit of 637²?",
            choiceA = "7", choiceB = "9", choiceC = "3", choiceD = "1",
            correctAnswer = "B"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Systems of Equations",
            questionText = "The sum of two numbers is 40. Their difference is 8. What are the numbers?",
            choiceA = "20 and 28", choiceB = "24 and 16", choiceC = "18 and 22", choiceD = "30 and 10",
            correctAnswer = "B"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Word Problems",
            questionText = "A boat travels 48 km downstream in 3 hours and the same distance upstream in 6 hours. Find the speed of the stream if the boat's still-water speed is 12 km/hr.",
            choiceA = "2 km/hr", choiceB = "3 km/hr", choiceC = "4 km/hr", choiceD = "5 km/hr",
            correctAnswer = "C"
        ),
                Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Factorization",
            questionText = "Factorize: 9x²y² + 6xyz + z².",
            choiceA = "(3xy + z)²", choiceB = "(3xy - z)²", choiceC = "(3xy + z)(3xy - z)", choiceD = "Prime",
            correctAnswer = "A"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Squares & Cubes",
            questionText = "Which is the smallest square number divisible by 8, 9, and 10?",
            choiceA = "3600", choiceB = "900", choiceC = "1600", choiceD = "14400",
            correctAnswer = "A"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Algebraic Identities",
            questionText = "(x + 2y)² - (x - 2y)² equals?",
            choiceA = "0", choiceB = "2(x+2y)²", choiceC = "2(x-2y)²", choiceD = "8xy",
            correctAnswer = "D"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Word Problems",
            questionText = "A general has 335250 soldiers. He arranges them in a square formation and finds 9 left over. How many are there in each row?",
            choiceA = "579", choiceB = "580", choiceC = "581", choiceD = "582",
            correctAnswer = "A"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Equations",
            questionText = "Solve for x: x² - 7x + 12 = 0.",
            choiceA = "x = 3 or 4", choiceB = "x = 2 or 6", choiceC = "x = 4 or 6", choiceD = "x = 1 or 12",
            correctAnswer = "A"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Systems of Equations",
            questionText = "Solve for x and y: 7x + 5y = 65, 3x - 2y = 3.",
            choiceA = "x = 7, y = 6", choiceB = "x = 5, y = 6", choiceC = "x = 6, y = 7", choiceD = "x = 4, y = 7",
            correctAnswer = "B"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Squares & Cubes",
            questionText = "Find the cube root of 1331.",
            choiceA = "9", choiceB = "10", choiceC = "11", choiceD = "12",
            correctAnswer = "C"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Word Problems",
            questionText = "The LCM of two numbers is 252 and their HCF is 21. If one number is 84, find the other.",
            choiceA = "42", choiceB = "72", choiceC = "75", choiceD = "63",
            correctAnswer = "D"
        ),
        Problem(
            userId = "global", source = "preset", topic = "Algebra", subtopic = "Exponents",
            questionText = "Simplify: (5² + 12²)^(1/2).",
            choiceA = "12", choiceB = "13", choiceC = "14", choiceD = "15",
            correctAnswer = "B"
        )
    ).shuffled()


    return when (index) {
        1 -> testAlgebra   // ✅ our new Number Theory-specific test
        else -> testAlgebra
    }
}
