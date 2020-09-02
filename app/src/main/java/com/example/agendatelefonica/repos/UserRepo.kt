package com.example.agendatelefonica.repos

import androidx.lifecycle.LiveData
import com.example.agendatelefonica.datos.UserDao
import com.example.agendatelefonica.modelo.User

class UserRepo (private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun actualizarUser(user: User) {
        userDao.actualizarUser(user)
    }

    suspend fun borrarUser(user: User){
        userDao.borrarUser(user)
    }

    suspend fun borrarTodosUser(){
        userDao.borrarTodosUsuarios()
    }

}