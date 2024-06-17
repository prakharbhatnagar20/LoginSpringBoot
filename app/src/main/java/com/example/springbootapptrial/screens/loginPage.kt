package com.example.springbootapptrial.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.springbootapptrial.data.LoginViewModel
import com.example.springbootapptrial.ui.theme.SpringBootAppTrialTheme

@Composable
fun loginPage(viewModel: LoginViewModel= viewModel()){
    val uiState by viewModel.uiState
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Login Page")

        OutlinedTextField(value = uiState.username, onValueChange = {viewModel.onUsernameChanged(it)})
        OutlinedTextField(value = uiState.password, onValueChange = {viewModel.onPasswordChanged(it)})

        Button(onClick = { viewModel.login() }) {
            Text(text = "Login")
            
        }

    }


}

@Preview
@Composable
fun loginPagePreview(){
    SpringBootAppTrialTheme {
        loginPage()
    }

}