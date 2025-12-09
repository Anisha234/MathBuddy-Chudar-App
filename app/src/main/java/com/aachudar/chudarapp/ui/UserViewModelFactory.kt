package com.aachudar.chudarapp.ui

import UserRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aachudar.chudarapp.data.ProblemDao

class UserViewModelFactory(
    private val repository: UserRepository,
    private val problemDao: ProblemDao,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository, problemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
