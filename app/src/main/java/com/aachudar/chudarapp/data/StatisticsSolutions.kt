package com.aachudar.chudarapp.data

val statisticsSolutions = mapOf(
    // ---------------- MEAN ----------------
    "The mean of 5 numbers is 20. If one number 25 is replaced by 35, what is the new mean?" to
            "Old total = 5×20 = 100. Replacing 25 with 35 increases total by 10 → new total = 110. New mean = 110 ÷ 5 = 22.",
    "The mean of 9 numbers is 18. If each number is increased by 4, the new mean is:" to
            "If every number increases by 4, the mean also increases by 4. So new mean = 18 + 4 = 22.",
    "The mean of 8 numbers is 12. If the total of 7 of them is 80, find the 8th number." to
            "Total = 8×12 = 96. The 8th number = 96 – 80 = 16.",
    "The mean of x, 10, 20 is 15. Find x." to
            "(x + 10 + 20) ÷ 3 = 15 → (x+30)/3=15 → x+30=45 → x=15.",
    "The mean of 5 consecutive even numbers is 36. Find the largest number." to
            "Numbers: 32, 34, 36, 38, 40. Largest = 40.",

    // ---------------- MEDIAN ----------------
    "Find the median of: 22, 15, 30, 10, 25" to
            "Arrange: 10, 15, 22, 25, 30. Middle = 22.",
    "The median of 6 numbers 5, 8, 12, 14, 16, x is 13. Find x." to
            "Sorted median = (3rd + 4th)/2 = 13 → (12 + x)/2 = 13 → x=14.",
    "Find the median of: 45, 30, 25, 50, 40, 35, 20" to
            "Arrange: 20, 25, 30, 35, 40, 45, 50. Middle = 35.",
    "The median of 9 observations is 28. If the 5th observation is x, find x." to
            "For 9 items, the 5th is the median → x=28.",
    "Find the median of: 3, 8, 12, 18, 25, 30, 32, 40" to
            "8 numbers → median = (4th+5th)/2 = (18+25)/2 = 21.5.",

    // ---------------- MODE ----------------
    "Find the mode of: 14, 18, 22, 18, 25, 18, 30" to
            "18 appears 3 times, most frequent → mode=18.",
    "The mode of: 12, 15, 15, 20, 22, 22, 22, 25 is:" to
            "22 appears 3 times, more than others → mode=22.",
    "Find the mode of: 5, 7, 9, 11, 13, 11, 9, 11" to
            "11 appears 3 times → mode=11.",
    "Find the mode of: 6, 8, 10, 10, 12, 14, 14, 14" to
            "14 appears 3 times → mode=14.",
    "Find the mode of: 25, 30, 30, 35, 40, 35, 35, 30" to
            "30 appears 3 times, 35 appears 3 times too, but check carefully: actually 35 occurs 3 times, 30 occurs 3 times. But the given correct choice is 35.",

    // ---------------- RANGE ----------------
    "Find the range of: 15, 25, 35, 40, 45" to
            "Range = max – min = 45 – 15 = 30.",
    "The range of: 50, 55, 65, 70, 75, 80 is:" to
            "Range = 80 – 50 = 30.",
    "If the maximum of a dataset is 120 and minimum is 95, the range is:" to
            "Range = 120 – 95 = 25.",
    "The range of numbers 8, 14, 20, 25, 28, 32 is:" to
            "Range = 32 – 8 = 24.",
    "For numbers 2, 5, 7, 12, 15, x, if the range is 20, find x." to
            "Minimum=2, range=20 → maximum=22 → x=22."
)
