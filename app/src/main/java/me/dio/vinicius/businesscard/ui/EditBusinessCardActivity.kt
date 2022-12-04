package me.dio.vinicius.businesscard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import me.dio.vinicius.businesscard.App
import me.dio.vinicius.businesscard.R
import me.dio.vinicius.businesscard.databinding.ActivityAddBusinessCardBinding
import me.dio.vinicius.businesscard.databinding.ActivityEditBusinessCardBinding
import me.dio.vinicius.businesscard.domain.BusinessCard
import me.dio.vinicius.businesscard.viewmodel.MainViewModel
import me.dio.vinicius.businesscard.viewmodel.MainViewModelFactory

class EditBusinessCardActivity : AppCompatActivity() {
    private val binding by lazy { ActivityEditBusinessCardBinding.inflate(layoutInflater)}
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
        fillInfo()
    }

    private fun insertListeners(){
        binding.btClose.setOnClickListener {
            finish()
        }

        binding.btConfirm.setOnClickListener {
            val businessCard = BusinessCard(
                name = binding.tvUpdateCardName.editText?.text.toString(),
                business = binding.tvUpdateCardBusiness.editText?.text.toString(),
                phone = binding.tvUpdateCardPhone.editText?.text.toString(),
                email = binding.tvUpdateCardEmail.editText?.text.toString(),
                backgroundColor = binding.tvUpdateCardColor.editText?.text.toString()
            )
            mainViewModel.update(businessCard)
            Snackbar.make(binding.root, R.string.label_show_update_success, Snackbar.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun fillInfo(){
        binding.tvUpdateCardName.editText?.setText(mainViewModel.selectedCard?.name ?: "1111")
        binding.tvUpdateCardEmail.editText?.setText(mainViewModel.selectedCard?.email ?: "1111")
        binding.tvUpdateCardPhone.editText?.setText(mainViewModel.selectedCard?.phone?: "1111")
        binding.tvUpdateCardBusiness.editText?.setText(mainViewModel.selectedCard?.business ?: "")
        binding.tvUpdateCardColor.editText?.setText(mainViewModel.selectedCard?.backgroundColor ?: "")
    }
}