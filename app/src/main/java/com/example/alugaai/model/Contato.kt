package com.example.alugaai.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contato")
data class Contato(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val email: String = "",
    val nome: String = "",
    val sobrenome: String = "",
    var password: String = ""
)