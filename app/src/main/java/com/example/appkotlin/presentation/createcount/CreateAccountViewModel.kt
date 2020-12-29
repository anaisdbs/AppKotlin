package com.example.appkotlin.presentation.createcount

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appkotlin.domain.entity.User
import com.example.appkotlin.domain.usecase.CreateUserUseCase
import com.example.appkotlin.domain.usecase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateAccountViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel(){
    val createLiveData : MutableLiveData<CreateStatus> = MutableLiveData()


    fun onClickedCreate(emailNewUser: String, passwordNewUser: String) {
        viewModelScope.launch(Dispatchers.IO) {
                val newUser = createUserUseCase.invoke(User(emailNewUser, passwordNewUser)) //création du nouvel utilisateur
                val createStatus = if(newUser != null){
                    CreateSuccess(emailNewUser, passwordNewUser)
                }else{
                    CreateError
                }
                withContext(Dispatchers.Main){
                    //on repasse sur le bon thread pour mettre à jour l'interface utilisateur
                    createLiveData.value = createStatus
                }
        }
    }
}
