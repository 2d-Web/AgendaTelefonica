package com.example.agendatelefonica.modelo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val nombre: String,
        val telefono: String,
        val direccion: String,
        val correo: String
): Parcelable