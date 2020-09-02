package com.example.agendatelefonica.datos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.agendatelefonica.modelo.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun actualizarUser(user: User)

    @Delete
    suspend fun borrarUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun borrarTodosUsuarios()

    @Query("SELECT * FROM user_table ORDER BY id ASC")

    fun readAllData (): LiveData<List<User>>

}