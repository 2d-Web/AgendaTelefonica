package com.example.agendatelefonica.vistamodelo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.agendatelefonica.datos.UserDatabase
import com.example.agendatelefonica.repos.UserRepo
import com.example.agendatelefonica.modelo.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserModel (application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repo: UserRepo

    init {
        val userDao= UserDatabase.getDatabase(application).userDao()
        repo = UserRepo(userDao)
        readAllData = repo.readAllData
    }

    fun addUser(user: User){

        viewModelScope.launch(Dispatchers.IO){
            repo.addUser(user)
        }
    }

    fun actualizarUser (user: User){
        viewModelScope.launch(Dispatchers.IO){
            repo.actualizarUser(user)

        }
    }

    fun borrarUser(user: User){

        viewModelScope.launch(Dispatchers.IO) {
            repo.borrarUser(user)

        }
    }

    fun borrarTodosUser(){

        viewModelScope.launch(Dispatchers.IO){
            repo.borrarTodosUser()

        }
    }
}