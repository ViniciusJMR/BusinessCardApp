package me.dio.vinicius.businesscard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        fillInfo()

        insertListeners()
    }

    private fun insertListeners(){
        binding.btClose.setOnClickListener {
            finish()
        }

        binding.btConfirm.setOnClickListener {

            val businessCard = BusinessCard(
                id = id,
                name = binding.tvUpdateCardName.editText?.text.toString(),
                business = binding.tvUpdateCardBusiness.editText?.text.toString(),
                phone = binding.tvUpdateCardPhone.editText?.text.toString(),
                email = binding.tvUpdateCardEmail.editText?.text.toString(),
                backgroundColor = binding.tvUpdateCardColor.editText?.text.toString()
            )
            mainViewModel.update(businessCard)
//            mainViewModel.update(binding.card!!)
            Snackbar.make(binding.root, R.string.label_show_update_success, Snackbar.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun fillInfo(){
        id = intent.extras?.getInt("id")!!
        mainViewModel.getById(id)
        mainViewModel.state.observe(this) {
            when(it){
                MainViewModel.State.DONE -> {
                    binding.tvUpdateCardId.text = id.toString()
                    binding.card = mainViewModel.card.value
                    Log.d("FILL", id.toString())
                    Log.d("FILL", binding.card.toString())
                    Log.d("FILL", mainViewModel.card.value.toString())
                }
                MainViewModel.State.LOADING -> TODO()
                MainViewModel.State.ERROR -> TODO()
            }

        }
    }
}