package com.example.meuslivros.repository

import androidx.lifecycle.LiveData
import com.example.meuslivros.dao.LivroDao
import com.example.meuslivros.model.Livro

class LivroRepository (private val livroDao : LivroDao) {

    val realAllData : LiveData<List<Livro>> = livroDao.listAll()

    suspend fun addLivro (livro: Livro){
        livroDao.insert(livro)
    }
}