package com.example.meuslivros.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.meuslivros.model.Livro

@Dao
interface LivroDao {
    @Insert
    fun insert(livro: Livro)

    @Delete
     fun delete(livro: Livro)

    @Update
    fun update(livro : Livro)

    @Query("SELECT * FROM Livro")
    fun listAll(): LiveData<List<Livro>>
}