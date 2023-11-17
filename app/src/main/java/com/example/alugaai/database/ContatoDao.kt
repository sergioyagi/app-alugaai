package com.example.alugaai.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.alugaai.model.Contato

@Dao
interface ContatoDao {
    @Insert
    fun salvar(contato: Contato): Long

    @Update
    fun atualizar(contato: Contato): Int

    @Query("SELECT * FROM contato WHERE email = :email")
    fun buscarContatoPeloEmail(email: String): Contato
}

