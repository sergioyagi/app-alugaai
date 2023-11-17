package com.example.alugaai.database.repository

import android.content.Context
import com.example.alugaai.database.ContatoDao
import com.example.alugaai.database.ContatoDatabase
import com.example.alugaai.model.Contato

class ContatoRepository(context: Context) {

    private val db = ContatoDatabase.getDatabase(context).contatoDao()

    fun salvar(contato: Contato): Long {
        return db.salvar(contato)
    }

    fun atualizarSenha(email: String, password: String): Int {
        val usuario = db.buscarContatoPeloEmail(email)
        usuario.password = password
        return db.atualizar(usuario)
    }

    fun buscarContatoPeloEmail(email: String): Contato {
        return db.buscarContatoPeloEmail(email)
    }

}
