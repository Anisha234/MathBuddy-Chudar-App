package com.aachudar.chudarapp.data

fun getPresetTestStatistics(index: Int): List<Problem> {

    // ✅ NEW: Number Theory full test (20 questions across all subtopics)
    val testStatistics = listOf(
        // ---------------- MEAN (5) ----------------

        Problem(
            userId = "global", source = "preset", topic = "Statistics", subtopic = "Mean",
            questionText = "The mean of 5 numbers is 20. If one number 25 is replaced by 35, what is the new mean?",
            choiceA = "22", choiceB = "21", choiceC = "23", choiceD = "20",
            correctAnswer = "A"
        ),
// Old total=100. New total=110. Mean=22 → Correct=A.

                Problem(
                userId = "global", source = "preset", topic = "Statistics", subtopic = "Mean",
        questionText = "The mean of 9 numbers is 18. If each number is increased by 4, the new mean is:",
        choiceA = "18", choiceB = "22", choiceC = "21", choiceD = "20",
        correctAnswer = "B"
    ),
// New mean=18+4=22.

    Problem(
        userId = "global", source = "preset", topic = "Statistics", subtopic = "Mean",
        questionText = "The mean of 8 numbers is 12. If the total of 7 of them is 80, find the 8th number.",
        choiceA = "12", choiceB = "14", choiceC = "16", choiceD = "18",
        correctAnswer = "C"
    ),
// Total=96. 8th=16.

    Problem(
        userId = "global", source = "preset", topic = "Statistics", subtopic = "Mean",
        questionText = "The mean of x, 10, 20 is 15. Find x.",
        choiceA = "10", choiceB = "15", choiceC = "20", choiceD = "25",
        correctAnswer = "B"
    ),
// (x+30)/3=15 → x=15.

    Problem(
        userId = "global", source = "preset", topic = "Statistics", subtopic = "Mean",
        questionText = "The mean of 5 consecutive even numbers is 36. Find the largest number.",
        choiceA = "40", choiceB = "42", choiceC = "38", choiceD = "44",
        correctAnswer = "A"
    ),
// Numbers: 32,34,36,38,40 → largest=40.

// ---------------- MEDIAN (5) ----------------

    Problem(
        userId = "global", source = "preset", topic = "Statistics", subtopic = "Median",
        questionText = "Find the median of: 22, 15, 30, 10, 25",
        choiceA = "20", choiceB = "22", choiceC = "23", choiceD = "25",
        correctAnswer = "B"
    ),
// Sorted: 10,15,22,25,30 → median=22.

    Problem(
        userId = "global", source = "preset", topic = "Statistics", subtopic = "Median",
        questionText = "The median of 6 numbers 5, 8, 12, 14, 16, x is 13. Find x.",
        choiceA = "14", choiceB = "6", choiceC = "12", choiceD = "10",
        correctAnswer = "A"
    ),
// Sorted median=(12+x)/2=13 → x=14. Correct=A (fix).

    Problem(
        userId = "global", source = "preset", topic = "Statistics", subtopic = "Median",
        questionText = "Find the median of: 45, 30, 25, 50, 40, 35, 20",
        choiceA = "35", choiceB = "40", choiceC = "30", choiceD = "45",
        correctAnswer = "A"
    ),
// Sorted: 20,25,30,35,40,45,50 → median=35.

    Problem(
        userId = "global", source = "preset", topic = "Statistics", subtopic = "Median",
        questionText = "The median of 9 observations is 28. If the 5th observation is x, find x.",
        choiceA = "28", choiceB = "29", choiceC = "27", choiceD = "30",
        correctAnswer = "A"
    ),
// For odd count, median=middle=5th → x=28.

    Problem(
        userId = "global", source = "preset", topic = "Statistics", subtopic = "Median",
        questionText = "Find the median of: 3, 8, 12, 18, 25, 30, 32, 40",
        choiceA = "20", choiceB = "21.5", choiceC = "22", choiceD = "23.5",
        correctAnswer = "B"
    ),
// 8 numbers → median=(18+25)/2=21.5.

// ---------------- MODE (5) ----------------

    Problem(
        userId = "global", source = "preset", topic = "Statistics", subtopic = "Mode",
        questionText = "Find the mode of: 14, 18, 22, 18, 25, 18, 30",
        choiceA = "18", choiceB = "22", choiceC = "25", choiceD = "30",
        correctAnswer = "A"
    ),
// 18 occurs most.

    Problem(
        userId = "global", source = "preset", topic = "Statistics", subtopic = "Mode",
        questionText = "The mode of: 12, 15, 15, 20, 22, 22, 22, 25 is:",
        choiceA = "15", choiceB = "20", choiceC = "22", choiceD = "25",
        correctAnswer = "C"
    ),
// 22 occurs 3 times.

    Problem(
        userId = "global", source = "preset", topic = "Statistics", subtopic = "Mode",
        questionText = "Find the mode of: 5, 7, 9, 11, 13, 11, 9, 11",
        choiceA = "9", choiceB = "11", choiceC = "13", choiceD = "7",
        correctAnswer = "B"
    ),
// 11 occurs most.

    Problem(
        userId = "global", source = "preset", topic = "Statistics", subtopic = "Mode",
        questionText = "Find the mode of: 6, 8, 10, 10, 12, 14, 14, 14",
        choiceA = "10", choiceB = "12", choiceC = "14", choiceD = "8",
        correctAnswer = "C"
    ),
// 14 occurs 3 times.

    Problem(
        userId = "global", source = "preset", topic = "Statistics", subtopic = "Mode",
        questionText = "Find the mode of: 25, 30, 30, 35, 40, 35, 35, 30",
        choiceA = "30", choiceB = "35", choiceC = "40", choiceD = "25",
        correctAnswer = "B"
    ),
// 35 occurs 3 times, 30 occurs 2.

// ---------------- RANGE (5) ----------------

    Problem(
        userId = "global", source = "preset", topic = "Statistics", subtopic = "Range",
        questionText = "Find the range of: 15, 25, 35, 40, 45",
        choiceA = "25", choiceB = "30", choiceC = "35", choiceD = "20",
        correctAnswer = "B"
    ),
// 45-15=30.

    Problem(
        userId = "global", source = "preset", topic = "Statistics", subtopic = "Range",
        questionText = "The range of: 50, 55, 65, 70, 75, 80 is:",
        choiceA = "25", choiceB = "30", choiceC = "35", choiceD = "40",
        correctAnswer = "B"
    ),
// 80-50=30.

    Problem(
        userId = "global", source = "preset", topic = "Statistics", subtopic = "Range",
        questionText = "If the maximum of a dataset is 120 and minimum is 95, the range is:",
        choiceA = "20", choiceB = "25", choiceC = "30", choiceD = "35",
        correctAnswer = "B"
    ),
// 120-95=25.

    Problem(
        userId = "global", source = "preset", topic = "Statistics", subtopic = "Range",
        questionText = "The range of numbers 8, 14, 20, 25, 28, 32 is:",
        choiceA = "20", choiceB = "22", choiceC = "24", choiceD = "25",
        correctAnswer = "C"
    ),
// 32-8=24.

    Problem(
        userId = "global", source = "preset", topic = "Statistics", subtopic = "Range",
        questionText = "For numbers 2, 5, 7, 12, 15, x, if the range is 20, find x.",
        choiceA = "18", choiceB = "20", choiceC = "21", choiceD = "22",
        correctAnswer = "D"
    ),
// Minimum=2, range=20 → maximum=22. So x=22.

    ).shuffled()


    return when (index) {
        1 -> testStatistics   // ✅ our new Number Theory-specific test
        else -> testStatistics
    }
}
