package com.example.appkotlin.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appkotlin.domain.usecase.CreateUserUseCase
import com.example.appkotlin.domain.usecase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val getUserUseCase: GetUserUseCase
) : ViewModel(){

    val loginLiveData : MutableLiveData<LoginStatus> = MutableLiveData()


    fun onClickedLogin(emailUser: String, passwordUser: String) {
        viewModelScope.launch(Dispatchers.IO) {
            //coroutine fait du multitache, opération lourde thread background
            val user = getUserUseCase.invoke(emailUser, passwordUser)
            val loginStatus = if(user != null){
                LoginSuccess(user.email, user.password)
            }else{
                LoginError
            }
            withContext(Dispatchers.Main){
                //on repasse sur le bon thread pour maj interface utilisateur
                loginLiveData.value = loginStatus
            }
        }
    }

}