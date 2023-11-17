package com.example.alugaai.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private val _usuario = mutableStateOf<Contato?>(null)
    val usuario: State<Contato?> = _usuario

    fun setUsuario(user: Contato) {
        _usuario.value = user
    }

    fun limparUsuario() {
        _usuario.value = null
    }
}
