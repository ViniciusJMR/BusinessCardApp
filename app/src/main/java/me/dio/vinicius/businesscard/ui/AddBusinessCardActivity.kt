package me.dio.vinicius.businesscard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        insertListeners()
    }

    private fun insertListeners(){
        binding.btClose.setOnClickListener {
           finish()
        }

        binding.btConfirm.setOnClickListener {
            val businessCard = BusinessCard(
                name = binding.tvNewCardName.editText?.text.toString(),
                business = binding.tvNewCardBusiness.editText?.text.toString(),
                phone = binding.tvNewCardPhone.editText?.text.toString(),
                email = binding.tvNewCardEmail.editText?.text.toString(),
                backgroundColor = binding.tvNewCardColor.editText?.text.toString()
            )
            mainViewModel.insert(businessCard)
            Snackbar.make(binding.root, R.string.label_show_success, Snackbar.LENGTH_SHORT).show()
            finish()
        }
    }
}