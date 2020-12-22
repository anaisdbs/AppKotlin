package com.example.appkotlin.Injection

import com.example.appkotlin.MainViewModel
import org.koin.dsl.module


val presentationModule = module {
    factory { MainViewModel() } //factory, quand on change de vue il se détruit puis se recrée

}