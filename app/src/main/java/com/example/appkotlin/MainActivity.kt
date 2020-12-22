package com.example.appkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_button.setOnClickListener{
            mainViewModel.onClickedIncrement()
        }

        /*mainViewModel.text.observe(this, Observer {
            main_text.text = it
            // value -> main_text.text = value --> autre option

        })*/

        mainViewModel.counter.observe(this, Observer {
            value -> main_text.text = value.toString()
        })

    }
}