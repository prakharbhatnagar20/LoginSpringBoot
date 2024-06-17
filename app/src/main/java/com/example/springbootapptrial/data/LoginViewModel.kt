package com.example.springbootapptrial.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.springbootapptrial.network.LoginRequest
import com.example.springbootapptrial.network.LoginResponse
import com.example.springbootapptrial.network.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    var uiState = mutableStateOf(LoginUiState())

    fun onUsernameChanged(newUsername:String){
        uiState.value = uiState.value.copy(username = newUsername)
    }

    fun onPasswordChanged(newPassword:String){
        uiState.value = uiState.value.copy(password = newPassword)
    }

    fun login() {
        Log.d("LoginViewModel", "login() called")

        // Update UI state to indicate loading state
        viewModelScope.launch {
            uiState.value = uiState.value.copy(isLoading = true, errorMessage = null)

            // Create login request object
            val loginRequest = LoginRequest(uiState.value.username, uiState.value.password)

            // Make Retrofit API call
            RetrofitInstance.api.login(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    Log.d("LoginViewModel", "onResponse: ${response.code()}")

                    if (response.isSuccessful && response.body()?.success == true) {
                        uiState.value = uiState.value.copy(isLoading = false)
                        Log.d("LoginViewModel", "Login successful")
                    } else {
                        val errorMessage = response.errorBody()?.string() ?: "Login failed"
                        uiState.value = uiState.value.copy(isLoading = false, errorMessage = errorMessage)
                        Log.d("LoginViewModel", "Login failed")
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    uiState.value = uiState.value.copy(isLoading = false, errorMessage = t.message ?: "An error occurred")
                    Log.e("LoginViewModel", "Login error: ${t.message}", t)
                }
            })
        }
    }

}