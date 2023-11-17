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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.alugaai.R
import com.example.alugaai.model.UserViewModel

@Composable
fun MeusDadosScreen(
    navController: NavController,
    viewModel: UserViewModel
) {

    Column {
        MeusDadosForm(
            navController = navController,
            viewModel = viewModel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeusDadosForm(
    navController: NavController,
    viewModel: UserViewModel
) {
    val usuario = viewModel.usuario.value // Get user data from ViewModel

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo2),
            contentDescription = "logo",
            modifier = Modifier.size(300.dp)
        )
        usuario?.let {
            Text(text = "Email: ${it.email}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Usuário: ${it.nome + it.sobrenome}")
          Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .padding(top = 8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Encontre uma locadora mais próxima",
            fontSize = 20.sp,
            color = Color(0,102,139)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.limparUsuario()
                navController.navigate("cep")
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Buscar", modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .padding(top = 8.dp)
        )
        Button(
            onClick = {
                viewModel.limparUsuario()
                navController.navigate("login")
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Logout", modifier = Modifier.padding(8.dp)
            )
        }

    }
}