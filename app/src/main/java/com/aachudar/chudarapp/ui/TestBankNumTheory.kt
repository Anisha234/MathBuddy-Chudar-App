package com.aachudar.chudarapp.data

fun getPresetTestNumTheory(index: Int): List<Problem> {

    // ✅ NEW: Number Theory full test (20 questions across all subtopics)
    val testNumberTheory = listOf(
        // Test of divisibility
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Test of divisibility",
            questionText = "Which of the following numbers is divisible by 15?", choiceA = "280", choiceB = "310", choiceC = "350", choiceD = "285", correctAnswer = "D"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Test of divisibility",
            questionText = "If the number 97215_6 is divisible by 11, find the smallest missing number", choiceA = "1", choiceB = "3", choiceC = "2", choiceD = "0", correctAnswer = "B"),

        // Order of operations
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Order of operations",
            questionText = "Find the value of 6 + 11 × 2² – 8 ÷ 2?", choiceA = "0", choiceB = "30", choiceC = "46", choiceD = "12", correctAnswer = "C"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Order of operations",
            questionText = "(7 + 9) × 2 – 4² ÷ 2 = ?", choiceA = "18", choiceB = "24", choiceC = "27", choiceD = "4.5", correctAnswer = "B"),

        // Integers – word problems
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Integers - word problems",
            questionText = "Latha is three times older than Rani. After 10 years the sum of their ages will be 80. What is Rani's age?", choiceA = "12", choiceB = "20", choiceC = "15", choiceD = "16", correctAnswer = "C"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Integers - word problems",
            questionText = "A submarine is at –300 m. It rises 120 m, goes down 20 m, and rises 40m. Where is it now?", choiceA = "–420 m", choiceB = "–180 m", choiceC = "–160 m", choiceD = "–120 m", correctAnswer = "C"),

        // Fractions – Conversion
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions – Conversion",
            questionText = "Convert 10/3 into a mixed fraction.", choiceA = "2 1/3", choiceB = "3 1/3", choiceC = "1 2/3", choiceD = "2 2/3", correctAnswer = "B"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions – Conversion",
            questionText = "Convert 4 2/5 to an improper fraction.", choiceA = "22/5", choiceB = "24/5", choiceC = "18/5", choiceD = "21/5", correctAnswer = "A"),

        // Fractions – Basic operations
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions – Basic operations",
            questionText = "Simplify: 2/3 x 1/6 x 4/5", choiceA = "1/90", choiceB = "7/90", choiceC = "4/45", choiceD = "1/15", correctAnswer = "C"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions – Basic operations",
            questionText = "Simplify: 5/8 - 1/4", choiceA = "3/8", choiceB = "1/2", choiceC = "1/8", choiceD = "5/12", correctAnswer = "A"),

        // Fractions – Word problems
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions – Word problems",
            questionText = "Anna takes 3/4 of a pizza, and Bob takes 1/4 of the remaining. How much pizza is left?", choiceA = "0", choiceB = "3/16", choiceC = "1/4", choiceD = "4/5", correctAnswer = "B"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Fractions – Word problems",
            questionText = "A 116 2/3 m long rope is cut into ten equal pieces. The length of each piece of the rope is", choiceA = "8 1/2 m", choiceB = "10 2/3 m", choiceC = "12 1/3 m", choiceD = "11 2/3 m", correctAnswer = "D"),
/*
        // Decimals – Conversion
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Decimals – Conversion",
            questionText = "Convert 0.75 to a fraction.", choiceA = "3/5", choiceB = "3/4", choiceC = "2/3", choiceD = "1/2", correctAnswer = "B"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Decimals – Conversion",
            questionText = "Convert 7/20 to a decimal.", choiceA = "0.25", choiceB = "0.35", choiceC = "0.45", choiceD = "0.4", correctAnswer = "D"),
*/
        // Decimals – Basic operations
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Decimals – Basic operations",
            questionText = "The value of (-0.01) cubed is", choiceA = "-.0001", choiceB = "-0.000001", choiceC = "-1/10000", choiceD = "1/1000000", correctAnswer = "B"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Decimals – Basic operations",
            questionText = "Find: 7.2 – 4.5", choiceA = "2.5", choiceB = "2.6", choiceC = "2.7", choiceD = "2.8", correctAnswer = "C"),

        // Rational numbers – Basic operations
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Rational numbers – Basic operations",
            questionText = "Simplify: (2/3) × (9/4)", choiceA = "3/2", choiceB = "1/2", choiceC = "2/9", choiceD = "3/4", correctAnswer = "A"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Rational numbers – Basic operations",
            questionText = "Simplify: (11/6) ÷ (11/3)", choiceA = "1/4", choiceB = "1/2", choiceC = "2/3", choiceD = "3/5", correctAnswer = "B"),

        // Rational numbers – Comparison
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Rational numbers – Comparison",
            questionText = "Arrange in ascending order: 3/7, 4/9, 1/3", choiceA = "4/9 < 3/7 < 1/3", choiceB = "1/3 < 3/7 < 4/9", choiceC = "3/7< 1/3 < 4/9", choiceD = "4/9 < 1/3 < 3/7", correctAnswer = "B"),
        Problem(userId = "global", source = "preset", topic = "Number Theory", subtopic = "Rational numbers – Comparison",
            questionText = "If one-fourth of one third of one sixth of a number is 15, then the number is ", choiceA = "1080", choiceB = "950", choiceC = "970", choiceD = "1200", correctAnswer = "A")
,
                Problem(
                userId = "global", source = "preset", topic = "Number Theory", subtopic = "LCM and GCD",
        questionText = "Find the LCM of 40 and 56.",
        choiceA = "280", choiceB = "560", choiceC = "360", choiceD = "480",
        correctAnswer = "A"
    ),

    Problem(
        userId = "global", source = "preset", topic = "Number Theory", subtopic = "LCM and GCD",
        questionText = "If the HCF of two numbers is 12 and their LCM is 180, find the product of the two numbers.",
        choiceA = "3240", choiceB = "2400", choiceC = "2160", choiceD = "2880",
        correctAnswer = "C"
    )
    ).shuffled()

    return when (index) {
        1 -> testNumberTheory   // ✅ our new Number Theory-specific test
        else -> testNumberTheory
    }
}
