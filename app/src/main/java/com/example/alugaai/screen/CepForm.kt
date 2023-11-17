package com.example.alugaai.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.alugaai.R
import com.example.alugaai.model.Endereco
import com.example.alugaai.model.UserViewModel
import com.example.alugaai.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CepForm(navController: NavHostController, userViewModel: UserViewModel) {

    var cepState by remember { mutableStateOf("") }
    var ufState by remember { mutableStateOf("") }
    var cidadeState by remember { mutableStateOf("") }
    var ruaState by remember { mutableStateOf("") }

    var listaEnderecoState by remember { mutableStateOf(listOf<Endereco>()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )

    {
        Image(
            painter = painterResource(id = R.drawable.logo2),
            contentDescription = "logo",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .padding(top = 8.dp)
        )
        Button(
            onClick = {
                navController.navigate("login")
            }, modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Logout", modifier = Modifier.padding(8.dp)
            )
        }
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Encontre uma unidade",
                    fontSize = 24.sp,
                    color = Color(0,102,139)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Digite o endereço",
                    fontWeight = FontWeight.Bold,
                    color = Color(0, 102, 139)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row() {
                    OutlinedTextField(
                        value = ufState,
                        onValueChange = {
                            ufState = it
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 4.dp),
                        label = {
                            Text(text = "UF?")
                        },
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Characters,
                            keyboardType = KeyboardType.Text
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = cidadeState,
                        onValueChange = {
                            cidadeState = it
                        },
                        modifier = Modifier.weight(2f),
                        label = {
                            Text(text = "Qual é a cidade?")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words
                        )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        value = ruaState,
                        onValueChange = {
                            ruaState = it
                        },
                        modifier = Modifier.weight(2f),
                        label = {
                            Text(text = "Qual é o logradouro?")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words
                        )
                    )
                    IconButton(onClick = {
                        val call = RetrofitFactory().getCepService().getEnderecos(ufState, cidadeState, ruaState)
                        call.enqueue(object : Callback<List<Endereco>>{
                            override fun onResponse(
                                call: Call<List<Endereco>>,
                                response: Response<List<Endereco>>
                            ) {
                                listaEnderecoState = response.body()!!
                            }

                            override fun onFailure(call: Call<List<Endereco>>, t: Throwable) {
                                TODO("Not yet implemented")
                            }

                        })
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn() {
            items(listaEnderecoState) {
                CardEndereco(it)
            }
        }
    }
}

@Composable
fun CardEndereco(endereco: Endereco) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 4.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        ) {
            Text(text = "CEP: ${endereco.cep}")
            Text(text = "Rua: ${endereco.rua}")
            Text(text = "Cidade: ${endereco.cidade}")
            Text(text = "Bairro: ${endereco.bairro}")
            Text(text = "UF: ${endereco.uf}")
        }
    }
}