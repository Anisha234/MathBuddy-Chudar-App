import com.aachudar.chudarapp.data.Problem
import com.aachudar.chudarapp.data.ProblemDao
import com.aachudar.data.User
import com.aachudar.data.UserDao

class UserRepository(private val userDao: UserDao, val problemDao: ProblemDao)

 {
    suspend fun getUser(username: String) = userDao.getUserByUsername(username)
    suspend fun insertUser(user: User) = userDao.insertUser(user)

     suspend fun getProblemsBySubtopic(topic: String, subtopic: String): List<Problem> {
         return problemDao.getProblemsBySubtopic(topic, subtopic)
     }

     suspend fun getProblemsByTopic(topic: String): List<Problem> {
         return problemDao.getProblemsByTopic(topic)
     }
     suspend fun updateCompletedTests(username: String, newCompletedTestsCsv: String) {
         userDao.updateCompletedTests(username, newCompletedTestsCsv)
     }

     suspend fun updateUser(user: User) = userDao.updateUser(user)  // ✅ add this


 }

