package com.example.meuslivros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.meuslivros.databinding.ActivityListarLivrosBinding
import com.example.meuslivros.viewModel.LivroViewModel

class ListarLivros : AppCompatActivity() {

    lateinit var binding: ActivityListarLivrosBinding
    lateinit var livroViewModel: LivroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_livros)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_listar_livros)
        livroViewModel = ViewModelProvider(this).get(LivroViewModel::class.java)

       binding.buttonAnterior.setOnClickListener {
           livroViewModel.irAnterior(livroViewModel.index.value!!)
           Log.i("index", "${livroViewModel.index.value}")
           livroViewModel.readAllData.observe(this, Observer {
               livroViewModel.index.observe(this, Observer { index ->
                   binding.textViewTitulo.text = it[livroViewModel.index.value!!].nome
                   binding.textViewAutor.text = it[livroViewModel.index.value!!].autor
                   binding.textViewAno.text = it[livroViewModel.index.value!!].ano.toString()
                   binding.ratingBarListar.rating = it[livroViewModel.index.value!!].nota.toFloat()
               })

           })

       }

        binding.buttonProximo.setOnClickListener {
            livroViewModel.irProximo(livroViewModel.index.value!!)

            Log.i("index", "${livroViewModel.index.value}")
            livroViewModel.readAllData.observe(this, Observer {

                    binding.textViewTitulo.text = it[livroViewModel.index.value!!].nome
                    binding.textViewAutor.text = it[livroViewModel.index.value!!].autor
                    binding.textViewAno.text = it[livroViewModel.index.value!!].ano.toString()
                    binding.ratingBarListar.rating = it[livroViewModel.index.value!!].nota.toFloat()


            })
        }

        livroViewModel.readAllData.observe(this, Observer {
            binding.textViewTitulo.text = it[livroViewModel.index.value!!].nome
            binding.textViewAutor.text = it[livroViewModel.index.value!!].autor
            binding.textViewAno.text = it[livroViewModel.index.value!!].ano.toString()
            binding.ratingBarListar.rating = it[livroViewModel.index.value!!].nota.toFloat()
        })
    }
}