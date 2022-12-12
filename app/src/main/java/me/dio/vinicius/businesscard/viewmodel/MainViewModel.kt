package me.dio.vinicius.businesscard.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.dio.vinicius.businesscard.data.BusinessCardRepository
import me.dio.vinicius.businesscard.domain.BusinessCard

class MainViewModel(private val repository: BusinessCardRepository) : ViewModel(){

    enum class State {
        LOADING,
        DONE,
        ERROR
    }

    var selectedCard: BusinessCard? = null
    private val _card = MutableLiveData<BusinessCard>()
    val card : LiveData<BusinessCard> = _card
    private val _state = MutableLiveData<State>()
    val state : LiveData<State> = _state

    fun insert(businessCard: BusinessCard) {
        repository.insert(businessCard)
    }

    fun getAll(): LiveData<List<BusinessCard>> = repository.getAll()

    fun getById(id:Int) = repository.getById(id)
//        _state.value = State.LOADING
//        val c = repository.getById(id)
//        _card.value = c.value
//        Log.d("GET", "${_card.value.toString()} - $id")
//        _state.value = State.DONE
//    }

    fun delete(businessCard: BusinessCard) {
        repository.delete(businessCard)
    }

    fun update(businessCard: BusinessCard) {
        repository.update(businessCard)
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