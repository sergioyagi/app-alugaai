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
fun RedefinirSenhaScreen(navController: NavController, viewModel: UserViewModel) {

    var emailState = remember {
        mutableStateOf("")
    }

    var passwordState = remember {
        mutableStateOf("")
    }

    var passwordConfirmationState = remember {
        mutableStateOf("")
    }

    Column {
        // Assuming you have a LoginForm composable
        RedefinirSenhaForm(
            email = emailState.value,
            password = passwordState.value,
            passwordConfirmation = passwordConfirmationState.value,
            onEmailChange = { emailState.value = it },
            onPasswordChange = { passwordState.value = it },
            onPasswordConfirmationChange = { passwordConfirmationState.value = it },
            navController = navController,
            viewModel = viewModel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RedefinirSenhaForm(
    email: String,
    password: String,
    passwordConfirmation: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmationChange: (String) -> Unit,
    navController: NavController,
    viewModel: UserViewModel
) {

    val context = LocalContext.current
    val contatoRepository = ContatoRepository(context)
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = androidx.compose.ui.Modifier.size(200.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = {
                onEmailChange(it)
                showError = false
            },
            modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
            label = {
                Text(text = "E-mail")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words
            )
        )
        Spacer(modifier = androidx.compose.ui.Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                onPasswordChange(it)
                showError = false
            },
            modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
            label = {
                Text(text = "Senha")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )
        Spacer(modifier = androidx.compose.ui.Modifier.height(8.dp))
        OutlinedTextField(
            value = passwordConfirmation,
            onValueChange = {
                onPasswordConfirmationChange(it)
                showError = false
            },
            modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
            label = {
                Text(text = "Confirmar senha")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )
        Spacer(
            modifier = androidx.compose.ui.Modifier
                .height(16.dp)
                .padding(top = 8.dp)
        )
        if (showError) {
            Text(
                text = "O e-mail não foi encontrado ou as senhas não são iguais. Por favor, tente novamente",
                modifier = androidx.compose.ui.Modifier.padding(8.dp)
            )
        }
        Spacer(
            modifier = androidx.compose.ui.Modifier
                .height(16.dp)
                .padding(top = 8.dp)
        )
        Button(
            onClick = {
                val usuario = contatoRepository.buscarContatoPeloEmail(email)
                if (usuario != null && password == passwordConfirmation) {
                    if (contatoRepository.atualizarSenha(usuario.email, password) > 0) {
                        viewModel.limparUsuario()
                        navController.navigate("login")
                    } else {
                        showError = true
                    }
                } else {
                    showError = true
                }
            },
            modifier = androidx.compose.ui.Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Salvar",
                modifier = androidx.compose.ui.Modifier.padding(8.dp)
            )
        }
        Spacer(
            modifier = androidx.compose.ui.Modifier
                .height(16.dp)
                .padding(top = 8.dp)
        )
        Button(
            onClick = {
                navController.navigate("login")
            },
            modifier = androidx.compose.ui.Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Login",
                modifier = androidx.compose.ui.Modifier.padding(8.dp)
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