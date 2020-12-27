package com.example.appkotlin.Injection

import android.content.Context
import androidx.room.Room
import com.example.appkotlin.data.local.AppDatabase
import com.example.appkotlin.data.local.DatabaseDao
import com.example.appkotlin.data.repository.UserRepository
import com.example.appkotlin.domain.usecase.CreateUserUseCase
import com.example.appkotlin.domain.usecase.GetUserUseCase
import com.example.appkotlin.presentation.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.security.AccessControlContext

// fichier qui crée toutes nos classes

val presentationModule = module {
    factory { MainViewModel(get(), get()) } //factory, quand on change de vue il se détruit puis se recrée

}

val domainModule = module {
    factory { CreateUserUseCase(get()) }
    factory { GetUserUseCase(get()) }
}

val dataModule = module {
    single { UserRepository(get()) }
    single {createDataBase(androidContext())}

}

fun createDataBase(context: Context): DatabaseDao {
    val appDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()
    return  appDatabase.databaseDao()

}
