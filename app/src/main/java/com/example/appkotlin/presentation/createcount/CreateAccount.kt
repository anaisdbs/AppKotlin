package com.example.appkotlin.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.example.appkotlin.R
import com.example.appkotlin.presentation.createcount.CreateAccountViewModel
import com.example.appkotlin.presentation.createcount.CreateError
import com.example.appkotlin.presentation.createcount.CreateSuccess
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.createaccount.*
import kotlinx.coroutines.delay
import org.koin.android.ext.android.inject

class CreateAccount : AppCompatActivity() {

    val createAccountViewModel: CreateAccountViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.createaccount)



        create_button.setOnClickListener{

            val emailcreate = email_create_edit.text.toString().trim()
            val passwordcreate = password_create_edit.text.toString()

            when { //verifier que le mail et le mot de passe ne sont pas null pour créer le compte
                emailcreate.isNullOrEmpty() -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Error")
                        .setMessage("You have to fill the email box")
                        .setPositiveButton("OK"){ dialog, which ->  dialog.dismiss()
                        }
                        .show()
                }
                passwordcreate.isNullOrEmpty() -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Error")
                        .setMessage("You have to fill the password box")
                        .setPositiveButton("OK"){ dialog, which ->  dialog.dismiss()
                        }
                        .show()
                }
                else -> {
                    createAccountViewModel.onClickedCreate(emailcreate, passwordcreate)
                }
            }

            createAccountViewModel.createLiveData.observe(this, Observer {
            when(it){
                is CreateSuccess -> {//si succès on affiche popup de validation et navigation vers login
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Account create")
                        .setMessage("You can connect")
                        .setPositiveButton("OK"){
                                dialog, which -> val intent2 = Intent(this, MainActivity::class.java)
                                startActivity(intent2)
                        }
                        .show()
                }
                CreateError -> {//si erreur on affiche pop d'erreur
                    MaterialAlertDialogBuilder(this)
                        .setTitle("There is an error")
                        .setPositiveButton("OK"){
                                dialog, which ->  dialog.dismiss()
                        }
                        .show()
                }
            }

        })
        }

    }
}