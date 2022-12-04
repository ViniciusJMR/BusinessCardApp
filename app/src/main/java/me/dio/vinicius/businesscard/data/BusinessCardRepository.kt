package me.dio.vinicius.businesscard.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.dio.vinicius.businesscard.domain.BusinessCard


class BusinessCardRepository(private val dao: BusinessCardDao) {

    fun insert(businessCard: BusinessCard) = runBlocking {
        launch(Dispatchers.IO) {
            dao.insert(businessCard)
        }
    }

    fun getAll() = dao.getAll()

    fun delete(businessCard: BusinessCard) = runBlocking {
        launch (Dispatchers.IO){
            dao.delete(businessCard)
        }
    }

    fun update(businessCard: BusinessCard) = runBlocking {
        launch (Dispatchers.IO){
            dao.update(businessCard)
        }
    }
}