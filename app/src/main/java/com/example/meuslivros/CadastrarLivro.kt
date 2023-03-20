package com.example.meuslivros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.meuslivros.databinding.ActivityCadastrarLivroBinding
import com.example.meuslivros.model.Livro
import com.example.meuslivros.viewModel.LivroViewModel

class CadastrarLivro : AppCompatActivity() {

    lateinit var binding: ActivityCadastrarLivroBinding
    lateinit var livroViewModel: LivroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_livro)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cadastrar_livro)
        livroViewModel = ViewModelProvider(this).get(LivroViewModel::class.java)

        binding.buttonCancelar.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        
        binding.ratingBarNota.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Toast.makeText(this, "Teste", Toast.LENGTH_SHORT).show()
        }

        binding.buttonSalvar.setOnClickListener {
            isertDataToDatabase()
        }

    }

    private fun isertDataToDatabase() {
        val titulo = binding.fieldTitulo.text.toString()
        val autor = binding.fieldAutor.text.toString()
        val ano  = binding.editTextDate.text.toString()

        val nota = binding.ratingBarNota.rating.toInt()
        Log.i("nota", "Livro: $titulo, $autor, $ano, $nota")

       if (inputCheck(titulo, autor, ano)){
           val livro = Livro(titulo,autor,ano.toInt(),nota)
           Log.i("livro", "Livro: $livro")

           livroViewModel.addLivro(livro)
           Toast.makeText(this, "Livro adicionado com sucesso!!", Toast.LENGTH_SHORT).show()
       }
        else{
           Toast.makeText(this, "erro ao cadastra o livro, por favor verifique os campos", Toast.LENGTH_SHORT).show()
       }
    }

    private fun inputCheck(titulo : String, autor : String, ano : String) : Boolean{
        return !(titulo.isEmpty() && autor.isEmpty() && ano.isEmpty())
    }


}