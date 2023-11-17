package com.example.alugaai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alugaai.screen.CadastroUsuarioScreen
import com.example.alugaai.screen.LoginIn
import com.example.alugaai.screen.MeusDadosScreen
import com.example.alugaai.screen.RedefinirSenhaScreen
import com.example.alugaai.model.UserViewModel
import com.example.alugaai.screen.CepForm
import com.example.alugaai.ui.theme.AlugaAiTheme

class MainActivity : ComponentActivity() {

    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AlugaAiTheme {

                // Use remember to prevent recomposition of NavController on configuration changes
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ) {
                        composable(route = "cadastro") {
                            CadastroUsuarioScreen(navController)
                        }
                        composable(route = "login") {
                            LoginIn(navController, userViewModel)
                        }
                        composable(route = "redefinir") {
                            RedefinirSenhaScreen(navController, userViewModel)
                        }
                        composable(route = "meus_dados") {
                            MeusDadosScreen(navController, userViewModel)
                        }
                        composable(route = "cep") {
                            CepForm(navController, userViewModel )
                        }
                    }
                }
            }
        }
    }
}
