package me.dio.vinicius.businesscard

import android.app.Application
import me.dio.vinicius.businesscard.data.AppDatabase
import me.dio.vinicius.businesscard.data.BusinessCardRepository

class App : Application() {
    val database by lazy {AppDatabase.getDatabase(this)}
    val repository by lazy { BusinessCardRepository(database.businessDao())}
}