package com.aachudar.chudarapp.data

val numTheorySolutions = mapOf(
    // Test of divisibility
    "Which of the following numbers is divisible by 15?" to
            "A number divisible by 15 must be divisible by both 3 and 5. 285 ÷ 3 = 95 and 285 ÷ 5 = 57, so answer = 285.",
    "If the number 97215_6 is divisible by 11, find the smallest missing number" to
            "Rule of 11: (sum of digits in odd places) – (sum of digits in even places) must be multiple of 11. Here 9+2+5+6 – (7+1) = 21–8=13. Replace '_' with 3 → 13+3=16, divisible by 11. So missing digit = 3.",

    // Order of operations
    "Find the value of 6 + 11 × 2² – 8 ÷ 2?" to
            "Follow BODMAS: 2²=4 → 11×4=44 → 8÷2=4. Expression = 6+44–4=46.",
    "(7 + 9) × 2 – 4² ÷ 2 = ?" to
            "Brackets first: (7+9)=16. Then 16×2=32. 4²=16 → 16÷2=8. Expression = 32–8=24.",

    // Integers – word problems
    "Latha is three times older than Rani. After 10 years the sum of their ages will be 80. What is Rani's age?" to
            "Let Rani = x, Latha = 3x. After 10 years: (x+10)+(3x+10)=80 → 4x+20=80 → 4x=60 → x=15.",
    "A submarine is at –300 m. It rises 120 m, goes down 20 m, and rises 40m. Where is it now?" to
            "-300+120=–180. Then –180–20=–200. Then –200+40=–160.",

    // Fractions – Conversion
    "Convert 10/3 into a mixed fraction." to
            "10 ÷ 3 = 3 remainder 1 → 3 1/3.",
    "Convert 4 2/5 to an improper fraction." to
            "Multiply whole part by denominator: 4×5=20. Add numerator 2 → 22. Denominator stays 5 → 22/5.",

    // Fractions – Basic operations
    "Simplify: 2/3 x 1/6 x 4/5" to
            "Multiply numerators: 2×1×4=8. Denominators: 3×6×5=90. Fraction = 8/90=4/45.",
    "Simplify: 5/8 - 1/4" to
            "Convert 1/4 to 2/8. Then 5/8–2/8=3/8.",

    // Fractions – Word problems
    "Anna takes 3/4 of a pizza, and Bob takes 1/4 of the remaining. How much pizza is left?" to
            "Left after Anna: 1–3/4=1/4. Bob takes 1/4 of 1/4=1/16. Remaining = 1/4–1/16=3/16.",
    "A 116 2/3 m long rope is cut into ten equal pieces. The length of each piece of the rope is" to
            "116 2/3 = 350/3. Divide by 10 → 35/3 = 11 2/3 m.",
/*
    // Decimals – Conversion
    "Convert 0.75 to a fraction." to
            "0.75=75/100=3/4.",
    "Convert 7/20 to a decimal." to
            "7÷20=0.35.",
*/
    // Decimals – Basic operations
    "The value of (-0.01) cubed is" to
            "(-0.01)³ = –0.01×0.01×0.01= –0.000001.",
    "Find: 7.2 – 4.5" to
            "7.2–4.5=2.7.",

    // Rational numbers – Basic operations
    "Simplify: (2/3) × (9/4)" to
            "(2×9)/(3×4)=18/12=3/2.",
    "Simplify: (11/6) ÷ (11/3)" to
            "(11/6) × (3/11) = 3/6=1/2.",

    // Rational numbers – Comparison
    "Arrange in ascending order: 3/7, 4/9, 1/3" to
            "Write all fractions with a common denominator of 63 (lcm of three numbers): 3/7=27/63, 4/9=28/63, 1/3≈21/63. 21 < 27 < 28, so the order is 1/3 < 3/7 < 4/9.",
    "If one-fourth of one third of one sixth of a number is 15, then the number is " to
            "(1/4×1/3×1/6)×N=15 → 1/72×N=15 → N=15×72=1080.",

    // LCM and GCD
    "Find the LCM of 40 and 56." to
            "Prime factors: 40=2³×5, 56=2³×7. LCM=2³×5×7=280.",
    "If the HCF of two numbers is 12 and their LCM is 180, find the product of the two numbers." to
            "Product of two numbers = HCF×LCM = 12×180=2160."
)

