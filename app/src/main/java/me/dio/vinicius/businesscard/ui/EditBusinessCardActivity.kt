package me.dio.vinicius.businesscard.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import me.dio.vinicius.businesscard.App
import me.dio.vinicius.businesscard.R
import me.dio.vinicius.businesscard.databinding.ActivityEditBusinessCardBinding
import me.dio.vinicius.businesscard.viewmodel.MainViewModel
import me.dio.vinicius.businesscard.viewmodel.MainViewModelFactory

class EditBusinessCardActivity : AppCompatActivity() {
    private val binding by lazy { ActivityEditBusinessCardBinding.inflate(layoutInflater)}
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.activity = this

        fillInfo()
    }


    private fun fillInfo(){
        id = intent.extras?.getInt("id")!!
        mainViewModel.getById(id).observe(this) {
            binding.card = it
        }
    }

    fun confirmCard() {
        mainViewModel.update(binding.card!!)
        Snackbar.make(binding.root, R.string.label_show_update_success, Snackbar.LENGTH_SHORT).show()
        finish()
    }
}