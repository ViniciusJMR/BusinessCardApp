package me.dio.vinicius.businesscard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.dio.vinicius.businesscard.data.BusinessCardRepository
import me.dio.vinicius.businesscard.domain.BusinessCard

class MainViewModel(private val repository: BusinessCardRepository) : ViewModel(){

    var selectedCard: BusinessCard? =
        BusinessCard(0, "","", "", "", "")

    fun insert(businessCard: BusinessCard) {
        repository.insert(businessCard)
    }

    fun getAll(): LiveData<List<BusinessCard>> = repository.getAll()

    fun delete(businessCard: BusinessCard) {
        repository.delete(businessCard)
    }

    fun update(businessCard: BusinessCard) {
        repository.delete(businessCard)
    }
}

class MainViewModelFactory(private val repository: BusinessCardRepository):
        ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}