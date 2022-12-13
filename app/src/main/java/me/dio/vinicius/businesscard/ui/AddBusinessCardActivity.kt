package me.dio.vinicius.businesscard.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import me.dio.vinicius.businesscard.App
import me.dio.vinicius.businesscard.R
import me.dio.vinicius.businesscard.databinding.ActivityAddBusinessCardBinding
import me.dio.vinicius.businesscard.domain.BusinessCard
import me.dio.vinicius.businesscard.viewmodel.MainViewModel
import me.dio.vinicius.businesscard.viewmodel.MainViewModelFactory

class AddBusinessCardActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener{

    private val binding by lazy {ActivityAddBusinessCardBinding.inflate(layoutInflater)}
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private val colorMap =
        mapOf("White" to R.color.white, "Black" to R.color.black
            , "Purple" to R.color.purple_200, "Teal" to R.color.teal_200 )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.card = BusinessCard()
        binding.activity = this

        setupSpinner()
    }

    private fun setupSpinner(){
        ArrayAdapter.createFromResource(
            this,
                R.array.color_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.sNewCardColor.adapter = adapter
        }
        binding.sNewCardColor.onItemSelectedListener = this
    }


    override fun onItemSelected(parent: AdapterView<*>?, v: View?, pos: Int, id: Long) {
        val item = parent?.getItemAtPosition(pos) as String

        binding.card?.backgroundColor = resources.getString(colorMap[item]!!)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        binding.card?.backgroundColor = resources.getString(colorMap["white"]!!)
    }

    fun confirmCard(){
        mainViewModel.insert(binding.card!!)
        Snackbar.make(binding.root, R.string.label_show_insert_success, Snackbar.LENGTH_SHORT).show()
        finish()
    }
}