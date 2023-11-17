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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.alugaai.model.Contato


@Composable
fun CadastroUsuarioScreen(navController: NavController) {

    var emailState = remember {
        mutableStateOf("")
    }

    var nomeState = remember {
        mutableStateOf("")
    }

    var sobrenomeState = remember {
        mutableStateOf("")
    }

    var passwordState = remember { //telefoneState
        mutableStateOf("")
    }

    Column {
        ContatoForm(
            email = emailState.value,
            nome = nomeState.value,
            sobrenome = sobrenomeState.value,
            password = passwordState.value,
            onEmailChange = {
                emailState.value = it
            },
            onNomeChange = {
                nomeState.value = it
            },
            onSobrenomeChange = {
                sobrenomeState.value = it
            },
            onPasswordChange = {
                passwordState.value = it
            },
            navController = navController,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContatoForm(
    email: String,
    nome: String,
    sobrenome: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onNomeChange: (String) -> Unit,
    onSobrenomeChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    navController: NavController,
) {

    val context = LocalContext.current
    val contatoRepository = ContatoRepository(context)

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
            modifier = Modifier.size(200.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { onEmailChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "E-mail")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, capitalization = KeyboardCapitalization.Words
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = nome,
            onValueChange = { onNomeChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Primeiro Nome")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, capitalization = KeyboardCapitalization.Words
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = sobrenome,
            onValueChange = { onSobrenomeChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Sobrenome")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, capitalization = KeyboardCapitalization.Words
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { onPasswordChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Senha")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .padding(top = 8.dp)
        )
        Button(
            onClick = {
                val contato = Contato(
                    email = email, nome = nome, sobrenome = sobrenome, password = password
                )
                contatoRepository.salvar(contato = contato)
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Cadastrar", modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .padding(top = 8.dp)
        )
        Button(
            onClick = { navController.navigate("login") }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Login", modifier = Modifier.padding(8.dp)
            )
        }

    }
}