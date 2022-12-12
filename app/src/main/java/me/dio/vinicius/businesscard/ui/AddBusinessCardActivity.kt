package me.dio.vinicius.businesscard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import me.dio.vinicius.businesscard.App
import me.dio.vinicius.businesscard.R
import me.dio.vinicius.businesscard.databinding.ActivityAddBusinessCardBinding
import me.dio.vinicius.businesscard.domain.BusinessCard
import me.dio.vinicius.businesscard.viewmodel.MainViewModel
import me.dio.vinicius.businesscard.viewmodel.MainViewModelFactory

class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy {ActivityAddBusinessCardBinding.inflate(layoutInflater)}
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.card = BusinessCard()
        binding.activity = this
    }

    fun confirmCard(){
        mainViewModel.insert(binding.card!!)
        Snackbar.make(binding.root, R.string.label_show_insert_success, Snackbar.LENGTH_SHORT).show()
        finish()
    }
}