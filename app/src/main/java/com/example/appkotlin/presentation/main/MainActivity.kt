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
                        .setTitle("Validé")
                        .setMessage("Compte connu")
                        .setPositiveButton("OK"){
                                dialog, which ->  dialog.dismiss()
                        }
                        .show()
                    val intent3 = Intent(this, RecyclerViewActivity::class.java)
                    startActivity(intent3)
                    //TODO Navigate
                }
                LoginError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Erreur")
                        .setMessage("Compte inconnu")
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
            //mainViewModel.onClickedCreate()
        }

        /*mainViewModel.text.observe(this, Observer {
            main_text.text = it
            // value -> main_text.text = value --> autre option
        })*/

       /* mainViewModel.counter.observe(this, Observer {
            value -> main_text.text = value.toString()
        })*/

    }
}