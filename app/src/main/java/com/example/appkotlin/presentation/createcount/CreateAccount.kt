package com.example.appkotlin.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.appkotlin.R
import com.example.appkotlin.presentation.createcount.CreateAccountViewModel
import com.example.appkotlin.presentation.createcount.CreateError
import com.example.appkotlin.presentation.createcount.CreateSuccess
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.createaccount.*
import org.koin.android.ext.android.inject

class CreateAccount : AppCompatActivity() {

    val createCountViewModel: CreateAccountViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.createaccount)

        createCountViewModel.createLiveData.observe(this, Observer {
            when(it){
                is CreateSuccess -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Validé")
                        .setMessage("CRÉÉE")
                        .setPositiveButton("OK"){
                                dialog, which ->  dialog.dismiss()
                        }
                        .show()
                    val intent2 = Intent(this, MainActivity::class.java)
                    startActivity(intent2)
                    //TODO Navigate
                }
                CreateError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Erreur")
                        .setMessage("REMPLIR")
                        .setPositiveButton("OK"){
                                dialog, which ->  dialog.dismiss()
                        }
                        .show()
                }//afficher popup d'erreur
            }

        })
        create_button.setOnClickListener{
            createCountViewModel.onClickedCreate(email_create_edit.text.toString().trim(), password_create_edit.text.toString())
        }

    }
}