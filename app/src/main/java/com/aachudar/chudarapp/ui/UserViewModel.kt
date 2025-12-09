package com.aachudar.chudarapp.ui

import UserRepository
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aachudar.chudarapp.data.Problem
import com.aachudar.data.User
import com.aachudar.data.UserDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Request
import org.json.JSONObject
import com.aachudar.chudarapp.BuildConfig
import com.aachudar.chudarapp.data.ProblemDao
import com.aachudar.chudarapp.data.WolframApi
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class UserViewModel(
    private val repository: UserRepository,
    private val problemDao: ProblemDao
) : ViewModel() {

    private val wolframAppId = "GLJYPY-PYKRRG6GEX"

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    var currentProblem: MutableState<Problem?> = mutableStateOf(null)
        private set
    var isLoading: MutableState<Boolean> = mutableStateOf(false)
        private set

    private val wolframApi: WolframApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.wolframalpha.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(WolframApi::class.java)
    }

    fun login(username: String) {
        viewModelScope.launch {
            var user = repository.getUser(username)
            if (user == null) {
                user = User(username, 0)
                repository.insertUser(user)
            }
            _currentUser.value = user
        }
    }
    private val _allUserProblems = MutableStateFlow<List<Problem>>(emptyList())



    fun getNextUnseenProblem(topic: String, user: User) {
        viewModelScope.launch {
            val allScopedProblems = problemDao.getProblemsForUser(user.username)

            val unseen = allScopedProblems.filter { problem ->
                problem.topic == topic && !user.completedProblemIds.contains(problem.id)
            }

            currentProblem.value = if (unseen.isNotEmpty()) unseen.random() else null
        }
    }


    private suspend fun callOpenAiForTopicQuestions(topic: String): List<Problem> = withContext(Dispatchers.IO) {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        val jsonMediaType = "application/json".toMediaType()

        val prompt = when (topic) {
            "Number Theory" ->  """
You are a Number Theory question generator for middle to high school students.

✅ Your task is to generate one **unique, moderately challenging multiple-choice math question** using **clear mathematical notation** that can be directly understood by a WOLFRAM SHORT ANSWER MODEL.

✅ The question must involve ONE of the following Number Theory subtopics (pick randomly each time):
- Test of divisibility (e.g., "Is 246 divisible by 6?")
- Integers – simple word problems (use small positive/negative numbers, avoid vague wording)
- Fractions – conversion of mixed ↔ improper fractions
- Fractions – basic operations (addition, subtraction, multiplication, division)
- Fractions – word problems (keep short, solvable without calculator)
- Decimals – conversion between decimals and fractions
- Decimals – basic operations (add, subtract, multiply, divide)
- Rational numbers – basic operations
- Rational numbers – comparison in ascending/descending order
- Greatest common divisor (GCD), e.g. gcd(24, 36)
- Least common multiple (LCM), e.g. lcm(12, 18)
- Modular arithmetic (e.g., 25 mod 6)
- Prime factorization (e.g., "Prime factorization of 84?")

✅ Rules:
- Use only **small integers under 100**
- Use **clear math notation** (no LaTeX, no formatting symbols)
- Avoid vague or incomplete wording
- Keep questions solvable without a calculator
- Multiple-choice with exactly 4 distinct answer options

❌ Do NOT generate proofs, graphs, multi-step story problems, or irrational/cube roots.

✅ Return a valid JSON array ONLY, in this exact format:
[
  {
    "questionText": "What is 37 mod 5?",
    "choiceA": "1",
    "choiceB": "2",
    "choiceC": "3",
    "choiceD": "4",
    "correctAnswer": "B"
  }
]
""".trimIndent()

            "Algebra" -> """
            You are an Algebra question generator for middle to high school students.

            ✅ Your task is to generate one **unique, moderately challenging multiple-choice question** using **clear mathematical notation** that can be directly understood by a WOLFRAM SHORT ANSWER MODEL

            ✅ The question must involve one of the following topics (choose randomly):
            - Exponents (squares, cubes, laws of exponents)
            - Polynomial basics (terms, degree, types)
          
            - Substitution in expressions
            - Framing algebraic statements
            - Basic algebraic operations
            - Word problems using equations
            - Algebraic identities (e.g., a² - b², (a + b)²)
            - Difference of squares

            ✅ Rules:
            - Use **small integers** for coefficients and constants
            - Keep questions **clear and direct**
            - Only use expressions Wolfram can evaluate (no LaTeX, no irrational/cube roots)

            ❌ Do NOT include advanced calculus, inequalities, or irrational roots

            ✅ Return a valid JSON array like this:
            [
              {
                "questionText": "Factorize: x² - 25",
                "choiceA": "(x - 5)(x + 5)",
                "choiceB": "(x - 25)(x + 1)",
                "choiceC": "(x - 2)(x + 12.5)",
                "choiceD": "(x - 10)(x + 2.5)",
                "correctAnswer": "A"
              }
            ]
        """
                .trimIndent()

            "Geometry" -> """
            You are a Geometry question generator for middle to high school students.
✅ Your task is to generate one **unique, moderately challenging multiple-choice question** using **clear mathematical notation** that can be directly understood by a WOLFRAM SHORT ANSWER MODEL

            ✅ The question must involve one of the following topics (choose randomly):
            - Types of angles
            - Properties of triangles
            - Perimeter, area, volume
            - Circle properties
            - Pythagorean Theorem

            ✅ Use **simple integers and measurements**

            ❌ Avoid 3D geometry or proofs

            ✅ Return a valid JSON array like this:
            [
              {
                "questionText": "What is the measure of a right angle?",
                "choiceA": "45°",
                "choiceB": "60°",
                "choiceC": "90°",
                "choiceD": "120°",
                "correctAnswer": "C"
              }
            ]
        """.trimIndent()

            "Statistics" -> """
           ✅ Your task is to generate one **unique, moderately challenging multiple-choice question** using **clear mathematical notation** that can be directly understood by a WOLFRAM SHORT ANSWER MODEL

           ✅ The question must involve one of the following topics (choose randomly):

           - Mean
           - Median
           - Mode
           - Range

           ✅ Always use clear number sets like {4, 6, 8, 10, 12} or small data lists.

           ❌ Do NOT use graphs, inference, probability, or standard deviation.

           ✅ Return a valid JSON array like this:
           [
             {
               "questionText": "What is the median of {3, 7, 9, 11, 15}?",
               "choiceA": "7",
               "choiceB": "9",
               "choiceC": "11",
               "choiceD": "8",
               "correctAnswer": "B"
             }
           ]
       """.trimIndent()

            else -> throw IllegalArgumentException("Unsupported topic: $topic")
        }

        val requestBodyMap = mapOf(
            "model" to "gpt-4o",
            "messages" to listOf(
                mapOf("role" to "system", "content" to "You are an API that outputs only valid JSON arrays. No explanations, no formatting."),
                mapOf("role" to "user", "content" to prompt)
            ),
            "temperature" to 1.0
        )

        val gson = Gson()
        val requestBodyJson = gson.toJson(requestBodyMap)
        val requestBody = requestBodyJson.toRequestBody(jsonMediaType)

        val request = Request.Builder()
            .url("https://api.openai.com/v1/chat/completions")
            .post(requestBody)
            .addHeader("Authorization", "Bearer ${BuildConfig.API_KEY}")
            .addHeader("Content-Type", "application/json")
            .addHeader("OpenAI-Organization", "org-jByf7wexB8KgyphDfvPqLD3g")
            .build()

        val response = client.newCall(request).execute()
        val responseBody = response.body?.string() ?: throw Exception("Empty response from OpenAI")

        Log.d("OpenAI", "Raw response body: $responseBody")

        val responseJson = JSONObject(responseBody)
        if (responseJson.has("error")) {
            val errorMessage = responseJson.getJSONObject("error").getString("message")
            throw Exception("OpenAI API error: $errorMessage")
        }

        val choicesArray = responseJson.getJSONArray("choices")
        val messageContent = choicesArray.getJSONObject(0).getJSONObject("message").getString("content").trim()

        val cleanedContent = messageContent
            .replace("```json", "", ignoreCase = true)
            .replace("```", "", ignoreCase = true)
            .trim()

        Log.d("OpenAI", "Cleaned GPT Content: $cleanedContent")

        if (!cleanedContent.startsWith("[")) {
            throw Exception("Invalid GPT response: Expected JSON array but got something else.")
        }

        return@withContext gson.fromJson(cleanedContent, Array<Problem>::class.java).toList()
    }

    // Tracks only the most recent generated problems
    private val _lastGeneratedProblems = MutableStateFlow<List<Problem>>(emptyList())
    val lastGeneratedProblems: StateFlow<List<Problem>> = _lastGeneratedProblems

    fun generateNewQuestionsFromOpenAI(topic: String) {
        viewModelScope.launch {
            isLoading.value = true

            try {
                val existingQuestions = problemDao.getAllProblems()
                    .map { it.questionText.trim().lowercase() }
                    .toSet()

                val validatedProblems = mutableListOf<Problem>()
                var attempts = 0

                while (validatedProblems.size < 3 && attempts < 10) {
                    attempts++

                    val newProblem = callOpenAiForTopicQuestions(topic).firstOrNull() ?: continue
                    val normalizedNew = newProblem.questionText.trim().lowercase()

                    val isDuplicate = normalizedNew in existingQuestions ||
                            validatedProblems.any { it.questionText.trim().lowercase() == normalizedNew }

                    if (isDuplicate) {
                        Log.d("GENERATOR", "Duplicate question detected (DB or session), skipping.")
                        continue
                    }

                    val validated = validateAnswerWithWolframWithRetry(topic, newProblem)

                    if (validated != null) {
                        validated.topic = topic
                        validatedProblems.add(validated)
                        Log.d("GENERATOR", "Accepted problem #${validatedProblems.size}")
                    } else {
                        Log.d("GENERATOR", "Validation failed on attempt $attempts")
                    }
                }

                if (validatedProblems.isNotEmpty()) {
                    saveGeneratedProblemsToDatabase(validatedProblems, topic)

                    // ✅ Expose this batch to UI
                    _lastGeneratedProblems.value = validatedProblems

                    currentUser.value?.let { user ->
                        getNextUnseenProblem(topic, user)
                    }
                } else {
                    Log.e("GENERATOR", "No valid problems generated after $attempts attempts")
                }

            } catch (e: Exception) {
                Log.e("OpenAI", "Error: ${e.localizedMessage}", e)
            } finally {
                isLoading.value = false
            }
        }
    }



    suspend fun isDuplicateQuestion(questionText: String): Boolean {
        val existingProblems = problemDao.getAllProblems()
        return existingProblems.any { it.questionText.trim().lowercase() == questionText.trim().lowercase() }
    }

    suspend fun saveGeneratedProblemsToDatabase(problems: List<Problem>, topic: String) {
        val userId = currentUser.value?.username ?: return

        problems.forEach {
            it.topic = topic
            it.subtopic = "Generated"   // ✅ add this line
            it.userId = userId
            it.source = "ai"
        }

        problemDao.insertAll(problems)
        Log.d("OpenAI", "Saved ${problems.size} AI problems to database for user $userId")
    }


    private suspend fun validateAnswerWithWolframWithRetry(topic:String, initialProblem: Problem): Problem? {
        var problem = initialProblem

        repeat(3) { attempt ->
            val validated = validateAnswerWithWolfram(problem)
            if (validated != null) {
                Log.d("WOLFRAM_RETRY", "Validated on attempt ${attempt + 1}")
                return validated
            }
            val newProblem = callOpenAiForTopicQuestions(topic).firstOrNull() ?: return null
            problem = newProblem
            Log.d("WOLFRAM_RETRY", "Retrying with new problem")
        }

        return null
    }

    private suspend fun validateAnswerWithWolfram(problem: Problem): Problem? {
        return withContext(Dispatchers.IO) {
            try {
                val response = wolframApi.getShortAnswer(problem.questionText, wolframAppId)
                if (!response.isSuccessful) return@withContext null

                val wolframAnswer = response.body()?.string()?.trim()
                Log.d("WOLFRAM_RAW", "Wolfram raw answer: $wolframAnswer")

                val correctedAnswerLetter = mapAnswerToChoiceLetter(problem, wolframAnswer)

                if (correctedAnswerLetter != null) {
                    problem.correctAnswer = correctedAnswerLetter
                }

                problem
            } catch (e: Exception) {
                null
            }
        }
    }

    fun submitAnswer(problem: Problem, selectedChoice: String, user: User, userDao: UserDao) {
        viewModelScope.launch {
            val isCorrect = selectedChoice == problem.correctAnswer
            val updatedCompleted = user.completedProblemIds.toMutableList().apply { add(problem.id) }
            val newScore = if (isCorrect) user.score + 1 else user.score
            val updatedUser = user.copy(score = newScore, completedProblemIds = updatedCompleted)
            userDao.updateUser(updatedUser)
            _currentUser.value = updatedUser
        }
    }
    private fun normalizeMathAnswer(input: String?): String {
        if (input == null) return ""

        return input.lowercase()
            .replace("x =", "")
            .replace("x equals", "")
            .replace("equals", "")
            .replace(" ", "")
            .replace("−", "-")  // Handle minus symbol variations
            .trim()
    }

    private fun mapAnswerToChoiceLetter(problem: Problem, wolframAnswer: String?): String? {
        if (wolframAnswer == null) return null

        val normalizedWolfram = normalizeMathAnswer(wolframAnswer)

        val choices = mapOf(
            "A" to normalizeMathAnswer(problem.choiceA),
            "B" to normalizeMathAnswer(problem.choiceB),
            "C" to normalizeMathAnswer(problem.choiceC),
            "D" to normalizeMathAnswer(problem.choiceD)
        )

        return choices.entries.firstOrNull { (_, value) ->
            value == normalizedWolfram
        }?.key
    }


    private fun cleanChoiceText(text: String): String {
        return text.replace(Regex("^[A-D][).:\\s]*"), "").trim().lowercase()
    }

    fun updateCompletedTests(username: String, newCompletedTestsCsv: String) {
        viewModelScope.launch {
            repository.updateCompletedTests(username, newCompletedTestsCsv)

            // Also update local StateFlow if needed
            val updatedUser = repository.getUser(username)
            _currentUser.value = updatedUser
        }
    }

    private val _problems = MutableStateFlow<List<Problem>>(emptyList())
    val problems: StateFlow<List<Problem>> = _problems

    fun loadProblemsBySubtopic(topic: String, subtopic: String) {
        viewModelScope.launch {
            val result = repository.getProblemsBySubtopic(topic, subtopic)
            _problems.value = result
        }
    }
    fun incrementTestsPassed() {
        viewModelScope.launch {
            val user = _currentUser.value
            if (user != null) {
                val updatedUser = user.copy(numTestsPassed = user.numTestsPassed + 1)
                repository.updateUser(updatedUser)
                _currentUser.value = updatedUser
            }
        }
    }

    fun addPracticeScore(points: Int) {
        viewModelScope.launch {
            _currentUser.value?.let { user ->
                val updatedUser = user.copy(
                    score = user.score + points
                )
                repository.updateUser(updatedUser)
                _currentUser.value = updatedUser
            }
        }
    }



}
