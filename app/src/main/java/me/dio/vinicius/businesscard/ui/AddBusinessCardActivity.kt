package me.dio.vinicius.businesscard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.dio.vinicius.businesscard.databinding.ActivityAddBusinessCardBinding

class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy {ActivityAddBusinessCardBinding.inflate(layoutInflater)}

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
           //TODO
        }
    }
}