package com.aachudar.chudarapp.data

val algebraSolutions = mapOf(
    // Systems of Equations
    "If 3x + 2y = 18 and 2x + y = 11, find x and y." to
            "From 2x + y = 11 → y = 11 – 2x. Sub into 3x + 2y = 18: 3x + 2(11–2x) = 18 → 3x + 22 – 4x = 18 → –x + 22 = 18 → x = 4, y = 3.",
    "If x + y = 12 and x - y = 4, what is x² - y²?" to
            "Note: x²–y² = (x+y)(x–y). Here (12)(4) = 48.",
    "The sum of two numbers is 40. Their difference is 8. What are the numbers?" to
            "(a+b)=40, (a–b)=8 → Add: 2a=48 → a=24, b=16.",
    "If 5x + 2y = 20 and 3x + 4y = 18, what is the value of x and y?" to
            "From 5x+2y=20 → multiply by 2: 10x+4y=40. Subtract (3x+4y=18): 7x=22 → x=2, then 2(2)+y=11 → y=3.",
    "Solve for x and y: 7x + 5y = 65, 3x - 2y = 3." to
            "Multiply 3x–2y=3 by 5: 15x–10y=15. Multiply 7x+5y=65 by 2: 14x+10y=130. Add: 29x=145 → x=5. Sub into 3(5)–2y=3 → 15–2y=3 → y=6.",

    // Word Problems
    "A father is three times as old as his son. In 10 years, the sum of their ages will be 80. What are their ages now?" to
            "Let son=x, father=3x. After 10 years: (x+10)+(3x+10)=80 → 4x+20=80 → x=15, father=45.",
    "Two taps can fill a tank in 15 and 20 minutes. A waste pipe empties it in 30 minutes. If all three are opened together, how long will it take to fill the tank?" to
            "Rates: 1/15+1/20–1/30 = 4/60+3/60–2/60=5/60=1/12. Time=12 min.",
    "A boat travels 48 km downstream in 3 hours and the same distance upstream in 6 hours. Find the speed of the stream if the boat's still-water speed is 12 km/hr." to
            "Downstream speed=48/3=16, upstream=48/6=8. Boat=12. Stream=(16–8)/2=4 km/hr.",
    "A general has 335250 soldiers. He arranges them in a square formation and finds 9 left over. How many are there in each row?" to
            "Total – 9 = 335241. √335241 ≈ 579. So 579 soldiers per row.",
    "The LCM of two numbers is 252 and their HCF is 21. If one number is 84, find the other." to
            "Formula: a×b=LCM×HCF=252×21=5292. If one=84 → other=5292/84=63.",

    // Factorization
    "Factorize completely: x³ + 3x²y + 3xy² + y³." to
            "This is the identity (x+y)³. So answer = (x+y)³.",
    "Factorize: 9x²y² + 6xyz + z²." to
            "Expression is a perfect square trinomial: (3xy+z)².",

    // Squares & Cubes
    "Find the smallest number by which 180 must be multiplied to make it a perfect square." to
            "180=2²×3²×5. Extra factor needed: 5. Multiply by 5 → 900 (perfect square).",
    "What is the units digit of 637²?" to
            "Units digit depends only on 7²=49. So units digit=9.",
    "Which is the smallest square number divisible by 8, 9, and 10?" to
            "LCM(8,9,10)=360. Smallest square multiple=3600 (since 360=6×60, need to multiply by 10 to make a square).",
    "Find the cube root of 1331." to
            "11³=1331 → cube root=11.",

    // Algebraic Identities
    "(x + 2y)² - (x - 2y)² equals?" to
            "This is (A²–B²)=(A–B)(A+B). Here A=x+2y, B=x–2y → (4y)(2x)=8xy.",

    // Equations
    "Solve for x: x² - 7x + 12 = 0." to
            "Factorize: (x–3)(x–4)=0 → x=3 or 4.",

    // Exponents
    "If 2ⁿ - 2ⁿ⁻² = 3, then n = ?" to
            "Factor: 2ⁿ⁻²(2²–1)=2ⁿ⁻²×3=3 → 2ⁿ⁻²=1 → n–2=0 → n=2.",
    "Simplify: (5² + 12²)^(1/2)." to
            "Inside: 25+144=169. √169=13."
)
