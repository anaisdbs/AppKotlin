package com.example.appkotlin.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.appkotlin.R
import com.example.appkotlin.presentation.recyclerview.RecyclerViewActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.loginLiveData.observe(this, Observer {
            when(it){
                is LoginSuccess -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Account known")
                        .setMessage("You will access to the app")
                        .setPositiveButton("OK"){
                                dialog, which ->  val intent3 = Intent(this, RecyclerViewActivity::class.java)
                            startActivity(intent3)
                        }
                        .show()

                }
                LoginError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Error")
                        .setMessage("Account unknown")
                        .setPositiveButton("OK"){
                            dialog, which ->  dialog.dismiss()
                        }
                        .show()
                }//afficher popup d'erreur
            }


        })
        login_button.setOnClickListener{
            mainViewModel.onClickedLogin(login_edit.text.toString().trim(), password_edit.text.toString())
        }

        create_account_button.setOnClickListener{
            val intent = Intent(this, CreateAccount::class.java)
            startActivity(intent)
        }


    }
}