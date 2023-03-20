package com.example.meuslivros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.example.meuslivros.databinding.ActivityMainBinding
import com.example.meuslivros.db.AppDatabase

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "meus_livros_db"
        ).allowMainThreadQueries()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.cadastrarNovoLivro.setOnClickListener {
            var intent = Intent(this, CadastrarLivro::class.java)
            startActivity(intent)
        }
        binding.listarLivros.setOnClickListener {
            var intent = Intent(this, ListarLivros::class.java)
            startActivity(intent)
        }
    }


}