package com.example.alugaai.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alugaai.R
import com.example.alugaai.database.repository.ContatoRepository
import com.example.alugaai.model.UserViewModel

@Composable
fun LoginIn(navController: NavController, viewModel: UserViewModel) {

    var emailState = remember {
        mutableStateOf("")
    }

    var passworState = remember {
        mutableStateOf("")
    }

    Column {
        LoginForm(
            email = emailState.value,
            password = passworState.value,
            onEmailChange = { emailState.value = it },
            onPasswordChange = { passworState.value = it },
            navController = navController,
            viewModel = viewModel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    navController: NavController,
    viewModel: UserViewModel
) {

    val context = LocalContext.current
    val contatoRepository = ContatoRepository(context)
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier.size(300.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = {
                onEmailChange(it)
                showError = false
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "E-mail")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                onPasswordChange(it)
                showError = false
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Senha")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )
        if (showError) {
            Text(
                text = "Email e/ou senha incorretos. Por favor, tente novamente",
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .padding(top = 8.dp)
        )
        Button(
            onClick = {
                val usuario = contatoRepository.buscarContatoPeloEmail(email)
                if (usuario != null && usuario.password == password) {
                    viewModel.setUsuario(usuario)
                    navController.navigate("meus_dados")
                } else {
                    showError = true
                }
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Entrar", modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .padding(top = 8.dp)
        )
        Button(
            onClick = { navController.navigate("redefinir") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Esqueci minha senha", modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .padding(top = 8.dp)
        )
        Button(
            onClick = { navController.navigate("cadastro") },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "Cadastrar", modifier = Modifier.padding(8.dp)
            )
        }
    }
}